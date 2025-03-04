import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.setdrive.R
import java.util.*

@Composable
fun FilterCard() {
    // Estado para os filtros
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var hoursWorked by remember { mutableStateOf("") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Função para exibir o DatePicker
    val showDatePickerDialog = { onDateSelected: (String) -> Unit ->
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedDate = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    // Layout com os filtros
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
    ) {
        // Row para colocar os campos de data na mesma linha
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Filtro para a Data Inicial
            OutlinedTextField(
                value = startDate,
                onValueChange = { startDate = it },
                label = { Text("Data Inicial") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                shape = RoundedCornerShape(12.dp), // Arredonda os cantos
                trailingIcon = {
                    IconButton(onClick = { showDatePickerDialog { startDate = it } }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_today),
                            contentDescription = "Selecionar Data Inicial",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )

            // Filtro para a Data Final
            OutlinedTextField(
                value = endDate,
                onValueChange = { endDate = it },
                label = { Text("Data Final") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                shape = RoundedCornerShape(12.dp), // Arredonda os cantos
                trailingIcon = {
                    IconButton(onClick = { showDatePickerDialog { endDate = it } }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_today),
                            contentDescription = "Selecionar Data Final",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Row para colocar as Horas Trabalhadas e o Botão na mesma linha
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Filtro para Horas Trabalhadas
            OutlinedTextField(
                value = hoursWorked,
                onValueChange = { hoursWorked = it },
                label = { Text("Horas") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp), // Arredonda os cantos
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            // Botão de Aplicar Filtro
            Button(
                onClick = {
                    // Lógica para aplicar os filtros
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface, // Fundo na cor de superfície
                    contentColor = MaterialTheme.colorScheme.primary // Ícones e texto na cor primária
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary), // Borda na cor primária
                contentPadding = PaddingValues(horizontal = 2.dp),
                modifier = Modifier
                    .padding(6.dp)
                    .weight(1f)
                    .height(58.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filtros_0),
                    contentDescription = "filtro",
                    modifier = Modifier.size(26.dp).padding(horizontal = 2.dp),
                    tint = MaterialTheme.colorScheme.primary // Ícone na cor primária
                )

                Text(
                    text = "Aplicar Filtro",
                    fontSize = 16.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 2.dp),
                    color = MaterialTheme.colorScheme.primary // Texto na cor primária
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterCardPreview() {
    FilterCard()
}
