package com.example.motiontubes.data.repository

import com.example.motiontubes.data.local.CartEntity
import com.example.motiontubes.data.local.ProductDao
import com.example.motiontubes.data.model.Product
import com.example.motiontubes.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(
    private val dao: ProductDao
) {

    private val api = RetrofitInstance.api

    fun getProducts(): Flow<List<Product>> = flow {

        val response = api.getProducts()

        val mapped = response.products.map {
            Product(
                id = it.id,
                title = it.title,
                price = it.price,
                description = it.description,
                image = it.thumbnail
            )
        }

        emit(mapped)
    }

    fun getCart() = dao.getCart()

    suspend fun addToCart(product: Product) {
        dao.insert(
            CartEntity(
                id = product.id,
                title = product.title,
                price = product.price,
                image = product.image,
                qty = 1
            )
        )
    }

    suspend fun increase(id: Int) {
        dao.increase(id)
    }

    suspend fun decrease(id: Int) {
        dao.decrease(id)
    }

    suspend fun remove(id: Int) {
        dao.remove(id)
    }
}