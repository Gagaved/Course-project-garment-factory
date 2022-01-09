package ru.fefu.courseproject_garmentfactory.api

import retrofit2.Call
import retrofit2.http.*
import ru.fefu.courseproject_garmentfactory.api.models.Accessories
import ru.fefu.courseproject_garmentfactory.api.models.LoginRequest
import ru.fefu.courseproject_garmentfactory.api.models.LoginResponse
import ru.fefu.courseproject_garmentfactory.api.models.Profile
import ru.fefu.courseproject_garmentfactory.ui.ItemListData

interface Api {
    @POST("api/v1/plane_login")
    fun login(@Body data: LoginRequest): Call<LoginResponse>

    @GET("api/v1/me")
    fun getProfile(@Header("Authorization") token: String): Call<Profile>

    @GET("api/v1/accessory")
    fun getAccessoryList(@Header("Authorization") token: String): Call<List<Accessories>>
}