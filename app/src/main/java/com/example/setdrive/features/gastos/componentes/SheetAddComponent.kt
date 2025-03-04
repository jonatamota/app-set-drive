import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseSheetComponent(
    onDismiss: () -> Unit = {},
    onSave: (String, Double, String, String, Int) -> Unit = { _, _, _, _, _ -> }
) {
    var nomeDespesa by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var dataVencimento by remember {
        mutableStateOf(
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        )
    }
    var tipoDespesa by remember { mutableStateOf("Eventual") }
    var numeroVezes by remember { mutableStateOf(1) }
    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                value = nomeDespesa,
                onValueChange = { nomeDespesa = it },
                label = { Text("Nome da despesa") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = valor,
                onValueChange = { valor = it },
                label = { Text("Valor") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = { showDatePicker = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Data de Vencimento: $dataVencimento")
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val selectedDate = Instant.ofEpochMilli(millis)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                                dataVencimento =
                                    selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            }
                            showDatePicker = false
                        }) {
                            Text("OK")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { tipoDespesa = if (tipoDespesa == "Eventual") "Fixa" else "Eventual" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = tipoDespesa)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Número de vezes: $numeroVezes")
                Row {
                    Button(
                        onClick = { if (numeroVezes > 1) numeroVezes-- },
                        modifier = Modifier.width(50.dp)
                    ) {
                        Text("-")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { if (numeroVezes < 12) numeroVezes++ },
                        modifier = Modifier.width(50.dp)
                    ) {
                        Text("+")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val valorNumerico = valor.toDoubleOrNull() ?: 0.0
                    if (nomeDespesa.isNotBlank() && valorNumerico > 0) {
                        onSave(nomeDespesa, valorNumerico, dataVencimento, tipoDespesa, numeroVezes)
                        onDismiss()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar")
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddExpenseSheetComponentPreview() {
    MaterialTheme {
        AddExpenseSheetComponent()
    }
}