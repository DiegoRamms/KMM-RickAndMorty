import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.Character
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.CharactersViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    KoinContext {
        AppContent()
    }
}

@Composable
fun AppContent(viewModel: CharactersViewModel = koinInject()) {
    MaterialTheme {

        val charactersState by viewModel.charactersResponse.collectAsState()
        val error by viewModel.errorState.collectAsState()
        var textTyped by rememberSaveable { mutableStateOf("") }


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

        Box(modifier = Modifier.fillMaxSize().background(Color(0, 0, 0))) {
            Column(Modifier.fillMaxWidth().padding(10.dp)) {

                Text(
                    "Rick and Morty",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    "The Rick and Morty API",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = textTyped,
                    onValueChange = { textTyped = it },
                    label = {
                        Text(
                            "Search character",
                            Modifier.padding(bottom = 4.dp),
                            color = Color.LightGray
                        )
                    },
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp).fillMaxWidth(),
                    colors = customColors,
                    maxLines = 1,
                    keyboardOptions = keyboardOptions,
                    shape = shape
                )

                Spacer(modifier = Modifier.height(10.dp))

                AnimatedVisibility(!(charactersState?.characters.isNullOrEmpty())) {
                    charactersState?.characters?.let {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            items(it) { character ->
                                CharacterView(character) {
                                    println(it)
                                }
                            }
                        }
                    }

                }

                AnimatedVisibility(error != null) {
                    error?.let { Text(it) }
                }

            }
        }

    }
}

@Composable
fun CharacterView(character: Character, onClickContact: (Character) -> Unit) {
    val painterResource: Resource<Painter> = asyncPainterResource(character.image)
    val shape = RoundedCornerShape(10.dp)

    Card(
        backgroundColor = Color(31, 33, 33),
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
