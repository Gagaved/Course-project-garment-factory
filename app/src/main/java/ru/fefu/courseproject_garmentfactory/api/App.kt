package ru.fefu.courseproject_garmentfactory.api

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.fefu.courseproject_garmentfactory.MainActivity
import ru.fefu.courseproject_garmentfactory.api.models.*

class App: Application() {
    private lateinit var retrofit: Retrofit

    companion object {
        var current_role = 0

        private lateinit var api: Api
        lateinit var orderCurrentSelected: Order
        private lateinit var sharedPref: SharedPreferences
        private const val APP_PREFERENCES = "storage"
        const val APP_PREFERENCES_TOKEN = "token"

        val getSharedPref: SharedPreferences
            get() = sharedPref

        val getApi: Api
            get() = api

        fun getToken(): String {
            var token: String? = null
            if (sharedPref.contains(APP_PREFERENCES_TOKEN)){
                token = sharedPref.getString(APP_PREFERENCES_TOKEN, "")
            }
            token?:let{ token = ""}
            return token!!
        }

    }

    override fun onCreate() {
        super.onCreate()

        sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        retrofit = Retrofit.Builder()
            .baseUrl("http://sewing.mrfox131.software/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }
}