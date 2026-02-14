package com.example.motiontubes.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.motiontubes.auth.SessionManager
import com.example.motiontubes.data.local.UserEntity
import com.example.motiontubes.data.repository.UserRepository
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(nav: NavController, repo: UserRepository) {

    val uid by SessionManager.userId.collectAsState()
    var user by remember { mutableStateOf<UserEntity?>(null) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(uid) {
        uid?.let {
            user = repo.getUser(it)
        }
    }

    user?.let { u ->

        var name by remember { mutableStateOf(u.name) }
        var address by remember { mutableStateOf(u.address) }
        var city by remember { mutableStateOf(u.city) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text(
                "Profile",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nama") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Alamat") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("Kota") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {
                            scope.launch {
                                repo.update(
                                    u.copy(
                                        name = name,
                                        address = address,
                                        city = city
                                    )
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Save Profile")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    SessionManager.logout()
                    nav.navigate("login") {
                        popUpTo("explore") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Logout")
            }
        }
    }
}