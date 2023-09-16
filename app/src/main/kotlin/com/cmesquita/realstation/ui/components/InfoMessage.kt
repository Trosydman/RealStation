package com.cmesquita.realstation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import com.cmesquita.realstation.ui.theme.RealStationTheme

@Composable
fun InfoMessageFullScreen(
    icon: ImageVector,
    text: String,
) {
    InfoMessage(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        icon = icon,
        text = text,
    )
}

@Composable
fun InfoMessage(
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
private fun InfoMessagePreview() = RealStationTheme {
    InfoMessage(
        icon = Icons.Default.Info,
        text = "This is a informational message",
    )
}

@Preview(showBackground = true)
@Composable
private fun InfoMessageFullScreenPreview() = RealStationTheme {
    InfoMessageFullScreen(
        icon = Icons.Default.Info,
        text = "This is a informational message",
    )
}
