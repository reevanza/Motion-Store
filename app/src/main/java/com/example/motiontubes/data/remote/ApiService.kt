package com.example.motiontubes.data.remote

import com.example.motiontubes.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products?limit=200")
    suspend fun getProducts(): ProductResponse

    @GET("products/search")
    suspend fun search(
        @Query("q") query: String
    ): ProductResponse
}