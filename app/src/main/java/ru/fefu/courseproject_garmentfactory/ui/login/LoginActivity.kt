package ru.fefu.courseproject_garmentfactory.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.LoginRequest
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponse
import ru.fefu.courseproject_garmentfactory.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        val button = findViewById<Button>(R.id.login_button_login)
        button.setOnClickListener(loginOnClickListener)
    }

    private val loginOnClickListener = View.OnClickListener {
        val login = findViewById<TextInputEditText>(R.id.login_input_login).text.toString()
        val pass = findViewById<TextInputEditText>(R.id.pass_input_login).text.toString()
        val data = LoginRequest(login, pass)
        App.getApi.login(data = data).enqueue(object: Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("failLogin", t.message.toString())
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val textError: TextView = findViewById(R.id.error_text_login)
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        Log.i("Login 200", "${it.token_type}_${it.access_token}")
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
        //TODO: Сделать переход на главную
    }
}