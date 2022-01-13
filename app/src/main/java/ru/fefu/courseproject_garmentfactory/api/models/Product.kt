package ru.fefu.courseproject_garmentfactory.api.models
import ru.fefu.courseproject_garmentfactory.ui.ItemListData

data class Product(
    val price: Int = 0,
    val width: Int = 0,
    val length: Int = 0,
    val comment: String ="",
    val previous: String = "",
    var clothes: List<Cloth>,
    var accessories: List<Accessories>,
): ItemListData(id = 0, name = "", article = 0, image = "",count = 0)

