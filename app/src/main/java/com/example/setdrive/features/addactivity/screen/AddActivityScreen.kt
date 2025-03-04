package com.example.setdrive.features.addactivity.screen

import CustomDatePicker
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.R
import com.example.setdrive.features.componentes.menu.CustomTopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddActivityScreen(navController: NavController, modifier: Modifier = Modifier) {
    var annotation by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Adicionar Nova Atividade",
                showBackButton = true, // Exibe o botão de voltar apenas nessa tela
                onBackPress = { navController.popBackStack() } // Lógica para voltar à tela anterior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CustomDatePicker()
            }

            item {
                AddActivityCard()
            }

            item {
                OutlinedTextField(
                    value = annotation,
                    onValueChange = { annotation = it },
                    label = { Text("Adicione uma nota aqui") },
                    modifier = Modifier
                        .fillMaxWidth(0.935f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                Button(
                    onClick = { /* Lógica para salvar a anotação */ },
                    modifier = Modifier
                        .fillMaxWidth(0.935f)
                        .height(60.dp)
                        .padding(6.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Salvar", fontSize = 20.sp)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddActivityScreenPreview() {
    val navController = rememberNavController()
    AddActivityScreen(navController = navController)
}
