package com.cmesquita.realstation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cmesquita.realstation.ui.theme.RealStationTheme

@Composable
internal fun CircularLoadingFullScreen() {
    CircularLoading(modifier = Modifier.fillMaxSize())
}

@Composable
internal fun CircularLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun CircularLoadingPreview() = RealStationTheme {
    CircularLoading()
}

@Preview(showBackground = true)
@Composable
private fun CircularLoadingFullScreenPreview() = RealStationTheme {
    CircularLoadingFullScreen()
}
