package ru.fefu.courseproject_garmentfactory.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponse

interface Api {
    @POST("api/v1/login")
    fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}