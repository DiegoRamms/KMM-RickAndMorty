package designsystem.componet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun <T>AppLazyRow(list: List<T>? = mutableListOf(), content: @Composable (T) -> Unit,) {
    AnimatedVisibility(!list.isNullOrEmpty()) {
        list?.let {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                items(it) { item ->
                    Box {
                        content(item)
                    }
                }
            }
        }

    }
}
