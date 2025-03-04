import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.setdrive.features.componentes.menu.CustomTopBar
import com.example.setdrive.features.gastos.componentes.BottomCalculerGastos
import com.example.setdrive.features.gastos.componentes.RowGastos

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GastosScreen(navController: NavController) {
    val despesasFixas = listOf(
        "Aluguel" to 500.00,
        "Plano de saúde" to 100.00,
        "Carro" to 600.00,
        "Colégio" to 200.00,
        "Mercado" to 700.00
    )
    val despesasEventuais = listOf("Festa fantasia" to 100.00)

    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = { CustomTopBar(title = "Meu Orçamento") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSheet = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nova Despesa")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                BottomCalculerGastos()
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Seção de Despesas Fixas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text("Despesas Fixas", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(despesasFixas) { despesa ->
                    RowGastos(descricao = despesa.first, valor = despesa.second)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Seção de Despesas Eventuais
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text("Despesas Eventuais", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(despesasEventuais) { despesa ->
                    RowGastos(descricao = despesa.first, valor = despesa.second)
                }
            }
        }
    }

    // BottomSheet para adicionar despesas
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            AddExpenseSheetComponent(
                onDismiss = { showSheet = false },
                onSave = { _, _, _, _, _ -> showSheet = false }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun GastosScreenPreview() {
    GastosScreen(navController = NavController(ComponentActivity()))
}
