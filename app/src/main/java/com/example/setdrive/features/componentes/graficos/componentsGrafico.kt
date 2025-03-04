import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.setdrive.R

@Composable
fun ElementosDoCard(
    iconResId: Int,
    title: String,
    amount: String,
    valor_hora: String,
    secondIconResId: Int? = null,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Linha com ícone e título
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Ícone ao lado do título
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                // Título ao lado do ícone
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(4.dp)) // Espaçamento entre título e a borda

            // Borda circular ao redor do segundo ícone e textos
            Box(
                modifier = Modifier
                    .size(88.dp) // Tamanho do círculo
                    .clip(CircleShape) // Borda circular
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.primary,// Cor da borda
                        shape = CircleShape
                    )
                    .padding(8.dp), // Espaço interno
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    secondIconResId?.let {
                        // Ícone secundário dentro do círculo
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp) // Tamanho do segundo ícone
                        )
                    }

                    // **Esse Spacer empurra os textos para cima**
                    Spacer(modifier = Modifier.height(2.dp)) // Define um espaçamento fixo maior

                    // Textos (amount e valor_hora)
                    Text(
                        text = amount,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = valor_hora,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElementosDoCardPreview() {
    ElementosDoCard(
        iconResId = android.R.drawable.ic_menu_info_details,
        title = "Resumo",
        amount = "R$ 100,00",
        valor_hora = "R$ 10,00",
        secondIconResId = R.drawable.ic_carro_frente_1
    )
}
