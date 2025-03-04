package com.example.setdrive.features.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.setdrive.features.User.UserLogin

@Composable
fun PasswordScreen(navController: NavController? = null) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(false) }

    // Atualiza a validação do formulário
    LaunchedEffect(password, confirmPassword) {
        isFormValid = password.trim().length >= 6 && confirmPassword.trim() == password.trim()
    }

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Quase lá, hora de criar sua senha!",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 24.dp),
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "Crie uma senha que só você sabe",
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                // Campo da senha
                OutlinedTextField(
                    value = password,
                    onValueChange = { newValue -> password = newValue },
                    label = { Text(text = "Crie uma senha") },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Gray
                    ),
                    isError = password.isNotEmpty() && password.length < 6
                )

                // Mensagem de erro para senha curta
                if (password.isNotEmpty() && password.length < 6) {
                    Text(
                        text = "A senha deve ter pelo menos 6 caracteres",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Confirmação da senha
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { newValue -> confirmPassword = newValue },
                    label = { Text(text = "Confirme sua senha") },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    isError = confirmPassword.isNotEmpty() && confirmPassword != password
                )

                // Mensagem de erro se as senhas não coincidirem
                if (confirmPassword.isNotEmpty() && confirmPassword != password) {
                    Text(
                        text = "As senhas não coincidem",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = "Conformo que li os Termos e Condiçoes de Uso incluindo a Política de Privacidade, e ao seguir para finalizar confirmo que estou de acordo",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )

                // Botão de finalização
                Button(
                    onClick = {
                        navController?.navigate("home")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(4.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled = isFormValid
                ) {
                    Text(text = "Finalizar",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordScreen() {
    PasswordScreen()
}

