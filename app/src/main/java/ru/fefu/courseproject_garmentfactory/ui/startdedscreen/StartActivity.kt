package ru.fefu.courseproject_garmentfactory.ui.startdedscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.fefu.courseproject_garmentfactory.MainActivity
import ru.fefu.courseproject_garmentfactory.api.App
import ru.fefu.courseproject_garmentfactory.api.models.Profile
import ru.fefu.courseproject_garmentfactory.databinding.ActivityStartBinding
import ru.fefu.courseproject_garmentfactory.ui.login.LoginActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkToken()

        binding.repeatButtonStarted.visibility = View.INVISIBLE
        binding.repeatButtonStarted.setOnClickListener {
            checkToken()
            it.visibility = View.INVISIBLE
        }
    }

    private fun checkToken() {
        if (App.getSharedPref.contains(App.APP_PREFERENCES_TOKEN))
            App.getSharedPref.getString(App.APP_PREFERENCES_TOKEN, "")?.let {
                App.getApi.getProfile(it).enqueue(object : Callback<Profile> {
                    override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                        when (response.code()) {
                            200 -> {
                                App.current_role = response.body()?.role ?: -1
                                goToMain()
                            }
                            401 -> {
                                val editor = App.getSharedPref.edit()
                                editor.remove(App.APP_PREFERENCES_TOKEN)
                                editor.apply()
                                goToLogin()
                            }
                            else -> {
                                Toast.makeText(
                                    this@StartActivity,
                                    "Проверьте соединение с интернетом",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.spinnerStarted.visibility = View.GONE
                                binding.repeatButtonStarted.visibility = View.VISIBLE
                            }
                        }
                    }

                    override fun onFailure(call: Call<Profile>, t: Throwable) {
                        Log.d("StartedToken", t.message.toString())
                        binding.repeatButtonStarted.visibility = View.VISIBLE
                    }
                })
            }
        else {
            goToLogin()
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}