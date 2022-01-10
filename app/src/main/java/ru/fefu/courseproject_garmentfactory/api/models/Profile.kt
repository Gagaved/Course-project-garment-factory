package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class Profile(
    val login: String = "",
    val name: String = "",
    val role: Int = 0
)

class ProfileDeserializer : JsonDeserializer<Profile> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Profile = Profile(
        json.asJsonObject.get("login").asString,
        json.asJsonObject.get("name").asString,
        json.asJsonObject.get("role").asInt
    )
}
