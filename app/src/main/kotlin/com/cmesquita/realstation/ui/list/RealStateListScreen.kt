package com.cmesquita.realstation.ui.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.cmesquita.realstation.ui.components.CircularLoadingFullScreen
import com.cmesquita.realstation.ui.components.InfoMessageFullScreen
import com.cmesquita.realstation.ui.list.model.RealStateListItem
import com.cmesquita.realstation.ui.theme.RealStationTheme
import java.math.BigDecimal
import kotlin.random.Random

@Composable
fun RealStateListScreen(
    viewModel: RealStateListViewModel,
    navigateToDetails: (String) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handle(RealStateListAction.Launch)
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.eventsFlow.collect { event ->
                when (event) {
                    is RealStateListEvent.NavigateToDetails -> navigateToDetails(event.id)
                }
            }
        }
    }

    RealStateListContent(
        modifier = Modifier.fillMaxSize(),
        state = state.value,
        onRealStateClick = { navigateToDetails(it) }
    )
}

@Composable
private fun RealStateListContent(
    state: RealStateListUIState,
    onRealStateClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is RealStateListUIState.Loading -> CircularLoadingFullScreen()
        is RealStateListUIState.Info -> InfoMessageFullScreen(
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
            onRealStateClick = onRealStateClick,
        )
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
                    price = BigDecimal(Random.nextInt(100, 999) * 1000000),
                    location = "Berlin",
                    area = Random.nextInt(1, 100),
                )
            }
        ),
        onRealStateClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentLoadingPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Loading,
        onRealStateClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentInfoPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Info("Some very useful information you cannot miss"),
        onRealStateClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentErrorPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Info.Error("Ups! Something went wrong..."),
        onRealStateClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateListContentEmptyPreview() = RealStationTheme {
    RealStateListContent(
        state = RealStateListUIState.Info.Empty("Ups! There is nothing to see here..."),
        onRealStateClick = {}
    )
}
