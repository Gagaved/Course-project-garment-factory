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
        private lateinit var api: Api

        private lateinit var sharedPref: SharedPreferences
        private const val APP_PREFERENCES = "storage"
        const val APP_PREFERENCES_TOKEN = "token"

        val getSharedPref: SharedPreferences
            get() = sharedPref

        val getApi: Api
            get() = api

    }

    override fun onCreate() {
        super.onCreate()

        sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


        val gson = GsonBuilder()
            .registerTypeAdapter(LoginRequest::class.java, LoginRequestSerializer())
            .registerTypeAdapter(LoginResponse::class.java, LoginResponseDeserializer())
            .registerTypeAdapter(Profile::class.java, ProfileDeserializer())
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("http://sewing.mrfox131.software/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(Api::class.java)
    }
}