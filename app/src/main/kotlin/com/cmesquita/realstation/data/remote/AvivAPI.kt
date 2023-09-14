package com.cmesquita.realstation.data.remote

import com.cmesquita.realstation.data.remote.model.RealStateDTO
import com.cmesquita.realstation.data.remote.model.RealStateListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AvivAPI {

    companion object {
        const val BASE_URL = "https://gsl-apps-technical-test.dignp.com/"

        const val ENDPOINT_LISTINGS = "listings"

        const val QUERY_ID = "id"
    }

    @GET("$ENDPOINT_LISTINGS.json")
    suspend fun getRealStateList(): Response<RealStateListDTO>

    @GET("$ENDPOINT_LISTINGS/{id}.json")
    suspend fun getRealStateBy(@Path(QUERY_ID) id: String): Response<RealStateDTO>
}
