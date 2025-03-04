package com.example.setdrive.features.account.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.features.componentes.menu.CustomTopBar

@Composable
fun AccountScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "Detalhes da Conta")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Lista de opções de menu
                val menuOptions = listOf(
                    MenuOptionData("Atualizar meus dados", Icons.Default.Person),
                    MenuOptionData("Exclusão de conta", Icons.Default.Delete),
                    MenuOptionData("Termos e Condições de uso", Icons.Default.Description),
                    MenuOptionData("Ajuda", Icons.Default.Help),
                    MenuOptionData("Aparência do aplicativo", Icons.Default.Tonality),
                    MenuOptionData("Notificações", Icons.Default.Notifications),
                    MenuOptionData("Sobre o aplicativo", Icons.Default.PermDeviceInformation)
                )

                // Exibição das opções de menu
                items(menuOptions) { menuOption ->
                    MenuOption(
                        title = menuOption.title,
                        icon = menuOption.icon,
                        onClick = { navController.navigate("home") }
                    )
                }

                // Botão de Sair da Conta dentro da LazyColumn
                item {
                    Button(
                        onClick = {
                            navController.navigate("login") {
                                popUpTo("account") { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Sair da conta")
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuOption(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = icon, contentDescription = null)
                Text(text = title)
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigate"
            )
        }
    }
}

data class MenuOptionData(val title: String, val icon: ImageVector)

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen(navController = rememberNavController())
}
