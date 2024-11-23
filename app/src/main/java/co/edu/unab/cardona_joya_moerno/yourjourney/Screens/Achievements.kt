package co.edu.unab.cardona_joya_moerno.yourjourney.Screens

import android.os.Bundle
import androidx.compose.ui.text.style.TextAlign
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import co.edu.unab.cardona_joya_moerno.yourjourney.R

class AchievementsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Logros",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                actions = {
                    IconButton(onClick = { /* Acción para el menú */ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFF2E0)
                )
            )
        },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFF2E0))
                .padding(16.dp)
        ) {
            AchievementsGrid()

            Spacer(modifier = Modifier.height(16.dp))

            // Cuadro blanco de "¡Tus Logros!"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp), // Ajusta la altura al 30% más grande del original (por ejemplo, si antes era 120.dp)
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "¡Tus Logros!\nAquí podrás ver los logros que has conseguido en Your Journey",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun AchievementsGrid() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            AchievementCard("Calma tu mente", R.drawable.serene_sunset_sail)
            AchievementCard("Reflección", R.drawable.sunset_reflection)
            AchievementCard("Esperanzas", R.drawable.distant_dreams)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            AchievementCard("Sueños", R.drawable.eclipse_peace)
            AchievementCard("Tu Fuerza", R.drawable.siberian_strength)
            AchievementCard("Sabiduría", R.drawable.owl_wisdom)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            AchievementCard("Amistad", R.drawable.comforting_friend)
            AchievementCard("Calma", R.drawable.calm_amidst_fire)
            AchievementCard("Visión", R.drawable.light_in_darkness)
        }
    }
}


@Composable
fun AchievementCard(title: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color(0x99000000))
                    .padding(4.dp)
            )
        }
    }
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
            selected = false,
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_explore),
                    contentDescription = "Explorar",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Explorar") },
            selected = false,
            onClick = { navController.navigate("explore") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_library),
                    contentDescription = "Librería",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Librería") },
            selected = false,
            onClick = { navController.navigate("library") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AchievementsScreenPreview() {

}
