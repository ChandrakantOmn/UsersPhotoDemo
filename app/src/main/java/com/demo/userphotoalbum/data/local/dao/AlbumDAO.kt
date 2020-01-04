package com.demo.userphotoalbum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.userphotoalbum.data.local.entities.Album
import io.reactivex.Flowable

/**
 * Created by Chandra Kant on 4/1/20.
 */
@Dao
interface AlbumDAO {

    @Query("SELECT * FROM album ")
    fun getAlbums(): Flowable<List<Album>>


    @Query("DELETE FROM album")
    fun deleteAlbums()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbum(album: List<Album>)

}