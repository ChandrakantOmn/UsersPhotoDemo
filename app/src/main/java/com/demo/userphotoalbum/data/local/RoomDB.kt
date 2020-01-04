package com.demo.userphotoalbum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.userphotoalbum.data.local.dao.AlbumDAO
import com.demo.userphotoalbum.data.local.dao.PhotosDAO
import com.demo.userphotoalbum.data.local.dao.UserDAO
import com.demo.userphotoalbum.data.local.entities.*

/**
 * Created by Chandra Kant on 4/1/20.
 */
@Database(
    entities = [User::class, Album::class, Photo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AddressConverter::class, GeoConverter::class, CompanyConverter::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun albumDAO(): AlbumDAO
    abstract fun photosDAO(): PhotosDAO

}