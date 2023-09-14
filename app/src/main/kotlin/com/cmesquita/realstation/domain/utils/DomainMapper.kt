package com.cmesquita.realstation.domain.utils

interface DomainMapper<Model, DomainModel> {
    fun toDomain(model: Model): DomainModel
}
