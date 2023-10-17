package com.cmesquita.realstation.domain

import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.domain.model.RealState

interface RealStateRepository {
    suspend fun getList(): Result<List<RealState>>
    suspend fun getBy(id: String): Result<RealState>
}
