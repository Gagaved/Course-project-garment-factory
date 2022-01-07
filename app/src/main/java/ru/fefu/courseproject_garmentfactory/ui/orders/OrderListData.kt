package ru.fefu.courseproject_garmentfactory.ui.orders

import java.time.LocalDateTime
data class OrderListData(
    val id: Int,
    val code: String,
    val customer: String,
    val status: String,
)