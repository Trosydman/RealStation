package com.cmesquita.realstation.ui.list

import com.cmesquita.realstation.ui.list.model.RealStateListItem

sealed class RealStateListUIState {
    data object Loading : RealStateListUIState()

    open class Info(open val message: String) : RealStateListUIState() {

        data class Error(override val message: String) : Info(message)

        data class Empty(override val message: String) : Info(message)
    }

    data class Content(val realStates: List<RealStateListItem>) : RealStateListUIState()
}

sealed class RealStateListAction {
    data object Launch : RealStateListAction()
}

sealed class RealStateListEvent {
    data class NavigateToDetails(val id: String) : RealStateListEvent()
}
