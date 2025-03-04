package com.example.setdrive.features.activities.ActivityScreen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.features.addactivity.screen.AddActivityCard
import com.example.setdrive.features.componentes.menu.CustomTopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditionScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var faturamentoBruto by remember { mutableStateOf(0.0f) }
    var numCorridas by remember { mutableStateOf(0) }
    var kmRodados by remember { mutableStateOf(0.0f) }
    var horasTrabalhadas by remember { mutableStateOf(0.0f) }
    var custo by remember { mutableStateOf(0.0f) }
    var faturamentoLiquido by remember { mutableStateOf(0.0f) }
    var annotation by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Editar Atividade",
                showBackButton = true,
                onBackPress = { navController.popBackStack() }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                item {
//                    CustomDatePicker()
//                }

                item {
                    AddActivityCard()
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.935f)
                            .padding(8.dp)
                    ) {
                        OutlinedTextField(
                            value = annotation,
                            onValueChange = { annotation = it },
                            label = { Text("Sua nota de atividade") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                // Implementar lógica de salvar a atividade
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Salvar Alteração", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EditionScreenPreview() {
    EditionScreen(
        navController = rememberNavController(),
    )
}
