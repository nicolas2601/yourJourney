package co.edu.unab.cardona_joya_moerno.yourjourney.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.edu.unab.cardona_joya_moerno.yourjourney.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF2E0)) // Fondo color crema
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Título de la aplicación
        Text(
            text = "Your\nJourney",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen dentro de un círculo
        Image(
            painter = painterResource(id = R.drawable.imagefor), // Reemplaza con el ID de tu imagen
            contentDescription = "Imagen de bienvenida",
            modifier = Modifier
                .size(300.dp) // Tamaño del círculo
                .clip(CircleShape) // Recorte en forma de círculo
                .background(Color.Gray), // Fondo gris para el círculo (opcional)
            contentScale = ContentScale.Crop // Ajuste de la imagen para llenar el círculo
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de saludo
        Text(
            text = "Oh, Hola!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF78871) // Color vibrante para el texto
        )

        // Subtítulo
        Text(
            text = "¡Bienvenido Aventurero! ¿Es hora de ir de aventura?",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones de "Registro" y "Entrar"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = "REGISTRO", color = Color.White)
            }

            OutlinedButton(
                onClick = { navController.navigate("login") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = "ENTRAR", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}



