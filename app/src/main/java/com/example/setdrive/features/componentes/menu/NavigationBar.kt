package com.example.setdrive.features.componentes.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.R.drawable.ic_calendario_bar_0
import com.example.setdrive.R.drawable.ic_calendario_bar_1
import com.example.setdrive.R.drawable.ic_casa_0
import com.example.setdrive.R.drawable.ic_casa_1
import com.example.setdrive.R.drawable.ic_documento_0
import com.example.setdrive.R.drawable.ic_documento_1
import com.example.setdrive.R.drawable.ic_orcamento
import com.example.setdrive.R.drawable.ic_orcamento_1
import com.example.setdrive.R.drawable.ic_usuario_0
import com.example.setdrive.R.drawable.ic_usuario_1
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigation(navController: NavController, modifier: Modifier = Modifier) {
    val navItemList = listOf(
        BottomNavItem("home", "Início", ic_casa_0, ic_casa_1),
        BottomNavItem("planejamento", "Agenda", ic_calendario_bar_0, ic_calendario_bar_1),
        BottomNavItem("relatorio", "Relatório", ic_documento_0, ic_documento_1),
        BottomNavItem("Gastos", "Gastos", ic_orcamento, ic_orcamento_1),
//        BottomNavItem("conta", "Conta", ic_usuario_0, ic_usuario_1),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .shadow(
                elevation = 8.dp, // Define a altura da sombra
                shape = androidx.compose.foundation.shape.RoundedCornerShape(30.dp)
            )
            .graphicsLayer {
                shape = androidx.compose.foundation.shape.RoundedCornerShape(30.dp)
                clip = true
            }
            .height(66.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
        ) {
            navItemList.forEach { navItem ->
                val isSelected = currentRoute == navItem.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(navItem.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(
                                if (isSelected) navItem.selectedIconRes else navItem.iconRes
                            ),
                            contentDescription = navItem.label,
                            modifier = Modifier.size(22.dp),
                            tint = if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface
                        )
                    },
                    label = {
                        Text(
                            text = navItem.label,
                            color = if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface
                        )
                    },
                    alwaysShowLabel = true, // Mantém o texto sempre visível
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0f),
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    BottomNavigation(navController = navController)
}