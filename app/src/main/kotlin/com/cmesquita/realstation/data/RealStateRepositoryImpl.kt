package com.cmesquita.realstation.data

import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.data.remote.AvivAPI
import com.cmesquita.realstation.data.remote.model.RealStateDTOMapper
import com.cmesquita.realstation.data.remote.model.RealStateListDTOMapper
import com.cmesquita.realstation.domain.model.RealState
import retrofit2.Response
import javax.inject.Inject

private const val CODE_NULLPOINTEREXCEPTION = 0

class RealStateRepositoryImpl @Inject constructor(
    private val avivAPI: AvivAPI,
    private val realStateDTOMapper: RealStateDTOMapper,
    private val realStateListDTOMapper: RealStateListDTOMapper,
) : RealStateRepository {

    override suspend fun getList(): Result<List<RealState>> {
        return handleResponse(
            response = avivAPI.getRealStateList(),
            transform = { realStateListDTOMapper.toDomain(it) }
        )
    }

    override suspend fun getBy(id: String): Result<RealState> {
        return handleResponse(
            response = avivAPI.getRealStateBy(id),
            transform = { realStateDTOMapper.toDomain(it) }
        )
    }

    @Suppress("TooGenericExceptionCaught")
    private fun <S, T> handleResponse(response: Response<S>, transform: (S) -> T): Result<T> {
        val responseBody = response.body()

        return if (response.isSuccessful && responseBody != null) {
            try {
                Result.Success(transform(responseBody))
            } catch (e: NullPointerException) {
                Result.Error(code = CODE_NULLPOINTEREXCEPTION, message = e.localizedMessage)
            }
        } else {
            Result.Error(
                code = response.code(), message = response.errorBody()?.string()
            )
        }
    }
}
