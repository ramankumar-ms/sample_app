//package com.example.hyd
//
//
//import android.content.Context
//import android.content.Intent
//import androidx.annotation.StringRes
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.focusModifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.dimensionResource
//import androidx.compose.ui.res.stringResource
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.example.hyd.ui.Coffee
//import com.example.hyd.ui.Restaurant
//
//import com.example.hyd.ui.data.DataSource
//import com.example.hyd.ui.theme.hydViewModel
//
////import com.example.cupcake.data.DataSource
//
//
///**
// * Composable that displays the topBar and displays back button if back navigation is possible.
// */
//enum class hydScreen(@StringRes val title:Int){
//    Start(title = R.string.app_name),
//    Coffee(title = R.string.coffee_shop),
//    Restaurant(title = R.string.restaurant),
//    Parks(title = R.string.parks)
//}
//
//
//
//@Composable
//fun hydApp(
//
//) {
//
//
//    Restaurant(
//
//        onNextButtonClicked = { },
////                    onNextButtonClicked = {},
////                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
//        onCancelButtonClicked = {},
//        options = listOf("Restaurant 1", "Restaurant 2", "Restaurant 3", "Restaurant 4"),
//        onSelectionChanged = { },
//        modifier = Modifier.fillMaxHeight()
//    )
//
//
//    Coffee(
//
//        onNextButtonClicked = { },
////                    onNextButtonClicked = {},
////                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
//        onCancelButtonClicked = {},
//        options = listOf("Coffee 1", "Coffee 2", "Coffee 3", "Coffee 4"),
//        onSelectionChanged = { },
//        modifier = Modifier.fillMaxHeight()
//    )
//
//
//}
//



package com.example.hyd

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.hyd.ui.Coffee
import com.example.hyd.ui.Restaurant
import com.example.hyd.ui.theme.HydTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle


sealed class HydScreen(val route: String) {
    object Start : HydScreen("start")
    object Coffee : HydScreen("coffee")
    object Restaurant : HydScreen("restaurant")
    object FinalCoffee : HydScreen("final_coffee")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun hydApp() {
    val navController = rememberNavController()
    val viewModel: HydViewModel = viewModel()

    HydTheme {
        Scaffold(

        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),

                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 25.sp),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                    },


                )


                NavHost(navController, startDestination = HydScreen.Start.route) {
                    composable(HydScreen.Start.route) {
                        SelectionScreen(navController)
                    }
                    composable(HydScreen.Coffee.route) {
                        CoffeeScreen(viewModel, navController)
                    }
                    composable(HydScreen.Restaurant.route) {
                        RestaurantScreen(viewModel, navController)
                    }
                    composable(HydScreen.FinalCoffee.route) {
                        FinalCoffeeScreen(viewModel, navController, coffeeId = "Coffee 2")
                    }
                }
            }
        }
    }
}

@Composable

fun SelectionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Find the Coolest Cafe around you",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.cafe),
                contentDescription = "Coffee Image",
                modifier = Modifier.padding(end = 16.dp).padding(start = 16.dp).clip(RoundedCornerShape(percent = 15))


            )
            Button(onClick = { navController.navigate(HydScreen.Coffee.route) }) {
                Text("Cafe")
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Text(
            "Find the Best Restaurant around you",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.restaurant),
                contentDescription = "Restaurant Image",
                modifier = Modifier.padding(end = 16.dp).padding(start = 16.dp).clip(RoundedCornerShape(percent = 15))
            )
            Button(onClick = { navController.navigate(HydScreen.Restaurant.route) }) {
                Text("Restaurant")
            }
        }
    }

}


@Composable
fun CoffeeScreen(viewModel: HydViewModel,navController: NavHostController) {



    Coffee(

        onNextButtonClicked = {navController.navigate(HydScreen.FinalCoffee.route) },
        onCancelButtonClicked = { navController.navigateUp() },
        options = listOf("The Roastery Coffee House", "Autumn Leaf Cafe", "The Gallery Cafe", "Driven Cafe"),
        onSelectionChanged = { selectedCoffee ->
            viewModel.setSelectedCoffee(selectedCoffee)},
        modifier = Modifier.fillMaxHeight()
    )
}

@Composable
fun RestaurantScreen(viewModel: HydViewModel,navController: NavHostController) {



    Restaurant(
        onNextButtonClicked = { },
        onCancelButtonClicked = { navController.navigateUp() },
        options = listOf("Restaurant 1", "Restaurant 2", "Restaurant 3", "Restaurant 4"),
        onSelectionChanged = { selectedRestaurant ->
            viewModel.setSelectedRestaurant(selectedRestaurant)},
        modifier = Modifier.fillMaxHeight()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    hydApp()
}

