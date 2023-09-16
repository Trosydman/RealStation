package com.cmesquita.realstation.ui.details.model

data class RealStateDetails(
    val photoURL: String?,
    val price: String,
    val area: String,
    val location: String,
    val totalRooms: Int,
    val bedrooms: Int,
    val propertyType: String,
    val professional: String,
)
