package com.cmesquita.realstation.ui.details

import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.domain.RealStateRepository
import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.dummy.dummyRealState
import com.cmesquita.realstation.dummy.dummyRealStateDetails
import com.cmesquita.realstation.fake.FakeCoroutineDispatchers
import com.cmesquita.realstation.ui.details.model.RealStateDetailsMapper
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RealStateDetailsViewModelShould {
    private val realStateDetailsResourcesMockk = mockk<RealStateDetailsResources>()
    private val realStateRepositoryMockk = mockk<RealStateRepository>()

    private val viewModel = RealStateDetailsViewModel(
        appCoroutineDispatchers = FakeCoroutineDispatchers(),
        realStateDetailsResources = realStateDetailsResourcesMockk,
        realStateDetailsMapper = RealStateDetailsMapper(),
        realStateRepository = realStateRepositoryMockk,
    )

    companion object {
        const val errorMessage = "ERROR"

        @JvmStatic
        fun launchArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Result.Error(0, "ERROR"),
                RealStateDetailsUIState.Error("[0] ERROR")
            ),
            Arguments.of(
                Result.Error(0, null),
                RealStateDetailsUIState.Error("[0] $errorMessage")
            ),
            Arguments.of(
                Result.Success(
                    value = dummyRealState(0)
                ),
                RealStateDetailsUIState.Content(
                    realState = dummyRealStateDetails(0)
                )
            ),
        )
    }

    @Test
    fun `when ViewModel is started, then initial state has to be loading`() = runTest {
        viewModel.state.value shouldBe RealStateDetailsUIState.Loading
    }

    @ParameterizedTest(
        name = "when repository response is \"{0}\", then it should become \"{1}\""
    )
    @MethodSource("launchArguments")
    fun `when ViewModel is launched, then it should handle the result properly`(
        getDetailsResult: Result<RealState>,
        expectedRealStateDetailsUIState: RealStateDetailsUIState,
    ) = runTest {
        MockBuilder().withGetRealStateDetails(getDetailsResult)

        viewModel.handle(RealStateDetailsAction.Launch("0"))

        viewModel.state.value shouldBe expectedRealStateDetailsUIState
    }

    inner class MockBuilder {

        init {
            coEvery { realStateDetailsResourcesMockk.genericError } returns errorMessage
        }

        fun withGetRealStateDetails(result: Result<RealState>) = apply {
            coEvery { realStateRepositoryMockk.getBy(any()) } returns result
        }
    }
}
