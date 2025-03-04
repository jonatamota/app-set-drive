package com.example.setdrive.features.componentes.menu

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val iconRes: Int, // Ícone para estado não selecionado
    @DrawableRes val selectedIconRes: Int, // Ícone para estado selecionado
)
