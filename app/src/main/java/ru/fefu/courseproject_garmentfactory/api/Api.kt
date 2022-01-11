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

    @GET("api/v1/accessory/{Article}")
    fun getAccessoryPacks(@Header("Authorization") token: String, @Path("Article") article: Int): Call<List<AccessoriesPacks>>

    @POST("api/v1/accessory/{Article}/{Batch}")
    fun accessoryDecommission(@Body data: AccessoryDecommission) :Call<AccessoryDecommission>
}