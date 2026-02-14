package com.example.motiontubes.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.motiontubes.data.repository.OrderManager
import com.example.motiontubes.data.repository.UserRepository
import com.example.motiontubes.auth.SessionManager
import com.example.motiontubes.data.model.Order
import kotlinx.coroutines.delay


@Composable
fun ShippingScreen(
    nav: NavController,
    repo: UserRepository
) {

    val orders by OrderManager.orders.collectAsState()
    val uid by SessionManager.userId.collectAsState()

    var address by remember { mutableStateOf("") }

    LaunchedEffect(uid) {
        uid?.let {
            val user = repo.getUser(it)
            address = user?.address ?: "-"
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Text("Order Tracking", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))
        }

        items(orders, key = { it.id }) { order ->

            OrderCard(order, address)

            Spacer(Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = { nav.navigate("explore") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Shop")
            }
        }
    }
}

@Composable
fun OrderCard(order: Order, address: String) {

    var status by remember(order.id) { mutableStateOf(order.status) }
    var progress by remember(order.id) { mutableStateOf(order.progress) }
    var finished by remember(order.id) { mutableStateOf(order.finished) }

    LaunchedEffect(order.id) {

        if (order.finished) return@LaunchedEffect

        delay(2000)
        status = "Pesanan dikemas"
        progress = 0.3f

        delay(3000)
        status = "Kurir mengambil paket"
        progress = 0.6f

        delay(3000)
        status = "Dalam perjalanan"
        progress = 0.8f

        delay(3000)
        status = "Hampir sampai"
        progress = 0.95f

        delay(3000)
        status = "Pesanan tiba 🎉"
        progress = 1f

        finished = true

        OrderManager.update(
            order.copy(
                status = status,
                progress = progress,
                finished = true
            )
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(Modifier.padding(16.dp)) {

            Text("Status", fontWeight = FontWeight.Bold)
            Text(status)

            Spacer(Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Text("Dikirim ke:")
            Text(address, fontWeight = FontWeight.SemiBold)

            Spacer(Modifier.height(12.dp))

            Text("Items:", fontWeight = FontWeight.Bold)

            order.items.forEach { item ->

                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AsyncImage(
                        model = item.image,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(Modifier.width(10.dp))

                    Column {
                        Text(item.title, maxLines = 1)
                        Text("Qty: ${item.qty}")
                    }
                }
            }

            if (finished) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Pesanan selesai",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}