package ru.fefu.courseproject_garmentfactory.api.models
data class Order(
    val id: Int,
    val creation_date: String,
    var completion_date: String,
    var stage: Int,
    val manager: User,
    val customer: User,
    val cost: Int,
    val products: List<Product>,
)