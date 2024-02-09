package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import designsystem.componet.AppCustomTextField
import designsystem.componet.AppTitleText
import designsystem.componet.TitleDescriptionText
import designsystem.theme.AppColors.Background
import org.koin.compose.KoinContext
import org.koin.compose.koinInject


@Composable
fun App() {
    KoinContext {
        AppContent()
    }
}

@Composable
fun AppContent(viewModel: CharactersViewModel = koinInject()) {
    MaterialTheme {

        val uiState by viewModel.uiState.collectAsState()


        Box(modifier = Modifier.fillMaxSize().background(Background)) {
            Column(Modifier.fillMaxWidth().padding(10.dp)) {

                AppTitleText("Rick and Morty")
                Spacer(modifier = Modifier.height(5.dp))
                TitleDescriptionText("The Rick and Morty API")
                Spacer(modifier = Modifier.height(20.dp))
                AppCustomTextField( uiState.textTyped , label = "Search character") {
                    viewModel.searchCharacter(it)
                }

                Spacer(modifier = Modifier.height(10.dp))


                AnimatedVisibility(uiState.characters.isNotEmpty()) {
                    uiState.characters.let {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            items(it) { item ->
                                CharacterView(item) { character ->
                                    println(character.name)
                                }
                            }
                        }
                    }

                    AnimatedVisibility(uiState.error != null) {
                        uiState.error?.let { AppTitleText(it) }
                    }

                }
            }
        }
    }
}

