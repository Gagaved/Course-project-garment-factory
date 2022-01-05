package ru.fefu.courseproject_garmentfactory.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.courseproject_garmentfactory.R
import ru.fefu.courseproject_garmentfactory.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
    }
}