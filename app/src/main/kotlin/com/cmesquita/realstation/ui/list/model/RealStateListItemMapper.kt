package com.cmesquita.realstation.ui.list.model

import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.domain.utils.UIMapper
import kotlin.math.roundToInt

class RealStateListItemMapper : UIMapper<RealState, RealStateListItem> {
    override fun toUi(model: RealState): RealStateListItem {
        with(model) {
            return RealStateListItem(
                id = id.toString(),
                photoURL = picUrl,
                price = price,
                location = city,
                area = area.roundToInt(),
            )
        }
    }
}
