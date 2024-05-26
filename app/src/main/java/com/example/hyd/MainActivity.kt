package com.example.hyd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hyd.ui.theme.HydTheme


import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            HydTheme {
//                hydApp()
//            }

                val first_fragment=fragment_0_1()
                val secon_fragment=Fragment_0_2()



            supportFragmentManager.beginTransaction()
                .replace(R.id.flfragment, Fragment1())
                .addToBackStack(null)
                .commit()
        }
    }
}
