package com.cmesquita.realstation.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmesquita.realstation.R
import com.cmesquita.realstation.ui.components.RealStateImage
import com.cmesquita.realstation.ui.theme.RealStationTheme

private const val HEADER_IMAGE_ASPECT_RATIO = 10f / 11f
private val headerImageShape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomEnd = 48.dp,
    bottomStart = 48.dp
)

@Composable
internal fun Header(
    photoURL: String?,
    location: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {

        RealStateImage(
            modifier = Modifier
                .aspectRatio(HEADER_IMAGE_ASPECT_RATIO)
                .clip(headerImageShape),
            imageURL = photoURL,
        )

        Surface(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
                .semantics(mergeDescendants = true) {},
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.secondary,
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(id = R.string.details_a11y_location),
                )

                Text(
                    text = location,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() = RealStationTheme {
    Header(
        photoURL = "https://picsum.photos/590/330",
        location = "Berlin",
    )
}
