package com.cmesquita.realstation.domain.model

import java.math.BigDecimal

data class RealState(
    val id: Long,
    val picUrl: String?,
    val price: BigDecimal,
    val area: Float,
    val city: String,
    val totalRooms: Int,
    val bedrooms: Int,
    val propertyType: String,
    val offerType: Int,
    val professional: String,
)
