package ru.fefu.courseproject_garmentfactory.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
//        binding.loginButtonLogin.setOnClickListener(loginOnClickListener)
    }

    private val loginOnClickListener = View.OnClickListener {
        val login = findViewById<TextInputEditText>(R.id.login_input_login).text.toString()
        val pass = findViewById<TextInputEditText>(R.id.pass_input_login).text.toString()
        val data = LoginRequest(login, pass)

        App.getApi.login(data = data).enqueue(loginCallback)
    }

    private val loginCallback = object: Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            // TODO: status 200 - successfully login
            response.body().let {
                Log.i("Login 200", it.toString())
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            // TODO: bad login
            Log.e("failLogin", t.message.toString())
        }
    }

}