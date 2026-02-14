package com.example.motiontubes.data.model

import com.example.motiontubes.data.local.CartEntity

data class Order(
    val id: Long = System.currentTimeMillis(),
    val items: List<CartEntity>,
    var status: String = "Pesanan diproses",
    var progress: Float = 0.1f,
    var finished: Boolean = false
)