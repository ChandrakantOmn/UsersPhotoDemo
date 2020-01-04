package com.demo.userphotoalbum.data.local

import android.content.Context
import androidx.room.Room
import com.demo.userphotoalbum.utils.AppConstants

/**
 * Created by Chandra Kant on 4/1/20.
 */

class RoomHelper(context: Context) {
    private var context = context


    private val db = Room.databaseBuilder(context, RoomDB::class.java, AppConstants.DB_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    fun getDatabase(): RoomDB {
        return db
    }
}