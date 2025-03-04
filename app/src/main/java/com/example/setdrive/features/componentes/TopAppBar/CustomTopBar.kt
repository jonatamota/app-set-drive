package com.example.setdrive.features.componentes.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    showBackButton: Boolean = false,
    onBackPress: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            if (showBackButton) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    onBackPress?.let {
                        IconButton(onClick = it) {
                            Icon(
                                painter = painterResource(id = com.example.setdrive.R.drawable.ic_back),
                                contentDescription = "Voltar",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        modifier = Modifier
            .height(54.dp)
            .graphicsLayer {
                shadowElevation = 4.dp.toPx()
            },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}


@Preview(showBackground = true)
@Composable
fun CustomTopBarPreview() {
    CustomTopBar(title = "Atividades", showBackButton = true)
}

@Preview(showBackground = true)
@Composable
fun CustomTopBarNoBackButtonPreview() {
    CustomTopBar(title = "Atividades", showBackButton = false)
}