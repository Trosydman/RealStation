package com.cmesquita.realstation.ui.list.model

import com.cmesquita.realstation.domain.model.RealState
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RealStateListItemMapperTest {
    private val mapper = RealStateListItemMapper()

    companion object {

        @JvmStatic
        fun mapperArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                RealState(
                    id = 1,
                    picUrl = "https://aviv.pics.com/1",
                    price = 500000.0f.toBigDecimal(),
                    area = 50.0f,
                    city = "Berlin",
                    totalRooms = 4,
                    bedrooms = 1,
                    propertyType = "Flat",
                    offerType = 2,
                    professional = "Professional",
                ),
                RealStateListItem(
                    id = "1",
                    photoURL = "https://aviv.pics.com/1",
                    price = 500000.0f.toBigDecimal(),
                    location = "Berlin",
                    area = 50,
                )
            ),
            Arguments.of(
                RealState(
                    id = 2,
                    picUrl = null,
                    price = 1500000.0f.toBigDecimal(),
                    area = 250.8f,
                    city = "Berlin",
                    totalRooms = 8,
                    bedrooms = 4,
                    propertyType = "Villa",
                    offerType = 1,
                    professional = "Professional",
                ),
                RealStateListItem(
                    id = "2",
                    photoURL = null,
                    price = 1500000.0f.toBigDecimal(),
                    location = "Berlin",
                    area = 251,
                ),
            )
        )
    }

    @ParameterizedTest(
        name = "when \"{0}\" is mapped to UI, then \"{1}\" is the result"
    )
    @MethodSource("mapperArguments")
    fun `when mapper is triggered, models should be mapped correctly`(
        realState: RealState,
        realStateListItem: RealStateListItem,
    ) = runTest {
        val actualResult = mapper.toUi(realState)

        actualResult shouldBe realStateListItem
    }
}
