package co.edu.unab.cardona_joya_moerno.yourjourney.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import co.edu.unab.cardona_joya_moerno.yourjourney.R

class JournalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Entrada de Diario",
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
                    containerColor = Color(0xFFFFF2E0) // Color de fondo del TopAppBar
                )
            )
        },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFF2E0)) // Fondo principal
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Entrada de Diario
            Text(
                text = "Día [número]", // Placeholder para el día actual
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Cuadro de texto para nueva entrada
            val textState = remember { mutableStateOf(TextFieldValue()) }
            BasicTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { /* Acciones cuando se termina de escribir */ }),
                textStyle = LocalTextStyle.current.copy(color = Color.Black)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto para las entradas anteriores
            Text(text = "Entradas Anteriores:", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))

            // Ejemplo de una entrada de diario vacía (sin texto)
            JournalEntryCard(day = "[día]", title = "", content = "")
            JournalEntryCard(day = "[día]", title = "", content = "")
            JournalEntryCard(day = "[día]", title = "", content = "")
        }
    }
}

@Composable
fun JournalEntryCard(day: String, title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Día $day",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 14.sp
            )
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            if (content.isNotEmpty()) {
                Text(
                    text = content,
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
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
            onClick = { /* Acción para la pestaña de Inicio */ }
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
            onClick = { /* Acción para la pestaña de Explorar */ }
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
            onClick = { /* Acción para la pestaña de Librería */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiaryScreen() {

}
