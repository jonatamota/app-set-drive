package com.example.setdrive.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.features.User.UserLogin

@Composable
fun RegisterScreen(navController: NavController? = null, onEnterClick: (UserLogin) -> Unit = {}) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userAge by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var confirmEmail by remember { mutableStateOf("") }

    // Verificação da validade do formulário
    val isFormValid by derivedStateOf {
        firstName.isNotBlank() && lastName.isNotBlank() && userAge.isNotBlank() && userAge.all { it.isDigit() } && userEmail.isNotBlank() && confirmEmail.isNotBlank() && confirmEmail == userEmail
    }

    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Rotas inteligentes, tempo economizado!",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 24.dp),
                        color = MaterialTheme.colorScheme.tertiary
                    )

                    Text(
                        text = "Vamos criar sua conta",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it.trim() },
                        label = { Text("Primeiro nome (ex. João, Maria Alice)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it.trim() },
                        label = { Text("Último nome (ex. Souza, Da Silva)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = userAge,
                        onValueChange = { if (it.all { char -> char.isDigit() }) userAge = it },
                        label = { Text("Informe sua idade") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = userEmail,
                        onValueChange = { userEmail = it.trim() },
                        label = { Text("Digite seu e-mail") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = confirmEmail,
                        onValueChange = { confirmEmail = it.trim() },
                        label = { Text("Confirme seu e-mail") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Você poderá receber um e-mail de verificação para validar sua conta",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (isFormValid) {
                                onEnterClick(UserLogin(firstName, lastName))
                                navController?.navigate("password") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(4.dp),
                        shape = RoundedCornerShape(12.dp),
                        enabled = isFormValid
                    ) {
                        Text(text = "Criar conta")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen()
}
