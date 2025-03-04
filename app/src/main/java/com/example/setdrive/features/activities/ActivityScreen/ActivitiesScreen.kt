import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.setdrive.features.activities.cards.ActivityCard
import com.example.setdrive.features.componentes.menu.CustomTopBar

@Composable
fun ActivitiesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "Registro de Atividades")
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface) // Define o fundo como surface
                    .fillMaxSize()
                    .padding(paddingValues), // Aplica o padding para ajustar o layout
                verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre os itens
            ) {
                item {
                    // Exibe o card de filtro
                    FilterCard()
                }

                items(4) { // Substitua "4" pelo tamanho real da sua lista de dados
                    // Aqui você coloca o componente de card de atividade
                    ActivityCard(navController)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ActivitiesScreenPreview() {
    val navController = rememberNavController()
    ActivitiesScreen(navController = navController)
}
