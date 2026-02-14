package com.example.motiontubes.data.repository

import com.example.motiontubes.data.model.Order
import com.example.motiontubes.data.local.CartEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object OrderManager {

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    fun createOrder(cart: List<CartEntity>) {
        if (cart.isEmpty()) return

        val newOrder = Order(items = cart)
        _orders.value = listOf(newOrder) + _orders.value
    }

    fun update(order: Order) {
        _orders.value = _orders.value.map {
            if (it.id == order.id) order else it
        }
    }
}