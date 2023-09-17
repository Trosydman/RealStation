package com.cmesquita.realstation.ui.list

import android.content.res.Resources
import com.cmesquita.realstation.R
import javax.inject.Inject

class RealStateListResources @Inject constructor(
    private val resources: Resources,
) {
    val genericError: String
        get() = resources.getString(R.string.list_message_generic_error)

    val emptyResult: String
        get() = resources.getString(R.string.list_message_generic_error)
}
