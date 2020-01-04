package com.demo.userphotoalbum.data

import com.demo.userphotoalbum.data.local.RoomHelper
import com.demo.userphotoalbum.data.remote.ApiHelper
import com.demo.userphotoalbum.utils.NetworkUtils
import javax.inject.Inject

/**
 * Created by Chandra Kant on 4/1/20.
 */

class DataManager @Inject constructor(
    roomHelper: RoomHelper, apiHelper: ApiHelper, networkUtils: NetworkUtils
) : IDataManager {
    val roomHelper = roomHelper
    val apiHelper = apiHelper
    val networkUtils= networkUtils
}

interface IDataManager {
}