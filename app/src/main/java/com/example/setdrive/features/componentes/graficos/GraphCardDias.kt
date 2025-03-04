import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThermometerBar(
    value: Float,  // Valor atual da barra
    maxValue: Float,  // Valor máximo de referência
    label: String,  // Rótulo do termômetro
    height: Int = 150  // Altura padrão da barra
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        // Container do termômetro
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(height.dp)
                .clip(RoundedCornerShape(8.dp)) // Arredondamento das bordas
                .background(MaterialTheme.colorScheme.surfaceVariant) // Cor do fundo
        ) {
            // Barra de progresso do termômetro (preenchida de baixo para cima)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Alinhado à parte inferior do container
                    .fillMaxWidth()
                    .fillMaxHeight(value / maxValue) // Proporção de preenchimento com base no valor
                    .clip(RoundedCornerShape(8.dp)) // Arredondamento da barra
                    .background( // Define a cor da barra com base no valor
                        when {
                            value / maxValue < 0.90f -> Color(0x90FF9100) // Verde (abaixo de 90%)
                            value / maxValue < 0.5f -> Color(0xFFFF0707) // Amarelo (abaixo de 50%)
                            else -> Color(0xFF00E676) // Vermelho (acima de 90%)
                        }
                    )
            )
        }

        Spacer(modifier = Modifier.height(4.dp)) // Espaço entre a barra e o texto

        // Exibe o valor numérico
        Text(
            text = String.format("%.0f", value), // Formata o valor sem casas decimais
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Exibe o rótulo do dia da semana
        Text(
            text = label,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(50.dp)
        )
    }
}

@Composable
fun GraphCardDias(dataList: List<List<Float>>) {
    val diasSemana = listOf("Seg", "Ter", "Qua", "Qui", "Sex") // Lista de dias da semana
    val maxValue = dataList.flatten().maxOrNull() ?: 100f // Obtém o maior valor da lista para escala

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        // Título do card
        Row(
            modifier = Modifier
        ) {
            Text(
                text = "Última semana",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        // Card contendo os termômetros
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Linha contendo os gráficos de cada dia
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly, // Distribui os elementos de forma equilibrada
                    verticalAlignment = Alignment.Bottom
                ) {
                    dataList.forEachIndexed { index, values ->
                        if (index < diasSemana.size) { // Garante que não ultrapasse os dias da semana
                            ThermometerBar(
                                value = values.firstOrNull() ?: 0f, // Usa o primeiro valor da lista ou 0 se estiver vazia
                                maxValue = maxValue,
                                label = diasSemana[index]
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GraphCardDiasPreview() {
    MaterialTheme {
        GraphCardDias(
            dataList = listOf(
                listOf(20f), // Valor para segunda-feira
                listOf(60f), // Terça-feira
                listOf(80f), // Quarta-feira
                listOf(100f), // Quinta-feira
                listOf(40f) // Sexta-feira
            )
        )
    }
}
