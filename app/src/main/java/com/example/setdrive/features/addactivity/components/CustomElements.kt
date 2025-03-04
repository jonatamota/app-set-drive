import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    textColor: Color,
    fontSize: Int,
    backgroundColor: Color,
    borderColor: Color,
    focusedBorderColor: Color,
    labelColor: Color,
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier,
    maxLength: Int? = null,
    onFocusChanged: (FocusState) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default // Parâmetro adicionado aqui
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            // Se maxLength for null ou o novo valor não exceder maxLength, atualiza o valor
            if (maxLength == null || newValue.length <= maxLength) {
                onValueChange(newValue)
            }
        },
        textStyle = TextStyle(
            color = textColor,
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape
            )
            .onFocusChanged { focusState -> // Adicionando o listener de foco
                onFocusChanged(focusState)
            },
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions, // Passando o keyboardOptions aqui
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                label = label?.let { { Text(text = it, color = labelColor) } },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = borderColor,
                    focusedBorderColor = focusedBorderColor
                ),
                contentPadding = PaddingValues(0.dp), // Removendo todo o padding interno
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = true,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = borderColor,
                            focusedBorderColor = focusedBorderColor
                        ),
                        shape = shape
                    )
                }
            )
        }
    )
}
