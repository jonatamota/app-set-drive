package com.example.setdrive.features.activities.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.R


@Composable
fun ActivityCard(navController: NavController) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Linha contendo a data e o ícone de menu dentro do Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 6.dp,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DD/MM/AAAA",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_pontos_de_menu),
                    contentDescription = "Mais opções",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(20.dp)
                        .clickable { isExpanded = !isExpanded }
                )
            }

            // Linha principal do card com as informações do dia
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Primeira coluna
                Column(modifier = Modifier.weight(1.5f)) {
                    // Faturamento Bruto
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fatura_0),
                                contentDescription = "Faturamento Bruto",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Faturamento Bruto",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Text(
                            text = "R$ 1.500,00", // Exemplo de valor
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold, // Valor em negrito
                            modifier = Modifier.padding(start = 24.dp) // Alinhar com o ícone
                        )
                    }

                    // Número de Corridas
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_corridas_0),
                                contentDescription = "Número de Corridas",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Número de Corridas",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Text(
                            text = "25", // Exemplo de valor
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold, // Valor em negrito
                            modifier = Modifier.padding(start = 24.dp) // Alinhar com o ícone
                        )
                    }

                    // Horas Trabalhadas
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_relogio_0),
                                contentDescription = "Horas Trabalhadas",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Horas Trabalhadas",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Text(
                            text = "8h 30min", // Exemplo de valor
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold, // Valor em negrito
                            modifier = Modifier.padding(start = 24.dp) // Alinhar com o ícone
                        )
                    }
                }

                // Segunda coluna
                Column(modifier = Modifier.weight(1f)) {
                    // KM Rodado
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_acompanhar_0),
                                contentDescription = "KM Rodado",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "KM Rodado",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Text(
                            text = "1.777 km", // Exemplo de valor
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold, // Valor em negrito
                            modifier = Modifier.padding(start = 24.dp) // Alinhar com o ícone
                        )
                    }

                    // Custo
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_custo_0),
                                contentDescription = "Custo",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Custo",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Text(
                            text = "R$ 300,00", // Exemplo de valor
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold, // Valor em negrito
                            modifier = Modifier.padding(start = 24.dp) // Alinhar com o ícone
                        )
                    }

                    // Líquido
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_liquido_din_0),
                                contentDescription = "Líquido",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Líquido",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Text(
                            text = "R$ 1.200,00", // Exemplo de valor
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold, // Valor em negrito
                            modifier = Modifier.padding(start = 24.dp) // Alinhar com o ícone
                        )
                    }
                }
            }

            // Botões que aparecem ao expandir - na mesma linha com texto completo
            AnimatedVisibility(visible = isExpanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Botão Editar
                    Button(
                        onClick = { navController.navigate("editaractivity") },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_editar_0),
                            contentDescription = "Ícone de editar",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Editar",
                            fontSize = 12.sp,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }

                    // Botão Apagar
                    Button(
                        onClick = { /* ação apagar */ },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lixeira_x),
                            contentDescription = "Ícone de apagar",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Apagar",
                            fontSize = 12.sp,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }

                    // Botão Ver Nota
                    Button(
                        onClick = { /* ação ver nota */ },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_comente_0),
                            contentDescription = "Ícone Anotação",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Ver Nota",
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityCardPreview() {
    ActivityCard(navController = rememberNavController())
}
