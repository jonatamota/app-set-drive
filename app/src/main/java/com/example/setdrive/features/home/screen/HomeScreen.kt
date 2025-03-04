package com.example.setdrive.features.account.screen

import BarChartCard
import GraphCardDias
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.R
import com.example.setdrive.features.componentes.graficos.ElementosDataItem
import com.example.setdrive.features.componentes.graficos.GraphCardDashboard
import com.example.setdrive.features.componentes.graficos.GraphDataItem
import com.example.setdrive.features.planning.componentes.BottomCalculer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start // Alinhando o ícone à esquerda
                    ) {
                        // Ícone de Perfil Redondo
                        Icon(
                            imageVector = Icons.Default.Person, // Ícone de perfil
                            contentDescription = "Perfil",
                            modifier = Modifier
                                .clip(CircleShape) // Tornar o ícone redondo
                                .background(MaterialTheme.colorScheme.surface) // Cor de fundo
                                .padding(8.dp) // Adicionando padding
                                .clickable {
                                    // Navegar para a tela de conta ao clicar no ícone
                                    navController.navigate("conta")
                                },
                            tint = MaterialTheme.colorScheme.primary // Cor do ícone
                        )
                        Spacer(modifier = Modifier.width(20.dp)) // Espaço entre o ícone e o título
                        Text(
                            text = "Desempenho de Março",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background // Definindo a cor de fundo da barra
                )
            )
        },

        // Sessão para criar atividade

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Navegar para a tela de adicionar atividade
                    navController.navigate("addscreen")
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar nova atividade",
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            item {
                // Componente de cálculos gerais
                BottomCalculer()

                Column(modifier = Modifier.padding(8.dp)) {
                    // Gráfico de progresso
                    GraphCardDashboard(
                        graphDataList = listOf(
                            GraphDataItem(0.40f, "R$ 100,00", "Planejado", R.drawable.ic_plano_0),
                            GraphDataItem(0.10f, "R$ 200,00", "Realizado", R.drawable.ic_moedas_0),
                            GraphDataItem(0.80f, "R$ 300,00", "Custos", R.drawable.ic_custo_0),
                            GraphDataItem(0.20f, "R$ 400,00", "Líquido", R.drawable.ic_liquido_din_0)
                        ),
                        elementosDataList = listOf(
                            ElementosDataItem("Rodagem", "100 KM", "1000 COR", R.drawable.ic_acompanhar_0, R.drawable.ic_carro_frente_1),
                            ElementosDataItem("Horas", "6:00", "R$ 900,00", R.drawable.ic_relogio_0, R.drawable.ic_relogio_1)
                        )
                    )

                    // Gráfico de barras
                    BarChartCard(
                        dataList = listOf(
                            listOf(40f, 50f),
                            listOf(60f, 70f),
                            listOf(80f, 90f),
                            listOf(100f, 110f)
                        )
                    )

                    // Gráfico de dias
                    GraphCardDias(
                        dataList = listOf(
                            listOf(20f, 30f),
                            listOf(60f, 70f),
                            listOf(80f, 90f),
                            listOf(100f, 110f),
                            listOf(10f, 80f)
                        )
                    )

                    // Espaço adicional para preencher
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val fakeNavController = rememberNavController()
    HomeScreen(navController = fakeNavController)
}
