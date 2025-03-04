package com.example.setdrive.features.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.R
import com.example.setdrive.features.User.UserLogin

@Composable
fun LoginScreen(
    navController: NavController? = null,
    onEnterClick: (UserLogin) -> Unit = {}
) {
    var userEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val isFormValid = userEmail.isNotBlank() && password.isNotBlank()

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baleia_img),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "SetDrive",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Gerencie tudo em um só lugar!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp),
                color = MaterialTheme.colorScheme.tertiary,
            )

            Text(
                text = "Entre ou crie sua conta",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                label = { Text(text = "Insira seu e-mail") },
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email_0),
                        contentDescription = "Ícone de email",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Insira sua senha") },
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_senha),
                        contentDescription = "Ícone de senha",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(if (passwordVisible) R.drawable.baixa_visao else R.drawable.baixa_visao_1),
                            contentDescription = "Alternar visibilidade da senha",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )

            TextButton(
                onClick = { /* Implementar lógica para recuperação de senha */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Esqueci minha senha", color = MaterialTheme.colorScheme.primary)
            }

            Text(
                text = "Ao entrar, você concorda com os Termos e Condições de uso. Os termos também estarão disponíveis para consulta na aba conta do aplicativo.",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall
            )

            Button(
                onClick = {
                    println("Dados de login: Email = $userEmail, Senha = $password")
                    onEnterClick(UserLogin(userEmail, password))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = isFormValid
            ) {
                Text(text = "Entrar", fontSize = MaterialTheme.typography.bodyLarge.fontSize)
            }

            OutlinedButton(
                onClick = {
                    navController?.navigate("registro")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Criar uma conta", fontSize = MaterialTheme.typography.bodyLarge.fontSize)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
