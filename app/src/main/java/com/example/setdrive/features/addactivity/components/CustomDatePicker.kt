import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.setdrive.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomDatePicker() {
    var date by remember { mutableStateOf(LocalDate.now()) }
    val context = LocalContext.current

    // Exibindo o ícone, data e o botão na mesma linha
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically // Alinha verticalmente todos os itens
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_calendario_1),
            contentDescription = "Ícone de calendário",
            modifier = Modifier
                .size(28.dp) // Tamanho ajustado para um melhor equilíbrio visual
                .padding(end = 8.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        // Formatação da data no formato 'seg., 18 fev. 2015'
        val formattedDate = date.format(
            DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy", Locale("pt", "BR"))
        )

        // A data agora está centralizada e visível
        Text(
            text = formattedDate,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onBackground, // Garante visibilidade correta
            fontSize = 20.sp
        )

        // Botão para abrir o DatePickerDialog
        TextButton(
            onClick = {
                DatePickerDialog(context, { _, year, month, dayOfMonth ->
                    date = LocalDate.of(year, month + 1, dayOfMonth)
                }, date.year, date.monthValue - 1, date.dayOfMonth).show()
            }
        ) {
            Text(
                "Alterar data",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CustomDatePickerPreview() {
    CustomDatePicker()
}
