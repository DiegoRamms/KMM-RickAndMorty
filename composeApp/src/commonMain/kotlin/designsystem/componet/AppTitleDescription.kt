package designsystem.componet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun TitleDescriptionText(text: String){
    Text(
        text,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = Color.LightGray
    )
}