package ru.fefu.courseproject_garmentfactory.api.models

data class User(
    val login: String,
    val name: String,
    val role: Int,
)