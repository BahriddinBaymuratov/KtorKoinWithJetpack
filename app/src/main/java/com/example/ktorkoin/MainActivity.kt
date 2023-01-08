package com.example.ktorkoin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ktorkoin.ui.screen.Screen
import com.example.ktorkoin.ui.screen.product.ProductScreen
import com.example.ktorkoin.ui.screen.product_list.ProductListScreen
import com.example.ktorkoin.ui.theme.KtorKoinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorKoinTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = Screen.PostListScreen.route)
                    {
                        composable(Screen.PostListScreen.route) {
                            ProductListScreen(navHostController)
                        }
                        composable(
                            "${Screen.PostScreen.route}/{id}",
                            arguments = listOf(navArgument(name = "id") {
                                type = NavType.StringType
                            }
                            )
                        ) {
                            val id = it.arguments?.getString("id") ?: "1"
                            ProductScreen(navHostController, id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorKoinTheme {
        Greeting("Android")
    }
}