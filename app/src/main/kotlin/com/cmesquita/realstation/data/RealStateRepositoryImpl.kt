package com.cmesquita.realstation.data

import com.cmesquita.realstation.data.model.Result
import com.cmesquita.realstation.data.remote.AvivAPI
import com.cmesquita.realstation.data.remote.NoConnectivityException
import com.cmesquita.realstation.data.remote.model.RealStateDTOMapper
import com.cmesquita.realstation.data.remote.model.RealStateListDTOMapper
import com.cmesquita.realstation.domain.model.RealState
import retrofit2.Response
import javax.inject.Inject

private const val CODE_NOCONNECTIVITYEXCEPTION = -1
private const val CODE_NULLPOINTEREXCEPTION = 0

class RealStateRepositoryImpl @Inject constructor(
    private val avivAPI: AvivAPI,
    private val realStateDTOMapper: RealStateDTOMapper,
    private val realStateListDTOMapper: RealStateListDTOMapper,
) : RealStateRepository {

    override suspend fun getList(): Result<List<RealState>> {
        return handleResponse(
            response = { avivAPI.getRealStateList() },
            transform = { realStateListDTOMapper.toDomain(it) },
        )
    }

    override suspend fun getBy(id: String): Result<RealState> {
        return handleResponse(
            response = { avivAPI.getRealStateBy(id) },
            transform = { realStateDTOMapper.toDomain(it) },
        )
    }

    @Suppress("TooGenericExceptionCaught")
    private suspend fun <S, T> handleResponse(
        response: suspend () -> Response<S>,
        transform: (S) -> T
    ): Result<T> {
        try {
            val responseValue = response()
            val responseBody = responseValue.body()

            return if (responseValue.isSuccessful && responseBody != null) {
                try {
                    Result.Success(transform(responseBody))
                } catch (e: NullPointerException) {
                    Result.Error(code = CODE_NULLPOINTEREXCEPTION, message = e.localizedMessage)
                }
            } else {
                Result.Error(
                    code = responseValue.code(), message = responseValue.errorBody()?.string()
                )
            }
        } catch (e: NoConnectivityException) {
            return Result.Error(code = CODE_NOCONNECTIVITYEXCEPTION, message = e.localizedMessage)
        }
    }
}
