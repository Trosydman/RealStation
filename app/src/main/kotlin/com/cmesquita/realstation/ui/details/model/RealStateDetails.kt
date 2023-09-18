package com.cmesquita.realstation.ui.details.model

import java.math.BigDecimal

data class RealStateDetails(
    val photoURL: String?,
    val price: BigDecimal,
    val area: Int,
    val location: String,
    val totalRooms: Int?,
    val bedrooms: Int?,
    val propertyType: String,
    val professional: String,
)
