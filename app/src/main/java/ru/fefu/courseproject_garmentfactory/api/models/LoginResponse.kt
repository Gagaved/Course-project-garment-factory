package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class LoginResponse(
    var access_token: String?,
    var token_type: String?
)

class LoginResponseDeserializer: JsonDeserializer<LoginResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LoginResponse = LoginResponse(
        json.asJsonObject.get("access_token").asString,
        json.asJsonObject.get("token_type").asString,
    )
}