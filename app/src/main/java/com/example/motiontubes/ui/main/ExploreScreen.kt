package com.example.motiontubes.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.motiontubes.viewmodel.ProductViewModel

@Composable
fun ExploreScreen(nav: NavController, vm: ProductViewModel) {

    val products by vm.products.collectAsState(initial = emptyList())
    var query by remember { mutableStateOf("") }

    val filtered = products.filter {
        it.title.contains(query, ignoreCase = true)
    }

    Column {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Search product...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {

            items(filtered) { p ->

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { nav.navigate("detail/${p.id}") }
                ) {

                    Column(Modifier.padding(8.dp)) {

                        AsyncImage(
                            model = p.image,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp),
                            contentScale = ContentScale.Fit
                        )

                        Text(p.title, maxLines = 2)
                        Text("$${p.price}")

                        Button(
                            onClick = { vm.addToCart(p) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Add")
                        }
                    }
                }
            }
        }
    }
}