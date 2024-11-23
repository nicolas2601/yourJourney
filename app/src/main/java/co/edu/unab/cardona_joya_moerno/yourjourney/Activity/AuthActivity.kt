package co.edu.unab.cardona_joya_moerno.yourjourney.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.LoginScreen
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.RegisterScreen
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.WelcomeScreen

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myController = rememberNavController()

            NavHost(
                    navController = myController,
                startDestination = "mainScreen"
            ) {
                composable("login") {
                    LoginScreen(myController)
                }
                composable("register") {
                    RegisterScreen(myController)
                }
                composable("mainScreen") {
                    WelcomeScreen(myController)
                }
            }
        }
    }
}
