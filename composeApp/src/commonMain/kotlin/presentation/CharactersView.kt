package presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.Character
import designsystem.theme.AppColors.PrimaryDark
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CharacterView(character: Character, onClickContact: (Character) -> Unit) {
    val painterResource: Resource<Painter> = asyncPainterResource(character.image)
    val shape = RoundedCornerShape(10.dp)

    Card(
        backgroundColor = PrimaryDark,
        shape = shape,
        modifier = Modifier.width(200.dp).height(250.dp)
            .clickable { onClickContact(character) }) {

        Column {
            KamelImage(painterResource,
                contentDescription = character.name, onLoading = {
                    Text("Loading")
                }, onFailure = { println(it.message) },
                modifier = Modifier.fillMaxWidth().height(160.dp),
                contentScale = ContentScale.Crop
            )
            Column (modifier = Modifier.padding(10.dp)) {
                Text(
                    character.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Box(modifier = Modifier.fillMaxSize()){
                    Text(
                        character.species,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 10.dp).align(Alignment.BottomEnd),
                        textAlign = TextAlign.End
                    )
                }

            }

        }
    }

}
