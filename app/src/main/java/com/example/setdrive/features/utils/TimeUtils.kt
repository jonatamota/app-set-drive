package com.example.setdrive.features.utils

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Float.formatAsTime(): String {
    if (this < 0) return "00:00" // Caso o valor seja negativo, retorna um valor padrão
    val hours = this.toInt()
    val minutes = ((this - hours) * 60).toInt()
    return String.format("%02d:%02d", hours, minutes)
}
