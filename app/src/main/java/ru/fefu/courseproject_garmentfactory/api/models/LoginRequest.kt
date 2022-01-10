package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

data class LoginRequest(
    val login: String = "",
    val password: String = ""
)

class LoginRequestSerializer: JsonSerializer<LoginRequest> {
    override fun serialize(
        src: LoginRequest,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement = JsonObject().apply {
        addProperty("login", src.login)
        addProperty("password", src.password)
    }
}