package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonElement
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

data class Order(
    val id: Int,
    val creation_date: String,
    var completion_date: String,
    var stage: Int,
    val manager: User,
    val customer: User,
    val cost: Int,
    var products: List<Product>,
)