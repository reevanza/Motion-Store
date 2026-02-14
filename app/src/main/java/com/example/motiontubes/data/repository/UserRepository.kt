package com.example.motiontubes.data.repository

import com.example.motiontubes.data.local.UserDao
import com.example.motiontubes.data.local.UserEntity

class UserRepository(private val dao: UserDao) {

    suspend fun register(
        username: String,
        password: String,
        name: String,
        address: String,
        city: String
    ) {
        dao.insert(
            UserEntity(
                username = username,
                password = password,
                name = name,
                address = address,
                city = city
            )
        )
    }

    suspend fun login(username: String, password: String): UserEntity? {
        return dao.login(username, password)
    }

    suspend fun getUser(id: Int): UserEntity? {
        return dao.getById(id)
    }

    suspend fun update(user: UserEntity) {
        dao.update(user)
    }
}