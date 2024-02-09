package designsystem.componet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AppCustomTextField(textTyped: String, label: String? = null, onTextChanged: (String) -> Unit) {
    val customColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color(31, 33, 33), // Change the background color
        focusedIndicatorColor = Color.Transparent, // Remove the focused border
        unfocusedIndicatorColor = Color.Transparent,
        cursorColor = Color.LightGray,
        textColor = Color.White
    )

    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    )

    val shape = RoundedCornerShape(8.dp)

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(10.dp)) {

            TextField(
                value = textTyped,
                onValueChange = { onTextChanged(it) },
                label = {
                    label?.let {
                        Text(
                            label,
                            Modifier.padding(bottom = 4.dp),
                            color = Color.LightGray
                        )
                    }
                },
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp).fillMaxWidth(),
                colors = customColors,
                maxLines = 1,
                keyboardOptions = keyboardOptions,
                shape = shape
            )

        }
    }
}