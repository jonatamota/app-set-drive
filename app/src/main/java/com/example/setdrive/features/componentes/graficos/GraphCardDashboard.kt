package com.example.setdrive.features.componentes.graficos

import ElementosDoCard
import GraphCircleProgress
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.setdrive.R

// Dados dos gráficos
data class GraphDataItem(
    val progress: Float,
    val amount: String,
    val title: String,
    val iconResId: Int
)

// Dados dos elementos (para ElementosDoCard)
data class ElementosDataItem(
    val title: String,
    val amount: String,
    val valor_hora: String,
    val iconResId: Int,
    val secondIconResId: Int
)

@Composable
fun GraphCardDashboard(
    graphDataList: List<GraphDataItem>,
    elementosDataList: List<ElementosDataItem>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Dashboard",
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Primeira linha (2 gráficos e 1 ElementosDoCard)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    graphDataList.take(2).forEach { item ->
                        GraphCircleProgress(
                            progress = item.progress,
                            amount = item.amount,
                            title = item.title,
                            iconResId = item.iconResId
                        )
                    }
                    ElementosDoCard(
                        iconResId = elementosDataList[0].iconResId,
                        title = elementosDataList[0].title,
                        amount = elementosDataList[0].amount,
                        valor_hora = elementosDataList[0].valor_hora,
                        secondIconResId = elementosDataList[0].secondIconResId,
                    )
                }

                // Segunda linha (2 gráficos e 1 ElementosDoCard)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    graphDataList.drop(2).take(2).forEach { item ->
                        GraphCircleProgress(
                            progress = item.progress,
                            amount = item.amount,
                            title = item.title,
                            iconResId = item.iconResId
                        )
                    }
                    ElementosDoCard(
                        iconResId = elementosDataList[1].iconResId,
                        title = elementosDataList[1].title,
                        amount = elementosDataList[1].amount,
                        valor_hora = elementosDataList[1].valor_hora,
                        secondIconResId = elementosDataList[1].secondIconResId,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GraphCardDashboardPreview() {
    GraphCardDashboard(
        graphDataList = listOf(
            GraphDataItem(0.40f, "R$ 100,00", "Planejado", R.drawable.ic_plano_0),
            GraphDataItem(0.10f, "R$ 200,00", "Realizado", R.drawable.ic_moedas_0),
            GraphDataItem(0.80f, "R$ 300,00", "Custos", R.drawable.ic_custo_0),
            GraphDataItem(0.20f, "R$ 400,00", "Líquido", R.drawable.ic_liquido_din_0)
        ),
        elementosDataList = listOf(
            ElementosDataItem("Rodagem", "R$ 500,00", "900 COR", R.drawable.ic_acompanhar_0, R.drawable.ic_carro_frente_1),
            ElementosDataItem("Horas", "6:00", "R$ 32,00", R.drawable.ic_relogio_0, R.drawable.ic_relogio_1)
        )
    )
}

