package com.cmesquita.realstation.ui.list

import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.domain.RealStateRepository
import com.cmesquita.realstation.domain.model.RealState
import com.cmesquita.realstation.dummy.dummyRealState
import com.cmesquita.realstation.dummy.dummyRealStateListItem
import com.cmesquita.realstation.fake.FakeCoroutineDispatchers
import com.cmesquita.realstation.ui.list.model.RealStateListItem
import com.cmesquita.realstation.ui.list.model.RealStateListItemMapper
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RealStateListViewModelShould {
    private val realStateListResourcesMockk = mockk<RealStateListResources>()
    private val realStateRepositoryMockk = mockk<RealStateRepository>()

    private val viewModel = RealStateListViewModel(
        appCoroutineDispatchers = FakeCoroutineDispatchers(),
        realStateListResources = realStateListResourcesMockk,
        realStateListItemMapper = RealStateListItemMapper(),
        realStateRepository = realStateRepositoryMockk,
    )

    companion object {
        const val emptyResultMessage = "emptyResult"
        const val errorMessage = "ERROR"

        @JvmStatic
        fun launchArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Result.Error(0, "ERROR"),
                RealStateListUIState.Info.Error("[0] ERROR")
            ),
            Arguments.of(
                Result.Error(0, null),
                RealStateListUIState.Info.Error("[0] $errorMessage")
            ),
            Arguments.of(
                Result.Success(
                    value = emptyList<RealStateListItem>()
                ),
                RealStateListUIState.Info.Empty(emptyResultMessage)
            ),
            Arguments.of(
                Result.Success(
                    value = List(3) { dummyRealState(it.toLong()) }
                ),
                RealStateListUIState.Content(
                    realStates = List(3) { dummyRealStateListItem(it.toLong()) }
                )
            ),
        )
    }

    @Test
    fun `when ViewModel is started, then initial state has to be loading`() = runTest {
        viewModel.state.value shouldBe RealStateListUIState.Loading
    }

    @ParameterizedTest(
        name = "when repository response is \"{0}\", then it should become \"{1}\""
    )
    @MethodSource("launchArguments")
    fun `when ViewModel is launched, then it should handle the result properly`(
        getListResult: Result<List<RealState>>,
        expectedRealStateListUIState: RealStateListUIState,
    ) = runTest {
        MockBuilder().withGetRealStateList(getListResult)

        viewModel.handle(RealStateListAction.Launch)

        viewModel.state.value shouldBe expectedRealStateListUIState
    }

    inner class MockBuilder {

        init {
            coEvery { realStateListResourcesMockk.emptyResult } returns emptyResultMessage
            coEvery { realStateListResourcesMockk.genericError } returns errorMessage
        }

        fun withGetRealStateList(result: Result<List<RealState>>) = apply {
            coEvery { realStateRepositoryMockk.getList() } returns result
        }
    }
}
