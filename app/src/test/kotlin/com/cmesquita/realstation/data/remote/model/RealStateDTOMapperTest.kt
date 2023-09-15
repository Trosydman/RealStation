package com.cmesquita.realstation.data.remote.model

import com.cmesquita.realstation.domain.model.RealState
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class RealStateDTOMapperTest {
    private val mapper = RealStateDTOMapper()

    companion object {

        @JvmStatic
        fun mapperArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                RealStateDTO(
                    bedrooms = 1,
                    city = "Berlin",
                    id = 1,
                    area = 50.0f,
                    url = "https://aviv.pics.com/1",
                    price = 500000.0f.toBigDecimal(),
                    professional = "Professional",
                    propertyType = "Flat",
                    offerType = 2,
                    rooms = 4
                ),
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
                )
            ),
            Arguments.of(
                RealStateDTO(
                    bedrooms = 4,
                    city = "Berlin",
                    id = 2,
                    area = 250.0f,
                    url = null,
                    price = 1500000.0f.toBigDecimal(),
                    professional = "Professional",
                    propertyType = "Villa",
                    offerType = 1,
                    rooms = 8
                ),
                RealState(
                    id = 2,
                    picUrl = null,
                    price = 1500000.0f.toBigDecimal(),
                    area = 250.0f,
                    city = "Berlin",
                    totalRooms = 8,
                    bedrooms = 4,
                    propertyType = "Villa",
                    offerType = 1,
                    professional = "Professional",
                )
            )
        )
    }

    @ParameterizedTest(
        name = "when \"{0}\" is mapped to domain, then \"{1}\" is the result"
    )
    @MethodSource("mapperArguments")
    fun `when mapper is triggered, models should be mapped correctly`(
        realStateDTO: RealStateDTO,
        realState: RealState,
    ) = runTest {
        val actualResult = mapper.toDomain(realStateDTO)

        actualResult shouldBe realState
    }
}
