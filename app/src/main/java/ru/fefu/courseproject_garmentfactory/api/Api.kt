package ru.fefu.courseproject_garmentfactory.api

import retrofit2.Call
import retrofit2.http.*
import ru.fefu.courseproject_garmentfactory.api.models.LoginRequest
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponse
import ru.fefu.courseproject_garmentfactory.api.models.Profile

interface Api {
    @POST("api/v1/plane_login")
    fun login(@Body data: LoginRequest): Call<LoginResponse>

    @GET("api/v1/me")
    fun getProfile(@Header("Authorization") token: String): Call<Profile>
}