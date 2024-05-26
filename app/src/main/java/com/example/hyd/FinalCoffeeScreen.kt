

package com.example.hyd

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.hyd.ui.theme.HydTheme
import androidx.compose.material3.Typography
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FinalCoffeeScreen(viewModel: HydViewModel, navController: NavHostController, coffeeId: String) {

    val id = viewModel.uiState.value.selectedCoffee


    val coffeeImageId = when (id) {
        "The Roastery Coffee House" -> R.drawable.coffee1
        "Autumn Leaf Cafe" -> R.drawable.coffee2
        "The Gallery Cafe" -> R.drawable.coffee3
        "Driven Cafe" -> R.drawable.coffee4
        else -> R.drawable.coffee1
    }

    val coffeeTextId = when (id) {
        "The Roastery Coffee House" -> R.string.Coffee1_name
        "Autumn Leaf Cafe" -> R.string.Coffee2_name
        "The Gallery Cafe" -> R.string.Coffee3_name
        "Driven Cafe" -> R.string.Coffee4_name
        else -> R.string.Coffee4_name
    }


    val coffeeDescriptionId = when (id) {
        "The Roastery Coffee House" -> R.string.Coffee1_description
        "Autumn Leaf Cafe" -> R.string.Coffee2_description
        "The Gallery Cafe" -> R.string.Coffee3_description
        "Driven Cafe" -> R.string.Coffee4_description
        else -> R.string.Coffee4_description
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header text


        val isTablet = LocalContext.current.resources.configuration.smallestScreenWidthDp >= 600

        if (isTablet) {
            TabletLayout(coffeeTextId = coffeeTextId, coffeeImageId = coffeeImageId, coffeeDescriptionId = coffeeDescriptionId)
        } else {
            PhoneLayout(coffeeTextId = coffeeTextId, coffeeImageId = coffeeImageId, coffeeDescriptionId = coffeeDescriptionId)
        }

        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Handle the result if needed
        }



        Button(
            onClick = {
                // Use the latitude and longitude of the coffee shop to open Google Maps
                val latitude = 17.21
                val longitude = 78.28
                val uri = "geo:$latitude,$longitude"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                intent.setPackage("com.google.android.apps.maps")
                val chooserIntent = Intent.createChooser(intent, "Open with...")
                launcher.launch(chooserIntent)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(stringResource(id = R.string.open_in_maps))
        }
    }
}

@Composable
fun PhoneLayout(
    coffeeTextId: Int,
    coffeeDescriptionId: Int,
    coffeeImageId: Int
) {


    Text(
        text = stringResource(id = coffeeTextId),
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 25.sp,fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Image(
        painter = painterResource(id = coffeeImageId),
        contentDescription = "Coffee Image",
        modifier = Modifier.height(200.dp).clip(RoundedCornerShape(percent = 15))
    )
    Text(
        text = stringResource(id = coffeeDescriptionId),
        modifier = Modifier.padding(vertical = 16.dp)
    )

}


@Composable

fun TabletLayout(
    coffeeTextId: Int,
    coffeeDescriptionId: Int,
    coffeeImageId: Int
) {
    Row(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = coffeeTextId),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 35.sp,fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 16.dp).clip(RoundedCornerShape(percent = 20)
                )
            )
            Image(
                painter = painterResource(id = coffeeImageId),
                contentDescription = "Coffee Image",
                modifier = Modifier.height(200.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = coffeeDescriptionId),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}