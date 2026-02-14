package com.example.motiontubes.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SessionManager {

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId

    fun login(id: Int) {
        _userId.value = id
    }

    fun logout() {
        _userId.value = null
    }

    val isLoggedIn: Boolean
        get() = _userId.value != null
}