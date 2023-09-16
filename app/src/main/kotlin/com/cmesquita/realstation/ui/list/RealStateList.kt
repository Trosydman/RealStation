package com.cmesquita.realstation.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmesquita.realstation.ui.list.model.RealStateListItem
import com.cmesquita.realstation.ui.theme.RealStationTheme
import kotlin.random.Random

@Composable
fun RealStateList(
    realStates: List<RealStateListItem>,
    onRealStateClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
    ) {
        items(
            items = realStates,
            key = { it.id }
        ) { realState ->
            RealStateItem(
                realState = realState,
                onItemClick = onRealStateClick,
            )
        }
    }
}

@Preview
@Composable
private fun RealStateListPreview() = RealStationTheme {
    RealStateList(
        contentPadding = PaddingValues(16.dp),
        realStates = List(4) {
            RealStateListItem(
                id = it.toString(),
                photoURL = "url.com",
                price = "${Random.nextInt(100, 999)}.000 €",
                location = "Berlin",
                area = "${Random.nextInt(1, 100)} m2",
            )
        },
        onRealStateClick = {},
    )
}
