package com.cmesquita.realstation.domain.utils

interface UIMapper<Model, UiModel> {
    fun toUi(model: Model): UiModel
}
