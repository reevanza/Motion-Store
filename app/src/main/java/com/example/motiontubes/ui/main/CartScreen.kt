package com.example.motiontubes.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.motiontubes.viewmodel.ProductViewModel

@Composable
fun CartScreen(nav: NavController, vm: ProductViewModel) {

    val cart by vm.cart.collectAsState(initial = emptyList())
    val total = cart.sumOf { it.price * it.qty }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Your Cart",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        if (cart.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Cart masih kosong 🛒")
            }
            return
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            items(cart, key = { it.id }) { c ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        AsyncImage(
                            model = c.image,
                            contentDescription = null,
                            modifier = Modifier.size(70.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                c.title,
                                maxLines = 2,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                "$${c.price}",
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                OutlinedButton(
                                    onClick = { vm.decrease(c) },
                                    modifier = Modifier.size(36.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) { Text("-") }

                                Text(
                                    "${c.qty}",
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )

                                OutlinedButton(
                                    onClick = { vm.increase(c) },
                                    modifier = Modifier.size(36.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) { Text("+") }
                            }
                        }

                        IconButton(
                            onClick = { vm.remove(c) }
                        ) {
                            Text("🗑")
                        }
                    }
                }
            }
        }

        Surface(
            tonalElevation = 8.dp) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    "Total: $${"%.2f".format(total)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                Button(
                    onClick = { nav.navigate("checkout") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ) {
                    Text("Checkout")
                }
            }
        }
    }
}