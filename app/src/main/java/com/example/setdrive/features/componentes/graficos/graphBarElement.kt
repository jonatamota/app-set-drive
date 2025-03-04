package com.example.setdrive.features.componentes.graficos

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BarChart(data: List<Float>, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Canvas(
                modifier = Modifier
                    .width(60.dp)  // Aumentei a largura total
                    .height(100.dp)
            ) {
                val barSpacing = size.width / (data.size * 1.2f)  // Reduzi ainda mais o espaçamento
                val barWidth = barSpacing * 0.8f  // Aumentei a largura das barras
                val maxValue = data.maxOrNull() ?: 1f
                val cornerRadius = 12f  // Raio dos cantos arredondados

                data.forEachIndexed { index, value ->
                    val barHeight = (value / maxValue) * size.height
                    val xOffset = index * barSpacing * 1.2f + (barSpacing - barWidth) / 2

                    // Criar um path para a barra com cantos arredondados
                    val path = Path().apply {
                        addRoundRect(
                            RoundRect(
                                left = xOffset,
                                top = size.height - barHeight,
                                right = xOffset + barWidth,
                                bottom = size.height,
                                radiusX = cornerRadius,
                                radiusY = cornerRadius
                            )
                        )
                    }

                    // Desenhar a barra usando o path
                    drawPath(
                        path = path,
                        color = if (index == 1) Color(0xFF00E676) else Color(0xFF6201ED),
                        style = Fill
                    )
                }
            }

            Text("Semana", fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BarChartPreview() {
    BarChart(
        data = listOf(40f, 100f),
        modifier = Modifier.padding(4.dp)
    )
}