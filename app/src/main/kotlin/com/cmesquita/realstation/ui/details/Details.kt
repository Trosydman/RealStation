package com.cmesquita.realstation.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmesquita.realstation.R
import com.cmesquita.realstation.extensions.formatCurrency
import com.cmesquita.realstation.ui.details.model.RealStateDetails
import com.cmesquita.realstation.ui.theme.RealStationTheme
import java.math.BigDecimal
import kotlin.random.Random

@Composable
internal fun Details(
    realState: RealStateDetails,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        Title(
            price = realState.price.formatCurrency(),
            professional = realState.professional,
        )

        Spacer(modifier = Modifier.size(32.dp))

        Body(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            realState = realState,
        )
    }
}

@Composable
private fun Title(
    price: String,
    professional: String,
    modifier: Modifier = Modifier,

    ) {
    Column(modifier = modifier) {
        Text(
            text = price,
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = stringResource(id = R.string.details_label_professional, professional),
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Composable
fun Body(
    realState: RealStateDetails,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = 2,
    ) {
        val realStateFeatureModifier = remember {
            Modifier.weight(1f)
        }

        RealStateFeature(
            modifier = realStateFeatureModifier,
            icon = Icons.Default.ExitToApp,
            title = stringResource(id = R.string.details_title_area),
            text = stringResource(id = R.string.measurement_square_meter, realState.area),
        )
        RealStateFeature(
            modifier = realStateFeatureModifier,
            icon = Icons.Default.Home,
            title = stringResource(id = R.string.details_title_property_type),
            text = realState.propertyType,
        )
        RealStateFeature(
            modifier = realStateFeatureModifier,
            icon = Icons.Default.AccountBox,
            title = stringResource(id = R.string.details_title_bedrooms),
            text = pluralStringResource(
                id = R.plurals.details_label_rooms,
                count = realState.bedrooms,
                realState.bedrooms,
            ),
        )
        RealStateFeature(
            modifier = realStateFeatureModifier,
            icon = Icons.Default.List,
            title = stringResource(id = R.string.details_title_total_rooms),
            text = pluralStringResource(
                id = R.plurals.details_label_rooms,
                count = realState.totalRooms,
                realState.totalRooms,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsPreview() = RealStationTheme {
    Details(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
}
