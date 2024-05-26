package com.example.hyd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun RestaurantScreen(viewModel: HydViewModel, navController: NavHostController, restaurantId: String) {
    viewModel.setSelectedCoffee(restaurantId)


    val coffeeImageId = when (restaurantId) {
        "Coffee 1" -> R.drawable.coffee1
        "Coffee 2" -> R.drawable.coffee2
        "Coffee 3" -> R.drawable.coffee3
        "Coffee 4" -> R.drawable.coffee1
        else -> R.drawable.coffee1 // Placeholder image if coffee ID doesn't match
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = coffeeImageId),
            contentDescription = "Coffee Image",
//            modifier = Modifier.size(200.dp) // Adjust size as needed
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = restaurantId)
    }
}
