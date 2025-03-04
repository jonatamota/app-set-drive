import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.setdrive.features.componentes.graficos.BarChart

@Composable
fun BarChartCard(dataList: List<List<Float>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) { Row (modifier = Modifier.fillMaxWidth()
    ) {
        // Título centralizado
        Text(
            text = "Desempenho Semanal",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )}

        // Card com o gráfico
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
                    .padding(4.dp)
            ) {
                // Linha com textos e ícones centralizados
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.Center, // Centraliza o conteúdo na linha
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ícone e texto Realizado (Azul)
                    Row(
                        horizontalArrangement = Arrangement.Center, // Centraliza o ícone e o texto
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "Circle Icon",
                            tint = Color(0xFF6201ED),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Planejado",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    // Espaço entre os itens
                    Spacer(modifier = Modifier.width(16.dp))

                    // Ícone e texto Planejado (Amarelo)
                    Row(
                        horizontalArrangement = Arrangement.Center, // Centraliza o ícone e o texto
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "Circle Icon",
                            tint = Color(0xFF00E676),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Realizado",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
                // Linha para os gráficos de barras
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    dataList.forEach { data ->
                        BarChart(
                            data = data,
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp) // Evita sobreposição
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BarChartCardPreview() {
    BarChartCard(
        dataList = listOf(
            listOf(80f, 30f),
            listOf(60f, 70f),
            listOf(30f, 20f),
            listOf(100f, 110f),
            listOf(50f, 80f)
        )
    )
}
