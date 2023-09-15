package com.cmesquita.realstation.data.remote.model

import java.math.BigDecimal

data class RealStateDTO(
    val bedrooms: Int,
    val city: String,
    val id: Long,
    val area: Float,
    val url: String?,
    val price: BigDecimal,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int,
)
