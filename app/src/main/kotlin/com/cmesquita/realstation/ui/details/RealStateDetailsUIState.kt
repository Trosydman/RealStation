package com.cmesquita.realstation.ui.details

import com.cmesquita.realstation.ui.details.model.RealStateDetails

sealed class RealStateDetailsUIState {

    data object Loading : RealStateDetailsUIState()

    data class Error(val message: String) : RealStateDetailsUIState()

    data class Content(val realState: RealStateDetails) : RealStateDetailsUIState()
}

sealed class RealStateDetailsAction {
    data class Launch(val id: String) : RealStateDetailsAction()
}
