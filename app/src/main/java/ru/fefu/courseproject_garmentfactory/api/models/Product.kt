package ru.fefu.courseproject_garmentfactory.api.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import ru.fefu.courseproject_garmentfactory.ui.ItemListData
import java.lang.reflect.Type

data class Product(
    val article: Int = 0,
    val _name: String = "",
    val price: Int = 0,
    val width: Int = 0,
    val length: Int = 0,
    val comment: String ="",
    val _image: String = "",
    val previous: String = "",
    val clothes: List<Cloth>,
    val accessories: List<Accessories>,

): ItemListData(id = 0, name = _name, code = article, image = _image)

class ProductDeserializer: JsonDeserializer<Product> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Product = Product(
        json?.asJsonObject?.get("article")?.asInt?:0,
        json?.asJsonObject?.get("name")?.asString?:"",
        json?.asJsonObject?.get("price")?.asInt?:0,
        json?.asJsonObject?.get("width")?.asInt?:0,
        json?.asJsonObject?.get("length")?.asInt?:0,
        json?.asJsonObject?.get("comment")?.asString?:"",
        json?.asJsonObject?.get("image")?.asString?:"",
        json?.asJsonObject?.get("previous")?.asString?:"",
        json?.asJsonObject?.get("clothes")?.asJsonObject,
        json?.asJsonObject?.get("accessories")?.asJsonObject,???????????????????????
    )
}
