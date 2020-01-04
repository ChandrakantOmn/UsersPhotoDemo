package com.demo.userphotoalbum.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.userphotoalbum.data.local.entities.Photo
import io.reactivex.Flowable

/**
 * Created by Chandra Kant on 4/1/20.
 */
@Dao
interface PhotosDAO {

    @Query("SELECT * FROM photo WHERE albumId=:id ")
    fun getPhotos(id: Int?): Flowable<List<Photo>>


    @Query("DELETE FROM photo")
    fun deletePhotos()


    @Query("DELETE  FROM photo WHERE title = :name")
    fun deleteRow(name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(photo: List<Photo>)

    @Query("SELECT * FROM photo ")
    fun getPhotosListData(): LiveData<List<Photo>>

}