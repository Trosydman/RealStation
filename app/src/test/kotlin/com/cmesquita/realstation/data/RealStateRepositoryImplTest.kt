package com.cmesquita.realstation.data

import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.data.remote.AvivAPI
import com.cmesquita.realstation.data.remote.model.RealStateDTO
import com.cmesquita.realstation.data.remote.model.RealStateDTOMapper
import com.cmesquita.realstation.data.remote.model.RealStateListDTO
import com.cmesquita.realstation.data.remote.model.RealStateListDTOMapper
import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.dummy.dummyEmptyRealStateListDTO
import com.cmesquita.realstation.dummy.dummyRealState
import com.cmesquita.realstation.dummy.dummyRealStateDTO
import com.cmesquita.realstation.dummy.dummyRealStateListDTO
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import retrofit2.Response
import java.util.stream.Stream

internal class RealStateRepositoryImplTest {

    private var avivAPIMock = mockk<AvivAPI>()

    private val repositoryImpl = RealStateRepositoryImpl(
        avivAPI = avivAPIMock,
        realStateDTOMapper = RealStateDTOMapper(),
        realStateListDTOMapper = RealStateListDTOMapper(),
    )

    companion object {
        private const val dummyCount = 4

        private const val CODE_INTERNAL_SERVER_ERROR = 500
        private const val MESSAGE_INTERNAL_SERVER_ERROR = "Internal server error"

        private const val CODE_NOT_FOUND = 404
        private const val MESSAGE_NOT_FOUND = "Not found"

        @JvmStatic
        fun getListArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Response.success(
                    dummyRealStateListDTO(dummyCount)
                ),
                Result.Success(
                    List(dummyCount) { dummyRealState(it.toLong()) }
                )
            ),
            Arguments.of(
                Response.error<RealStateDTO>(
                    CODE_INTERNAL_SERVER_ERROR,
                    ResponseBody.create(null, MESSAGE_INTERNAL_SERVER_ERROR)
                ),
                Result.Error(
                    code = CODE_INTERNAL_SERVER_ERROR,
                    message = MESSAGE_INTERNAL_SERVER_ERROR,
                )
            ),
            Arguments.of(
                Response.success(dummyEmptyRealStateListDTO()),
                Result.Success(emptyList<RealState>())
            ),
        )

        @JvmStatic
        fun getByArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                "1",
                Response.success(
                    dummyRealStateDTO(1)
                ),
                Result.Success(
                    dummyRealState(1)
                )
            ),
            Arguments.of(
                "$CODE_NOT_FOUND",
                Response.error<RealStateDTO>(
                    CODE_NOT_FOUND,
                    ResponseBody.create(null, MESSAGE_NOT_FOUND)
                ),
                Result.Error(
                    code = CODE_NOT_FOUND,
                    message = MESSAGE_NOT_FOUND,
                )
            ),
        )
    }

    @ParameterizedTest(
        name = "when \"{0}\" is received as response, then \"{1}\" should be the result"
    )
    @MethodSource("getListArguments")
    fun `when receiving real state list response, then handle result accordingly`(
        response: Response<RealStateListDTO>,
        result: Result<List<RealState>>,
    ) = runTest {
        coEvery { avivAPIMock.getRealStateList() } returns response

        val realStateResult = repositoryImpl.getList()

        realStateResult shouldBe result
    }

    @ParameterizedTest(
        name = "when \"{0}\" is received as response, then \"{1}\" should be the result"
    )
    @MethodSource("getByArguments")
    fun `when receiving real state response, then handle result accordingly`(
        id: String,
        response: Response<RealStateDTO>,
        result: Result<RealState>,
    ) = runTest {
        coEvery { avivAPIMock.getRealStateBy(id) } returns response

        val realStateResult = repositoryImpl.getBy(id)

        realStateResult shouldBe result
    }
}
