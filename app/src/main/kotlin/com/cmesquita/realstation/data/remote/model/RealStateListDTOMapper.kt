package com.cmesquita.realstation.data.remote.model

import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.domain.utils.DomainMapper
import javax.inject.Inject

class RealStateListDTOMapper @Inject constructor() :
    DomainMapper<RealStateListDTO, List<RealState>> {
    private val realStateDTOMapper = RealStateDTOMapper()

    override fun toDomain(model: RealStateListDTO): List<RealState> {
        return model.items.map(realStateDTOMapper::toDomain)
    }
}
