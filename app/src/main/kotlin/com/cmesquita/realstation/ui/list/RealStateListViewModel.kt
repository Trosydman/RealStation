package com.cmesquita.realstation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmesquita.realstation.BuildConfig
import com.cmesquita.realstation.data.RealStateRepository
import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.ui.list.model.RealStateListItemMapper
import com.cmesquita.realstation.utils.coroutines.AppCoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealStateListViewModel @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val realStateListResources: RealStateListResources,
    private val realStateListItemMapper: RealStateListItemMapper,
    private val realStateRepository: RealStateRepository,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<RealStateListUIState>(RealStateListUIState.Loading)
    internal val state: StateFlow<RealStateListUIState> = _stateFlow

    private val _eventChannel = Channel<RealStateListEvent>(Channel.BUFFERED)
    internal val eventsFlow = _eventChannel.receiveAsFlow()

    fun handle(action: RealStateListAction) {
        when (action) {
            is RealStateListAction.Launch -> fetchRealStates()
        }
    }

    private fun fetchRealStates() = viewModelScope.launch(appCoroutineDispatchers.io()) {
        val newState = when (val result = realStateRepository.getList()) {
            is Result.Error -> RealStateListUIState.Info.Error(
                if (BuildConfig.DEBUG) {
                    "[${result.code}] ${result.message ?: realStateListResources.genericError}"
                } else {
                    realStateListResources.genericError
                },
            )

            is Result.Success -> {
                val realStates = result.value

                if (realStates.isEmpty()) {
                    RealStateListUIState.Info.Empty(realStateListResources.emptyResult)
                } else {
                    RealStateListUIState.Content(
                        realStates = realStates.map(realStateListItemMapper::toUi)
                    )
                }
            }
        }

        _stateFlow.value = newState
    }
}
