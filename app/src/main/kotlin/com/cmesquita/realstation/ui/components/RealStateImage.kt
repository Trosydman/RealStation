package com.cmesquita.realstation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.cmesquita.realstation.R
import com.cmesquita.realstation.ui.theme.RealStationTheme

@Composable
internal fun RealStateImage(
    imageURL: String?,
    modifier: Modifier = Modifier,
) {
    val asyncImagePainter = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageURL)
            .size(Size.ORIGINAL)
            .build()
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        when (asyncImagePainter.state) {
            is AsyncImagePainter.State.Success -> {
                Image(
                    modifier = Modifier.matchParentSize(),
                    painter = asyncImagePainter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }

            is AsyncImagePainter.State.Error -> {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.ic_broken_house),
                    alpha = 0.25f,
                    contentDescription = null,
                )
            }

            else -> CircularLoading()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RealStateImagePreview() = RealStationTheme {
    val aspectRatio = remember { 16f / 9f }

    RealStateImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio),
        imageURL = "https://picsum.photos/id/58/1280/853",
    )
}
