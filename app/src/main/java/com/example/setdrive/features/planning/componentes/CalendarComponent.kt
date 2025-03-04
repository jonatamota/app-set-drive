package com.example.setdrive.features.planning.componentes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarComponent() {
    var anoAtual by remember { mutableStateOf(LocalDate.now().year) }
    var mesAtual by remember { mutableStateOf(LocalDate.now().monthValue) }

    val primeiroDiaDoMes = remember(mesAtual, anoAtual) {
        LocalDate.of(anoAtual, mesAtual, 1).dayOfWeek.value
    }

    val diasDoMes = remember(mesAtual, anoAtual) {
        (1..YearMonth.of(anoAtual, mesAtual).lengthOfMonth()).toList()
    }

    val diasComEspacos = remember(primeiroDiaDoMes, diasDoMes) {
        val espacosVazios = MutableList(primeiroDiaDoMes - 1) { "" }
        espacosVazios + diasDoMes
    }

    val diasCompletos = remember(diasComEspacos) {
        val resto = diasComEspacos.size % 7
        if (resto != 0) {
            diasComEspacos + MutableList(7 - resto) { "" }
        } else {
            diasComEspacos
        }
    }

    val valores = remember { mutableStateMapOf<String, String>() }

    val corPrimaria = MaterialTheme.colorScheme.primary
    val corLightGray = Color(0xFFD3D3D3)
    val corBordaAzul = MaterialTheme.colorScheme.primary
    val corFeriado = Color.Red
    val corPontoFacultativo = Color(0xFF5D6DDA)

    val diaAtual = LocalDate.now().dayOfMonth
    val mesAtualHoje = LocalDate.now().monthValue
    val anoAtualHoje = LocalDate.now().year

    val focusRequester = FocusRequester()

    fun mesNome(mes: Int): String {
        return listOf(
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio",
            "Junho",
            "Julho",
            "Agosto",
            "Setembro",
            "Outubro",
            "Novembro",
            "Dezembro"
        )[mes - 1]
    }

    val holidays = mapOf(
        LocalDate.of(2025, 1, 1) to "Ano Novo",
        LocalDate.of(2025, 3, 29) to "Sexta-feira Santa",
        LocalDate.of(2025, 4, 21) to "Tiradentes",
        LocalDate.of(2025, 5, 1) to "Dia do Trabalho",
        LocalDate.of(2025, 9, 7) to "Independência do Brasil",
        LocalDate.of(2025, 10, 12) to "Nossa Senhora Aparecida",
        LocalDate.of(2025, 11, 2) to "Finados",
        LocalDate.of(2025, 11, 15) to "Proclamação da República",
        LocalDate.of(2025, 11, 20) to "Dia da Consciência Negra",
        LocalDate.of(2025, 12, 25) to "Natal"
    )

    val pontosFacultativos = mapOf(
        LocalDate.of(2025, 3, 3) to "Carnaval",
        LocalDate.of(2025, 3, 4) to "Carnaval",
        LocalDate.of(2025, 3, 5) to "Quarta-feira de Cinzas",
        LocalDate.of(2025, 6, 5) to "Corpos Christi",
        LocalDate.of(2025, 12, 24) to "Véspera de Natal",
        LocalDate.of(2025, 12, 31) to "Véspera de Ano Novo",
    )

    fun feriadosEPontosFacultativosDoMes(ano: Int, mes: Int): List<String> {
        val feriados =
            holidays.filterKeys { it.year == ano && it.monthValue == mes }.values.toList()
        val pontosFacultativos =
            pontosFacultativos.filter { it.key.year == ano && it.key.monthValue == mes }.values.toList()
        return feriados + pontosFacultativos
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp, 6.dp, 2.dp, 2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Cabeçalho com nome do mês e setas para navegação
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                if (mesAtual == 1) {
                    mesAtual = 12; anoAtual--
                } else mesAtual--
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleLeft,
                    contentDescription = "Mês anterior",
                    tint = corPrimaria
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${mesNome(mesAtual)} $anoAtual",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = corPrimaria
                )
                feriadosEPontosFacultativosDoMes(anoAtual, mesAtual).forEach { evento ->
                    val corEvento = when {
                        pontosFacultativos.values.contains(evento) -> corPontoFacultativo
                        else -> corFeriado
                    }
                    Text(
                        text = evento,
                        fontSize = 14.sp,
                        color = corEvento,
                    )
                }
            }
            IconButton(onClick = {
                if (mesAtual == 12) {
                    mesAtual = 1; anoAtual++
                } else mesAtual++
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleRight,
                    contentDescription = "Próximo mês",
                    tint = corPrimaria
                )
            }
        }

        // Adiciona os dias da semana
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom").forEach { diaSemana ->
                Text(
                    text = diaSemana,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Dias do mês
        diasCompletos.chunked(7).forEach { semana ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                semana.forEach { dia ->
                    val dataAtual =
                        LocalDate.of(anoAtual, mesAtual, dia.toString().toIntOrNull() ?: 1)
                    val isHoje =
                        dia == diaAtual && mesAtual == mesAtualHoje && anoAtual == anoAtualHoje
                    val isFeriado = holidays.containsKey(dataAtual)
                    val isPontoFacultativo = pontosFacultativos.containsKey(dataAtual)

                    val isFocado = remember { mutableStateOf(false) }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (dia is Int) {
                            Text(
                                text = dia.toString(),
                                color = when {
                                    isFeriado -> corFeriado
                                    isPontoFacultativo -> corPontoFacultativo
                                    isHoje -> corPrimaria
                                    else -> MaterialTheme.colorScheme.onBackground
                                },
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                            // Borda do box que segue o campo de texto
                            Box(
                                modifier = Modifier
                                    .height(30.dp)
                                    .fillMaxWidth()
                                    .border(
                                        2.dp,
                                        if (isFocado.value) corBordaAzul else MaterialTheme.colorScheme.surface,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colorScheme.surface)
                                    .onFocusChanged {
                                        isFocado.value = it.hasFocus
                                    }
                                    .focusRequester(focusRequester)
                            ) {
                                BasicTextField(
                                    value = valores[dia.toString()] ?: "",
                                    onValueChange = {
                                        if (it.all { char -> char.isDigit() } && it.length <= 4) { // Permite apenas números e até 4 caracteres
                                            valores[dia.toString()] = it
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                    textStyle = TextStyle(
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.onBackground
                                    ),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalendarComponentPreview() {
    CalendarComponent()
}