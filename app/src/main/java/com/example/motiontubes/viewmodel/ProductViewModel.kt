package com.example.motiontubes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motiontubes.data.model.Product
import com.example.motiontubes.data.repository.ProductRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.motiontubes.data.local.CartEntity

class ProductViewModel(
    private val repo: ProductRepository
) : ViewModel() {

    val products = repo.getProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val cart = repo.getCart()

    fun addToCart(p: Product) = viewModelScope.launch {
        repo.addToCart(p)
    }

    fun increase(item: CartEntity) = viewModelScope.launch {
        repo.increase(item.id)
    }

    fun decrease(item: CartEntity) = viewModelScope.launch {
        repo.decrease(item.id)
    }

    fun remove(item: CartEntity) = viewModelScope.launch {
        repo.remove(item.id)
    }
}