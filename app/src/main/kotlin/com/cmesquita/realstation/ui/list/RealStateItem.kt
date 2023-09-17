package com.cmesquita.realstation.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmesquita.realstation.ui.components.RealStateImage
import com.cmesquita.realstation.ui.list.model.RealStateListItem
import com.cmesquita.realstation.ui.theme.RealStationTheme
import kotlin.random.Random

private const val ASPECT_RATIO_IMAGE = 90f / 47f

@Composable
fun RealStateItem(
    realState: RealStateListItem,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { onItemClick(realState.id) }
    ) {
        Column {
            RealStateImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(ASPECT_RATIO_IMAGE)
                    .clip(CardDefaults.shape),
                imageURL = realState.photoURL,
            )

            InfoSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                price = realState.price,
                location = realState.location,
                area = realState.area,
            )
        }
    }
}

@Suppress("MagicNumber")
@Composable
private fun InfoSection(
    price: String,
    location: String,
    area: String,
    modifier: Modifier = Modifier,
) {
    val titleWeight = remember { 0.67f }
    val subInfoWeight = remember { 1f - titleWeight }

    Row(
        modifier = modifier
            .semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(titleWeight),
            text = price,
            style = MaterialTheme.typography.displaySmall,
        )

        Box(
            modifier = Modifier.weight(subInfoWeight),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Column {
                InfoItem(
                    icon = Icons.Default.LocationOn,
                    info = location,
                )

                Spacer(modifier = Modifier.size(4.dp))

                InfoItem(
                    icon = Icons.Default.Home,
                    info = area,
                )
            }
        }
    }
}

@Composable
private fun InfoItem(
    icon: ImageVector,
    info: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = icon,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = info,
            style = MaterialTheme.typography.labelMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun RealStateItemPreview() = RealStationTheme {
    RealStateItem(
        modifier = Modifier.fillMaxWidth(),
        realState = RealStateListItem(
            id = "1",
            photoURL = "https://picsum.photos/id/58/1280/853",
            price = "${Random.nextInt(100, 999)}.000.000 €",
            location = "Berlin",
            area = "${Random.nextInt(1, 100)} m2",
        ),
        onItemClick = {}
    )
}
