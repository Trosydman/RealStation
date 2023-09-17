package com.cmesquita.realstation.ui.list.model

import java.math.BigDecimal

data class RealStateListItem(
    val id: String,
    val photoURL: String?,
    val price: BigDecimal,
    val location: String,
    val area: Int,
)
