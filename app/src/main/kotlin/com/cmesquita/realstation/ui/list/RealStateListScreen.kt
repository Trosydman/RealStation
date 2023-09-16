package com.cmesquita.realstation.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmesquita.realstation.ui.components.CircularLoadingFullScreen
import com.cmesquita.realstation.ui.list.model.RealStateListItem
import com.cmesquita.realstation.ui.theme.RealStationTheme
import kotlin.random.Random

@Composable
fun RealStateListScreen() {
    RealStateListContent(state = RealStateListUIState.Info("This app still under constructions"))
}

@Composable
private fun RealStateListContent(
    state: RealStateListUIState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is RealStateListUIState.Loading -> CircularLoadingFullScreen()
        is RealStateListUIState.Info -> Info(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            icon = when (state) {
                is RealStateListUIState.Info.Empty -> Icons.Default.Search
                is RealStateListUIState.Info.Error -> Icons.Default.Warning
                else -> Icons.Default.Info
            },
            text = state.message,
        )

        is RealStateListUIState.Content -> RealStateList(
            modifier = modifier,
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
            realStates = state.realStates,
            onRealStateClick = { TODO() }
        )
    }
}

@Composable
private fun Info(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .semantics(mergeDescendants = true) {},
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CompositionLocalProvider(
                LocalContentColor provides LocalContentColor.current.copy(
                    alpha = 0.25f
                )
            ) {
                Icon(
                    modifier = Modifier.size(56.dp),
                    imageVector = icon,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Content(
            realStates = List(4) {
                RealStateListItem(
                    id = it.toString(),
                    photoURL = "https://picsum.photos/id/${Random.nextInt(1, 300)}/720/376",
                    price = "${Random.nextInt(100, 999)}.000 €",
                    location = "Berlin",
                    area = "${Random.nextInt(1, 100)} m2",
                )
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentLoadingPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Loading
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentInfoPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Info("Some very useful information you cannot miss")
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentErrorPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Info.Error("Ups! Something went wrong...")
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentEmptyPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Info.Empty("Ups! There is nothing to see here...")
    )
}
