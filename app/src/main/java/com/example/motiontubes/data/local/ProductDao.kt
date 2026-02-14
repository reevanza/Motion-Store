package com.example.motiontubes.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM cart")
    fun getCart(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartEntity)

    @Query("UPDATE cart SET qty = qty + 1 WHERE id = :id")
    suspend fun increase(id: Int)

    @Query("UPDATE cart SET qty = qty - 1 WHERE id = :id AND qty > 1")
    suspend fun decrease(id: Int)

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun remove(id: Int)
}