package co.edu.unab.cardona_joya_moerno.yourjourney.Screens

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.edu.unab.cardona_joya_moerno.yourjourney.Models.Users
import co.edu.unab.cardona_joya_moerno.yourjourney.ui.theme.YourJourneyTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}

@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var urlImageProfile by remember { mutableStateOf("https://i.pinimg.com/originals/8a/a0/87/8aa0870bbf5bb3a165a41c97da736654.jpg") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    YourJourneyTheme {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = Color(0xFFFFF2E0)),
                verticalArrangement = Arrangement.Center
            ) {
                // Encabezados
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registro",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Ingresa la información para crear tu cuenta y registrate",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Tarjeta con fondo blanco
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = {
                                Text(text = "Nombre")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val calendar = Calendar.getInstance()
                                    val datePicker = DatePickerDialog(
                                        context,
                                        { _, year, month, dayOfMonth ->
                                            birthday = "$dayOfMonth/${month + 1}/$year"
                                        },
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH)
                                    )
                                    datePicker.show()
                                }
                        ) {
                            OutlinedTextField(
                                value = birthday,
                                onValueChange = { birthday = it },
                                label = {
                                    Text(text = "Fecha de nacimiento")
                                },
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                                enabled = false
                            )
                        }
                        OutlinedTextField(
                            value = urlImageProfile,
                            onValueChange = { urlImageProfile = it },
                            label = {
                                Text(text = "Url foto de perfil")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = {
                                Text(text = "Correo")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = {
                                Text(text = "Contraseña")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        Button(
                            onClick = {
                                if (name.isEmpty() ||
                                    birthday.isEmpty() ||
                                    urlImageProfile.isEmpty() ||
                                    email.isEmpty() ||
                                    password.isEmpty()
                                ) {
                                    Toast.makeText(context, "Llena los campos faltantes", Toast.LENGTH_LONG).show()
                                } else {
                                    registerIn(name, birthday, email, password, urlImageProfile, context, navController)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF78871))
                        ) {
                            Text(text = "Registrar")
                        }
                        OutlinedButton(
                            onClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Regresar")
                        }
                    }
                }
            }
        }
    }
}

fun registerIn(name: String, birthday: String, email: String, password: String, urlImageprofile: String, context: Context, navController: NavController) {
    val auth = Firebase.auth
    val db = Firebase.firestore
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                val newUser = Users(
                    name = name,
                    birthday = birthday,
                    email = email,
                    urlImageprofile = urlImageprofile

                )
    
                db.collection("users").document(user!!.uid)
                    .set(newUser.toMap())
                    .addOnSuccessListener {
                        Toast.makeText(context, "Usuario creado correctamente", Toast.LENGTH_LONG).show()
                        navController.navigate("login")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error al guardar usuario en Firestore", e)
                        Toast.makeText(context, "Error al guardar usuario.", Toast.LENGTH_SHORT).show()
                    }


                val newJson=newUser.toMap()

                db.collection("users").document(user!!.uid).set(newJson)
                Toast.makeText(context, "Usuario creado correctamente", Toast.LENGTH_LONG).show()
                navController.navigate("login")
            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(context, "Error al crear usuario.", Toast.LENGTH_SHORT).show()
            }
        }
}