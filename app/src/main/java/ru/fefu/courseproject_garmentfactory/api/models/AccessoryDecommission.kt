package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

data class AccessoryDecommission(
    val article: Int = 0,
    val batch: Int = 0,
    val quantity: Int = 0
)

class AccessoryDecommissionSerializer: JsonSerializer<AccessoryDecommission> {
    override fun serialize(
        src: AccessoryDecommission,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement = JsonObject().apply {
        addProperty("article", src.article)
        addProperty("batch", src.batch)
        addProperty("quantity", src.quantity)
    }
}