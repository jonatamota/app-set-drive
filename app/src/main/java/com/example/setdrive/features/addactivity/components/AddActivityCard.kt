package com.example.setdrive.features.addactivity.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.setdrive.R
import com.example.setdrive.features.addactivity.components.ElementCard
import com.example.setdrive.features.addactivity.components.NumberFormat
import com.example.setdrive.features.addactivity.viewmodel.AddActivityScreenViewModel

@Composable
fun AddActivityCard(viewModel: AddActivityScreenViewModel = AddActivityScreenViewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Cabeçalho
            Text(
                text = "Registro de atividade",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )

            // Cards internos com dados
            ElementCard(
                title = "Faturamento Bruto",
                value = viewModel.faturamentoBruto,
                numberFormat = NumberFormat.Currency, // Formato de moeda
                cardColor = MaterialTheme.colorScheme.primary,
                titleColor = MaterialTheme.colorScheme.onPrimary,
                onValueChange = { viewModel.faturamentoBruto = it },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fatura_0),
                        contentDescription = "Faturamento Bruto",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
            ElementCard(
                title = "Número de Corridas",
                value = viewModel.numCorridas,
                numberFormat = NumberFormat.Integer, // Formato inteiro
                cardColor = MaterialTheme.colorScheme.primary,
                titleColor = MaterialTheme.colorScheme.onPrimary,
                onValueChange = { viewModel.numCorridas = it },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_corridas_0),
                        contentDescription = "Número de Corridas",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
            ElementCard(
                title = "Quilômetros Rodados",
                value = viewModel.kmRodados,
                numberFormat = NumberFormat.Decimal, // Formato decimal
                cardColor = MaterialTheme.colorScheme.primary,
                titleColor = MaterialTheme.colorScheme.onPrimary,
                onValueChange = { viewModel.kmRodados = it },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_acompanhar_0),
                        contentDescription = "Quilômetros Rodados",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
            ElementCard(
                title = "Horas Trabalhadas",
                value = viewModel.horasTrabalhadas,
                numberFormat = NumberFormat.Decimal, // Formato decimal
                cardColor = MaterialTheme.colorScheme.primary,
                titleColor = MaterialTheme.colorScheme.onPrimary,
                onValueChange = { viewModel.horasTrabalhadas = it },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_relogio_0),
                        contentDescription = "Horas Trabalhadas",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
            ElementCard(
                title = "Custo Total",
                value = viewModel.custo,
                numberFormat = NumberFormat.Currency, // Formato de moeda
                cardColor = MaterialTheme.colorScheme.primary,
                titleColor = MaterialTheme.colorScheme.onPrimary,
                onValueChange = { viewModel.custo = it },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_custo_0),
                        contentDescription = "Custo Total",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
            ElementCard(
                title = "Faturamento Líquido",
                value = viewModel.faturamentoLiquido,
                numberFormat = NumberFormat.Currency, // Formato de moeda
                cardColor = MaterialTheme.colorScheme.primary,
                titleColor = MaterialTheme.colorScheme.onPrimary,
                onValueChange = { viewModel.faturamentoLiquido = it },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_liquido_din_0),
                        contentDescription = "Faturamento Líquido",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddActivityCardPreview(viewModel: AddActivityScreenViewModel = AddActivityScreenViewModel()) {
    AddActivityCard()
}
