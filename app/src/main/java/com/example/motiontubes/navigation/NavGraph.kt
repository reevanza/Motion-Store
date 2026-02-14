package com.example.motiontubes.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import androidx.room.Room
import com.example.motiontubes.viewmodel.ProductViewModel
import com.example.motiontubes.ui.screen.*
import com.example.motiontubes.data.local.AppDatabase
import com.example.motiontubes.data.repository.UserRepository
import com.example.motiontubes.auth.SessionManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(vm: ProductViewModel) {

    val nav = rememberNavController()
    val cart by vm.cart.collectAsState(initial = emptyList())
    val backStack by nav.currentBackStackEntryAsState()
    val current = backStack?.destination?.route

    val context = LocalContext.current

    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "motion_db"
        ).fallbackToDestructiveMigration().build()
    }

    val userRepo = remember {
        UserRepository(db.userDao())
    }

    val userId by SessionManager.userId.collectAsState()

    val hideBar = current == "login" || current == "register"

    Scaffold(

        topBar = {
            if (!hideBar) {
                TopAppBar(title = { Text("Motion Store") })
            }
        },

        bottomBar = {
            if (!hideBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = current == "explore",
                        onClick = {
                            nav.navigate("explore") {
                                popUpTo("explore")
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                if (current == "explore")
                                    Icons.Filled.Home
                                else
                                    Icons.Outlined.Home,
                                contentDescription = null
                            )
                        },
                        label = { Text("Explore") }
                    )

                    // 🛒 CART
                    NavigationBarItem(
                        selected = current == "cart",
                        onClick = {
                            nav.navigate("cart") {
                                popUpTo("explore")
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    val count = cart.sumOf { it.qty }
                                    if (count > 0) Badge { Text("$count") }
                                }
                            ) {
                                Icon(
                                    if (current == "cart")
                                        Icons.Filled.ShoppingCart
                                    else
                                        Icons.Outlined.ShoppingCart,
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text("Cart") }
                    )

                    NavigationBarItem(
                        selected = current == "shipping",
                        onClick = { nav.navigate("shipping") },
                        icon = {
                            Icon(
                                if (current == "shipping")
                                    Icons.Filled.LocalShipping
                                else
                                    Icons.Outlined.LocalShipping,
                                null
                            )
                        },
                        label = { Text("Tracking") }
                    )

                    NavigationBarItem(
                        selected = current == "profile",
                        onClick = {
                            nav.navigate("profile") {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector =
                                    if (current == "profile")
                                        Icons.Filled.Person
                                    else
                                        Icons.Outlined.Person,
                                contentDescription = null
                            )
                        },
                        label = { Text("Profile") }
                    )
                }
            }
        }

    ) { padding ->

        NavHost(
            navController = nav,
            startDestination = if (userId != null) "explore" else "login",
            modifier = Modifier.padding(padding)
        ) {

            composable("login") {
                LoginScreen(nav, userRepo)
            }

            composable("register") {
                RegisterScreen(nav, userRepo)
            }

            composable("explore") {
                ExploreScreen(nav, vm)
            }

            composable("detail/{id}") {
                val id = it.arguments?.getString("id")?.toIntOrNull() ?: 0
                DetailScreen(id, vm, nav)
            }

            composable("cart") {
                CartScreen(nav, vm)
            }

            composable("shipping") {
                ShippingScreen(nav, userRepo)
            }

            composable("checkout") {
                CheckoutScreen(nav)
            }

            composable("success") {
                SuccessScreen(nav, vm)
            }

            composable("profile") {
                ProfileScreen(nav, userRepo)
            }
        }
    }
}