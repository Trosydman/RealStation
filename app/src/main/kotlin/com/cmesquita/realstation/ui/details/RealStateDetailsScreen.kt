package com.cmesquita.realstation.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmesquita.realstation.ui.components.CircularLoadingFullScreen
import com.cmesquita.realstation.ui.components.InfoMessageFullScreen
import com.cmesquita.realstation.ui.details.model.RealStateDetails
import com.cmesquita.realstation.ui.theme.RealStationTheme
import java.math.BigDecimal
import kotlin.random.Random

@Composable
fun RealStateDetailsScreen() {
    RealStateDetailsContent(
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 24.dp,
        ),
        state = RealStateDetailsUIState.Error("This app still under constructions"),
    )
}

@Composable
fun RealStateDetailsContent(
    state: RealStateDetailsUIState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    when (state) {
        is RealStateDetailsUIState.Loading -> CircularLoadingFullScreen()
        is RealStateDetailsUIState.Error -> InfoMessageFullScreen(
            icon = Icons.Default.Warning,
            text = state.message,
        )

        is RealStateDetailsUIState.Content -> RealStateDetails(
            modifier = modifier,
            realState = state.realState,
            contentPadding = contentPadding,
        )
    }
}

@Composable
fun RealStateDetails(
    realState: RealStateDetails,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    Column(modifier = modifier) {
        Header(
            modifier = Modifier.fillMaxWidth(),
            photoURL = realState.photoURL,
            location = realState.location,
        )

        Details(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            realState = realState,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RealStateDetailsContentPreview() = RealStationTheme {
    RealStateDetailsContent(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 24.dp,
        ),
        state = RealStateDetailsUIState.Content(
            realState = RealStateDetails(
                photoURL = "https://picsum.photos/590/330",
                price = BigDecimal(Random.nextInt(100, 999) * 1000000),
                area = Random.nextInt(1, 100),
                location = "Berlin",
                totalRooms = 4,
                bedrooms = 1,
                propertyType = "Flat",
                professional = "Real State Agent",
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateDetailsContentErrorPreview() = RealStationTheme {
    RealStateDetailsContent(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 24.dp,
        ),
        state = RealStateDetailsUIState.Error(
            message = "Ups! An unexpected error happened...\nTry again later"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun RealStateDetailsContentLoadingPreview() = RealStationTheme {
    RealStateDetailsContent(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 24.dp,
        ),
        state = RealStateDetailsUIState.Loading
    )
}
