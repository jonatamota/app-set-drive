package com.example.setdrive.features.planning.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.setdrive.features.componentes.menu.CustomTopBar
import com.example.setdrive.features.planning.componentes.BottomCalculer
import com.example.setdrive.features.planning.componentes.CalendarComponent
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlanningScreen(navController: NavController, modifier: Modifier = Modifier) {
    var isRefreshing by remember { mutableStateOf(false) }
    var hasInitializedMonth by remember { mutableStateOf(false) }
    var hasFirstGoal by remember { mutableStateOf(false) }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    // Controla a exibição do box de carregamento
    var showLoadingBox by remember { mutableStateOf(false) }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            showLoadingBox = true
            kotlinx.coroutines.delay(1500) // Tempo da animação de carregamento
            hasInitializedMonth = true
            isRefreshing = false
            showLoadingBox = false
        }
    }

    // Animação para o Box de carregamento descer
    val boxOffsetAnim = animateDpAsState(
        targetValue = if (isRefreshing) 0.dp else -60.dp, // O box desce para 0 e depois sobe para -60
        animationSpec = tween(durationMillis = 500)
    )

    // Animação para os elementos descerem e depois subirem
    val contentOffsetAnim = animateDpAsState(
        targetValue = if (isRefreshing) 60.dp else 0.dp, // Os elementos descem 60dp e depois sobem
        animationSpec = tween(durationMillis = 500)
    )

    Scaffold(
        topBar = {
            CustomTopBar(title = "Planejamento Mensal")
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            // Corpo principal da tela com conteúdo rolável
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { isRefreshing = true },
                indicator = { _, _ -> }  // Remove o indicador de progresso do swipe
            ) {
                // Animação do Box de carregamento
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .offset(y = boxOffsetAnim.value) // Animação do Box descendo
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    if (showLoadingBox) {
                        CircularProgressIndicator()
                    }
                }

                // Lista rolável (os elementos vão descer e depois subir)
                LazyColumn(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                        .animateContentSize() // Anima a transição ao remover o loading
                        .offset(y = contentOffsetAnim.value), // Anima a descida e subida dos elementos
                    horizontalAlignment = Alignment.CenterHorizontally,
                            contentPadding = PaddingValues(bottom = 60.dp) // Adiciona espaço extra no final da lista
                ) {
                    if (hasInitializedMonth) {
                        item { BottomCalculer() }
                    }

                    item {
                        Box(
                            modifier = Modifier.alpha(
                                if (hasInitializedMonth) 1f else 0.5f,
                            )
                        ) {
                            CalendarComponent()
                        }
                    }
                }
            }

            // Card fixo na parte inferior
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(6.dp)
                    .background(Color.Transparent), // Torna o fundo do Box transparente
            ) {
                Card(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = when {
                            !hasInitializedMonth -> MaterialTheme.colorScheme.primary
                            hasFirstGoal -> MaterialTheme.colorScheme.surfaceVariant
                            else -> MaterialTheme.colorScheme.primary
                        }
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when {
                                !hasInitializedMonth -> "Deslize para baixo para iniciar o mês"
                                hasFirstGoal -> "Insira ou altere suas metas"
                                else -> "Tudo pronto, insira suas metas diárias"
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(8.dp),
                            color = if (hasFirstGoal) {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            } else {
                                MaterialTheme.colorScheme.onPrimary
                            }
                        )
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PlanningScreenPreview() {
    PlanningScreen(navController = NavController(LocalContext.current))
}
