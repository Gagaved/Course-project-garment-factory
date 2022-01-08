package ru.fefu.courseproject_garmentfactory.api

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    private lateinit var retrofit: Retrofit

    companion object {
        private lateinit var api: Api

        val getApi: Api
            get() = api
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl("http://sewing.mrfox131.software/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

}