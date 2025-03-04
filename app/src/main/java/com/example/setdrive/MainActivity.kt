package com.example.setdrive

import ActivitiesScreen
import GastosScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.features.account.screen.AccountScreen
import com.example.setdrive.features.account.screen.HomeScreen
import com.example.setdrive.features.addactivity.screen.AddActivityScreen
import com.example.setdrive.features.componentes.menu.BottomNavigation
import com.example.setdrive.features.planning.screen.PlanningScreen
import com.example.setdrive.features.activities.ActivityScreen.EditionScreen
import com.example.setdrive.features.register.LoginScreen
import com.example.setdrive.features.register.PasswordScreen
import com.example.setdrive.features.register.RegisterScreen
import com.example.setdrive.ui.theme.SetDriveTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetDriveTheme {
                MainApp()
            }
        }
    }

    private fun setNavigationBarColor(color: Int) {
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightNavigationBars = false
        window.navigationBarColor = color
    }

    private fun setStatusBarColor(color: Int, isDarkMode: Boolean) {
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = !isDarkMode
        window.statusBarColor = color
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainApp() {
    val navController = rememberNavController()
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (shouldShowBottomBar(currentScreen)) {
                BottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home",
                enterTransition = { scaleIn(initialScale = 1.5f, animationSpec = tween(400)) },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                HomeScreen(navController = navController)
            }

            composable("planejamento",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                PlanningScreen(navController = navController)
            }

            composable("addscreen",
                enterTransition = { scaleIn(initialScale = 1.5f, animationSpec = tween(400)) },
                exitTransition = {
                    scaleOut(
                        targetScale = 0.9f,
                        animationSpec = tween(400)
                    ) + fadeOut(animationSpec = tween(400))
                }
            ) {
                AddActivityScreen(navController = navController)
            }

            composable("relatorio",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                ActivitiesScreen(navController = navController)
            }

            composable("Gastos",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                GastosScreen(navController = navController)
            }

            composable("conta",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                AccountScreen(navController = navController)
            }

            composable("login",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                LoginScreen(navController = navController)
            }

            composable("registro",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                RegisterScreen(navController = navController)
            }

            composable("password",
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(500)
                    )
                }
            ) {
                PasswordScreen(navController = navController)
            }

            composable(
                "editaractivity",
                enterTransition = {
                    slideInVertically(
                        initialOffsetY = { it }, // Começa abaixo da tela
                        animationSpec = tween(400)
                    ) + fadeIn(animationSpec = tween(300))
                },
                exitTransition = {
                    slideOutVertically(
                        targetOffsetY = { it }, // Sai para baixo
                        animationSpec = tween(400)
                    ) + fadeOut(animationSpec = tween(300))
                }
            ) {
                EditionScreen(navController = navController)
            }

        }
    }
}

// Função para verificar se a BottomNavigation deve ser exibida
fun shouldShowBottomBar(currentScreen: String?): Boolean {
    val hiddenRoutes =
        listOf("addscreen", "editaractivity", "login", "registro", "password", "conta")
    return currentScreen != null && currentScreen !in hiddenRoutes
}
