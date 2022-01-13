package ru.fefu.courseproject_garmentfactory.api.models
import ru.fefu.courseproject_garmentfactory.ui.ItemListData
data class Accessories(
    val type: String = "",
    val width: Int = 0,
    val length: Int = 0,
    val weight: Int = 0,
    val price: Int = 0,
): ItemListData(id = 0, name ="", article = 0, image = "")