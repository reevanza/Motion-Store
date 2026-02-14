package com.example.motiontubes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CartEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
}