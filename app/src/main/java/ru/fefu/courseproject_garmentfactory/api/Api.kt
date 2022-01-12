package ru.fefu.courseproject_garmentfactory.api

import retrofit2.Call
import retrofit2.http.*
import ru.fefu.courseproject_garmentfactory.api.models.*
import ru.fefu.courseproject_garmentfactory.ui.ItemListData
import retrofit2.http.GET



interface Api {
    @POST("api/v1/plane_login")
    fun login(@Body data: LoginRequest): Call<LoginResponse>

    @GET("api/v1/me")
    fun getProfile(@Header("Authorization") token: String): Call<Profile>

    @GET("api/v1/accessory")
    fun getAccessoryList(@Header("Authorization") token: String): Call<List<Accessories>>

    @GET("api/v1/cloth")
    fun getClothesList(@Header("Authorization") token: String): Call<List<Cloth>>

    @GET("api/v1/cloth/{number}")
    fun getClothPacks(@Header("Authorization") token: String, @Path("number") number: Int): Call<List<ClothPack>>

    @GET("api/v1/product")
    fun getProductList(@Header("Authorization") token: String): Call<List<Product>>

}