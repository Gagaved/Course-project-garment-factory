package ru.fefu.courseproject_garmentfactory.api

import retrofit2.Call
import retrofit2.http.*
import ru.fefu.courseproject_garmentfactory.api.models.*
import ru.fefu.courseproject_garmentfactory.ui.ItemListData
import retrofit2.http.GET



interface Api {
    @POST("api/v1/plane_login")
    fun login(@Body data: LoginRequest): Call<LoginResponse>

    @PATCH("api/v1/cloth/{article}/{number}?")
    fun clothDecommission(@Header("Authorization") token: String,
                          @Path("article") article: Int,
                          @Path("number") number: Int,
                          @Query("length") length: Float): Call<ClothDecommissionResponse>

    @PATCH("api/v1/accessory/{article}")
    fun accessoryDecommission(@Header("Authorization") token: String,
                          @Path("article") article: Int,
                          @Query("quantity") quantity: Int): Call<AccessoryDecommissionResponse>

    @GET("api/v1/order")
    fun getOrderList(@Header("Authorization") token: String): Call<List<Order>>

    @GET("api/v1/accessory/{article}")
    fun getAccessoryPack(@Header("Authorization") token: String, @Path("article") article: Int): Call<AccessoryPack>

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

    @GET("api/v1/product/{article}/previous")
    fun getPreviousProductList(@Header("Authorization") token: String, @Path("article") article: Int): Call<List<Product>>

}