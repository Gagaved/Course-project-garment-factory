package ru.fefu.courseproject_garmentfactory.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.MainActivity
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.LoginRequest
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponse
import ru.fefu.courseproject_garmentfactory.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var spinner: ProgressBar
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spinner = binding.spinnerLogin

        val button = binding.loginButtonLogin
        button.setOnClickListener(loginOnClickListener)
    }

    private val loginOnClickListener = View.OnClickListener {
        spinner.visibility = View.VISIBLE
        val login = binding.loginInputLogin.text.toString()
        val pass = binding.passInputLogin.text.toString()
        val data = LoginRequest(login, pass)
        App.getApi.login(data = data).enqueue(object: Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("failLogin", t.message.toString())
                spinner.visibility = View.INVISIBLE
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                spinner.visibility = View.INVISIBLE
                val textError: TextView = binding.errorTextLogin
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        val editor = App.getSharedPref.edit()
                        editor.putString(
                            App.APP_PREFERENCES_TOKEN,
                            "${it.token_type} ${it.access_token}"
                        )
                        editor.apply()
                        textError.text = ""
                        goToMain()
                    }
                } else {
                    when (response.code()) {
                        401 -> {
                            textError.text = "Неправильная связка логин-пароль, проверьте правильность введённых данных"
                        }
                        else -> {
                            textError.text = response.message()
                        }
                    }
                }
            }
        })
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}