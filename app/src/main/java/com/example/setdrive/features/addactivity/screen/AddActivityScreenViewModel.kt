package com.example.setdrive.features.addactivity.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class AddActivityScreenViewModel : ViewModel() {
    var faturamentoBruto by mutableStateOf("")
    var numCorridas by mutableStateOf("")
    var kmRodados by mutableStateOf("")
    var horasTrabalhadas by mutableStateOf("")
    var custo by mutableStateOf("")
    var faturamentoLiquido by mutableStateOf("")
}
@RequiresApi(Build.VERSION_CODES.O)
fun AddActivityScreenViewModel.getCurrentDate(): String {
    val today = java.time.LocalDate.now()
    return today.toString() // Formate conforme necessário
}