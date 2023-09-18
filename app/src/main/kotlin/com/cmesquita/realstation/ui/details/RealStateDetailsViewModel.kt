package com.cmesquita.realstation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmesquita.realstation.BuildConfig
import com.cmesquita.realstation.data.RealStateRepository
import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.ui.details.model.RealStateDetailsMapper
import com.cmesquita.realstation.utils.coroutines.AppCoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealStateDetailsViewModel @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val realStateDetailsMapper: RealStateDetailsMapper,
    private val realStateDetailsResources: RealStateDetailsResources,
    private val realStateRepository: RealStateRepository,
) : ViewModel() {

    private val _stateFlow =
        MutableStateFlow<RealStateDetailsUIState>(RealStateDetailsUIState.Loading)
    internal val state: StateFlow<RealStateDetailsUIState> = _stateFlow

    fun handle(action: RealStateDetailsAction) {
        when (action) {
            is RealStateDetailsAction.Launch -> fetchRealStateBy(action.id)
        }
    }

    private fun fetchRealStateBy(id: String) = viewModelScope.launch(appCoroutineDispatchers.io()) {
        val newState = when (val result = realStateRepository.getBy(id)) {
            is Result.Error -> RealStateDetailsUIState.Error(
                if (BuildConfig.DEBUG) {
                    "[${result.code}] ${result.message ?: realStateDetailsResources.genericError}"
                } else {
                    realStateDetailsResources.genericError
                },
            )

            is Result.Success -> {
                RealStateDetailsUIState.Content(
                    realState = realStateDetailsMapper.toUi(result.value)
                )
            }
        }

        _stateFlow.value = newState
    }
}
