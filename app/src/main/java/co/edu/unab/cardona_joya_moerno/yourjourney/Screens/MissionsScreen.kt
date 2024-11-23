package co.edu.unab.cardona_joya_moerno.yourjourney

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissionsScreen(navController: NavController, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color(0xFFFFF2E0))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                MissionBox("Meditación", "Medita durante 5 minutos.", "RECOMPENSA: 10 Pts.", R.drawable.meditation_image)
            }
            item {
                MissionBox("Escritura", "Escribe los sueños que has cumplido y como lo has hecho.", "RECOMPENSA: 15 Pts.", R.drawable.writing_image)
            }
            item {
                MissionBox("Familia", "Llama a una persona cercana y pregúntale por su día.", "RECOMPENSA: 8 Pts.", R.drawable.family_image)
            }
            item {
                MissionBox("Dibuja", "Haz una caricatura de tu personaje favorito.", "RECOMPENSA: 20 Pts.", R.drawable.drawing_image)
            }
        }
    }
}

@Composable
fun MissionBox(title: String, description: String, reward: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFAFAFA))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = title.uppercase(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF78871)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color(0xFF000000)

                )
                Text(
                    text = reward,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color(0xFF000000)
                )
            }
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0E0E0))
            )
        }
    }
}


