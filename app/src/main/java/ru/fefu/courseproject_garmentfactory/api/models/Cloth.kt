package ru.fefu.courseproject_garmentfactory.api.models

import ru.fefu.courseproject_garmentfactory.ui.ItemListData

data class Cloth(
    val print: String = "",
    val composition: String = "",
    val width: Int = 0,
    val price: Int = 0,
    val color: String = "",
) : ItemListData(id = 0, name = "", article = 0, image = "")

