package com.cmesquita.realstation.ui.details.model

import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.domain.utils.UIMapper
import kotlin.math.roundToInt

class RealStateDetailsMapper : UIMapper<RealState, RealStateDetails> {
    override fun toUi(model: RealState): RealStateDetails {
        with(model) {
            return RealStateDetails(
                photoURL = picUrl,
                price = price,
                area = area.roundToInt(),
                location = city,
                totalRooms = totalRooms,
                bedrooms = bedrooms,
                propertyType = propertyType,
                professional = professional,
            )
        }
    }
}
