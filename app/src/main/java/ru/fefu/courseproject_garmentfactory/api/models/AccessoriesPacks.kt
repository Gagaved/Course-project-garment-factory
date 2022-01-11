package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class AccessoriesPacks(
    val batch: Int = 0,
    val article: Int = 0,
    var count: Int = 0,
)

class AccessoriesPacksDeserializer: JsonDeserializer<AccessoriesPacks> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AccessoriesPacks = AccessoriesPacks(
        json?.asJsonObject?.get("batch")?.asInt?:0,
        json?.asJsonObject?.get("article")?.asInt?:0,
        json?.asJsonObject?.get("count")?.asInt?:0,
    )
}
