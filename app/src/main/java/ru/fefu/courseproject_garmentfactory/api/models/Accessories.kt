package ru.fefu.courseproject_garmentfactory.api.models
import ru.fefu.courseproject_garmentfactory.ui.ItemListData
data class Accessories(
    val type: String = "",
    val width: Int = 0,
    val length: Int = 0,
    val weight: Double = 0.0,
    val price: Int = 0,
    val kg_acceptable: Boolean
): ItemListData(id = 0, name ="", article = 0, image = "")