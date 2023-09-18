package com.cmesquita.realstation.data.remote

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val mContext: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoConnectivityException()
        }

        val request: Request = chain.request().newBuilder().build()

        return chain.proceed(request)
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}
