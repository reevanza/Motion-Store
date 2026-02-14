package com.example.motiontubes.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.motiontubes.viewmodel.ProductViewModel
import com.example.motiontubes.data.repository.OrderManager

@Composable
fun SuccessScreen(
    nav: NavController,
    vm: ProductViewModel
) {

    val cart by vm.cart.collectAsState(initial = emptyList())

    LaunchedEffect(cart) {
        if (cart.isNotEmpty()) {
            OrderManager.createOrder(cart)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Payment Success 🎉")

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { nav.navigate("shipping") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Track Order")
        }
    }
}