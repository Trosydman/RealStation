package com.cmesquita.realstation.data.remote.model

import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.domain.utils.DomainMapper
import javax.inject.Inject

class RealStateDTOMapper @Inject constructor() : DomainMapper<RealStateDTO, RealState> {

    override fun toDomain(model: RealStateDTO): RealState {
        with(model) {
            return RealState(
                id = id,
                picUrl = url,
                price = price,
                area = area,
                city = city,
                totalRooms = rooms,
                bedrooms = bedrooms,
                propertyType = propertyType,
                offerType = offerType,
                professional = professional,
            )
        }
    }
}
