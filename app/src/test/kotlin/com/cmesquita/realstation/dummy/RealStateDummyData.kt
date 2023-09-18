package com.cmesquita.realstation.dummy

import com.cmesquita.realstation.data.remote.model.RealStateDTO
import com.cmesquita.realstation.data.remote.model.RealStateListDTO
import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.ui.details.model.RealStateDetails
import com.cmesquita.realstation.ui.list.model.RealStateListItem

fun dummyRealStateDTO(id: Long) = RealStateDTO(
    bedrooms = 1,
    city = "Berlin",
    id = id,
    area = 50.0f,
    url = "https://aviv.pics.com/$id",
    price = 500000.0f.toBigDecimal(),
    professional = "Professional",
    propertyType = "Flat",
    offerType = 2,
    rooms = 4
)

fun dummyRealState(id: Long) = RealState(
    id = id,
    picUrl = "https://aviv.pics.com/$id",
    price = 500000.0f.toBigDecimal(),
    area = 50.0f,
    city = "Berlin",
    totalRooms = 4,
    bedrooms = 1,
    propertyType = "Flat",
    offerType = 2,
    professional = "Professional",
)

fun dummyRealStateListItem(id: Long) = RealStateListItem(
    id = id.toString(),
    photoURL = "https://aviv.pics.com/$id",
    price = 500000.0f.toBigDecimal(),
    location = "Berlin",
    area = 50,
)

fun dummyRealStateDetails(id: Long) = RealStateDetails(
    photoURL = "https://aviv.pics.com/$id",
    price = 500000.0f.toBigDecimal(),
    area = 50,
    location = "Berlin",
    totalRooms = 4,
    bedrooms = 1,
    propertyType = "Flat",
    professional = "Professional",
)

fun dummyRealStateListDTO(count: Int) = RealStateListDTO(
    items = List(count) { dummyRealStateDTO(it.toLong()) },
    totalCount = count,
)

fun dummyEmptyRealStateListDTO() = RealStateListDTO(
    items = emptyList(),
    totalCount = 0,
)
