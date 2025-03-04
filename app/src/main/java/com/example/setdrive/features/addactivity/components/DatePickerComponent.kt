package com.example.setdrive.features.addactivity.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.setdrive.R
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent() {
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(getCurrentDate()) }
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { showDatePicker = true }
                .padding(8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendario_0),
                contentDescription = "icone de calendário",
                modifier = Modifier.size(22.dp), // Aumentado para melhor visibilidade
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = selectedDate,
                fontSize = 18.sp,
                color = Color.Unspecified
            )
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
                        selectedDate = sdf.format(Date(datePickerState.selectedDateMillis ?: 0L))
                        showDatePicker = false
                    }) {
                        Text("OK")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
    return sdf.format(Date())
}

@Preview(showBackground = true)
@Composable
fun PreviewDatePicker() {
    DatePickerComponent()
}
