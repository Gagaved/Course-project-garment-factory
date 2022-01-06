package ru.fefu.courseproject_garmentfactory.ui.product

import java.util.*

data class Product(
    val imgSrc: String?,
    val vendor: Int,
    val length: Int,
    val width: Int,
    val cloths: List<String>,
    val accessories: List<String>,
    val dateOfOlden: Date?
)