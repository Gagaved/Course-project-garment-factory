package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class ClothPack(
    val article: Int = 0,
    val number: String = "",
    var length: Float = 0F,
)

class ClothPacksDeserializer: JsonDeserializer<ClothPack> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ClothPack = ClothPack(
        json?.asJsonObject?.get("article")?.asInt?:0,
        json?.asJsonObject?.get("name")?.asString?:"",
        json?.asJsonObject?.get("length")?.asFloat?:0F,
    )
}
