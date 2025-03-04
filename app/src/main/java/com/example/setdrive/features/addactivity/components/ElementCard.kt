package com.example.setdrive.features.addactivity.components

import CustomOutlinedTextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.setdrive.R

sealed class NumberFormat {
    object Currency : NumberFormat()    // Para valores monetários (R$ 1.234,00)
    object Integer : NumberFormat()     // Para números inteiros (1234)
    object Decimal : NumberFormat()     // Para números decimais (1234,5)
}

@Composable
fun ElementCard(
    title: String,
    value: String,
    numberFormat: NumberFormat = NumberFormat.Currency,
    cardColor: Color = MaterialTheme.colorScheme.primary,
    titleColor: Color = MaterialTheme.colorScheme.onPrimary,
    onValueChange: (String) -> Unit,
    icon: @Composable (() -> Unit)? = null,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp)
) {
    var isFocused by remember { mutableStateOf(false) }

    fun formatValue(input: String, focused: Boolean): String {
        // Se estiver focado, mostra apenas os números (e vírgula para decimal)
        if (focused) {
            return when (numberFormat) {
                is NumberFormat.Integer -> input.replace(Regex("[^0-9]"), "")
                is NumberFormat.Currency, is NumberFormat.Decimal -> input.replace(Regex("[^0-9,]"), "")
            }
        }

        // Se não estiver focado, aplica a formatação apropriada
        val cleanedInput = input.replace(Regex("[^0-9]"), "")
        if (cleanedInput.isEmpty()) return ""

        return when (numberFormat) {
            is NumberFormat.Currency -> {
                // Formata como moeda (sempre com 2 casas decimais)
                "$cleanedInput,00"
            }
            is NumberFormat.Integer -> {
                // Mantém apenas números inteiros
                cleanedInput
            }
            is NumberFormat.Decimal -> {
                // Permite números decimais com vírgula
                if (input.contains(",")) {
                    val parts = input.split(",")
                    val integerPart = parts[0].replace(Regex("[^0-9]"), "")
                    val decimalPart = parts.getOrNull(1)?.replace(Regex("[^0-9]"), "")?.take(2) ?: ""
                    if (decimalPart.isEmpty()) integerPart else "$integerPart,$decimalPart"
                } else {
                    cleanedInput
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(48.dp), // Altura fixa para o Row
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Card com ícone e título
        Card(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight(), // Ocupa a altura total do Row
            colors = CardDefaults.cardColors(
                containerColor = cardColor
            ),
            shape = shape
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(12.dp)
            ) {
                icon?.invoke()
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    color = titleColor,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // CustomOutlinedTextField
        CustomOutlinedTextField(
            value = formatValue(value, isFocused),
            onValueChange = { newValue ->
                onValueChange(newValue)
            },
            label = null,
            textColor = MaterialTheme.colorScheme.primary,
            fontSize = 26,
            backgroundColor = Color.Transparent,
            borderColor = MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            labelColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(), // Adiciona espaço à esquerda, // Ocupa a altura total do Row
            maxLength = null,
            onFocusChanged = { focusState ->
                isFocused = focusState.isFocused
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number // Habilita apenas teclado numérico
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ElementCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var value1 by remember { mutableStateOf("250") }
        ElementCard(
            title = "Faturamento Bruto",
            value = value1,
            cardColor = MaterialTheme.colorScheme.primary,
            titleColor = MaterialTheme.colorScheme.onPrimary,
            onValueChange = { value1 = it },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fatura_0),
                    contentDescription = "Ícone customizado",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
    }
}