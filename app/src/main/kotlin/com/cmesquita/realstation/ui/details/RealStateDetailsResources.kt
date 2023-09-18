package com.cmesquita.realstation.ui.details

import android.content.res.Resources
import com.cmesquita.realstation.R
import javax.inject.Inject

class RealStateDetailsResources @Inject constructor(
    private val resources: Resources,
) {
    val genericError: String
        get() = resources.getString(R.string.list_message_generic_error)
}
