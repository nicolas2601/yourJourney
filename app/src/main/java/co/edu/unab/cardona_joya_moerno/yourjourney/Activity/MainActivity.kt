package co.edu.unab.cardona_joya_moerno.yourjourney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.DiaryScreen
import com.google.firebase.FirebaseApp
import co.edu.unab.cardona_joya_moerno.yourjourney.ui.theme.YourJourneyTheme
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.MeditationScreen
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.ProfileScreen
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.AchievementsScreen
import co.edu.unab.cardona_joya_moerno.yourjourney.Screens.DiaryScreen

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            YourJourneyTheme {
                val navController = rememberNavController()

                MainScaffold(
                    navController = navController,
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "MissionsScreen"
                    ) {
                        composable("MissionsScreen") {
                            MissionsScreen(navController, paddingValues)
                        }
                        composable("MeditationScreen") {
                            MeditationScreen(navController)
                        }
                        composable("profile") {
                            ProfileScreen(navController)
                        }
                        composable("diary") {
                            DiaryScreen(navController)
                        }
                        composable("logros") {
                            AchievementsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            DynamicTopBar(navController)
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicTopBar(navController: NavController) {
    // Obtenemos la ruta actual
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // Definimos el título dinámico basado en la ruta
    val title = when (currentRoute) {
        "MissionsScreen" -> "Misiones del Día"
        "logros" -> "Logros"
        "diary" -> "Diario"
        "profile" -> "Perfil"
        "MeditationScreen" -> "Meditación"
        else -> "Your Journey"
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFFF78871)
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Inicio",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Inicio") },
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "MissionsScreen",
            onClick = {
                navController.navigate("MissionsScreen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_explore),
                    contentDescription = "Logros",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Logros") },
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "logros",
            onClick = {
                navController.navigate("logros") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_library),
                    contentDescription = "Diario",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Diario") },
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "diary",
            onClick = {
                navController.navigate("diary") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_meditation), // Asegúrate de tener un ícono correspondiente
                    contentDescription = "Meditación",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Meditación") },
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "MeditationScreen",
            onClick = {
                navController.navigate("MeditationScreen") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile), // Asegúrate de tener este ícono
                    contentDescription = "Perfil",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Perfil") },
            selected = navController.currentBackStackEntryAsState().value?.destination?.route == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}


