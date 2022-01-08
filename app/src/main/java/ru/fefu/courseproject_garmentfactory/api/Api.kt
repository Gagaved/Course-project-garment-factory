package ru.fefu.courseproject_garmentfactory.api

import retrofit2.Call
import retrofit2.http.*
import ru.fefu.courseproject_garmentfactory.api.models.LoginRequest
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponse

interface Api {
    @POST("api/v1/plane_login")
    fun login(
        @Body data: LoginRequest
    ): Call<LoginResponse>
}