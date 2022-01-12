package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.fefu.courseproject_garmentfactory.ui.ItemListData
import java.lang.reflect.Type

data class Cloth(
    val article: Int = 0,
    val _name: String = "",
    val print: String = "",
    val _image: String = "",
    val composition: String = "",
    val width: Int = 0,
    val price: Int = 0,
    val color: String = "",
): ItemListData(id = 0, name = _name, code = article, image = _image)

class ClothDeserializer: JsonDeserializer<Cloth> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Cloth = Cloth(
        json?.asJsonObject?.get("article")?.asInt?:0,
        json?.asJsonObject?.get("name")?.asString?:"",
        json?.asJsonObject?.get("print")?.asString?:"",
        json?.asJsonObject?.get("image")?.asString?:"",
        json?.asJsonObject?.get("composition")?.asString?:"",
        json?.asJsonObject?.get("width")?.asInt?:0,
        json?.asJsonObject?.get("price")?.asInt?:0,
        json?.asJsonObject?.get("color")?.asString?:"",
    )
}
