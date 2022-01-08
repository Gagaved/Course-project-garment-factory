package ru.fefu.courseproject_garmentfactory.api

import android.app.Application
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.fefu.courseproject_garmentfactory.api.models.LoginRequest
import ru.fefu.courseproject_garmentfactory.api.models.LoginRequestSerializer
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponse
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponseDeserializer

class App: Application() {
    private lateinit var retrofit: Retrofit

    companion object {
        private lateinit var api: Api

        val getApi: Api
            get() = api
    }

    override fun onCreate() {
        super.onCreate()

        val gson = GsonBuilder()
            .registerTypeAdapter(LoginRequest::class.java, LoginRequestSerializer())
            .registerTypeAdapter(LoginResponse::class.java, LoginResponseDeserializer())
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("http://sewing.mrfox131.software/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(Api::class.java)
    }

}