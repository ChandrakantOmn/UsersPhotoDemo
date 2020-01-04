package com.demo.userphotoalbum.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Chandra Kant on 4/1/20.
 */


class NetworkUtils ( val context: Context) {
    fun isConnectedToInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}