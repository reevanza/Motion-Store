package com.example.motiontubes.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AuthManager {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _user = MutableStateFlow("Guest")
    val user: StateFlow<String> = _user

    fun login(name: String) {
        _user.value = name
        _isLoggedIn.value = true
    }

    fun logout() {
        _user.value = "Guest"
        _isLoggedIn.value = false
    }
}