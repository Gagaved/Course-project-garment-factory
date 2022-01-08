package ru.fefu.courseproject_garmentfactory.api.models

data class LoginResponse(
    var access_token: String?,
    var token_type: String?
)