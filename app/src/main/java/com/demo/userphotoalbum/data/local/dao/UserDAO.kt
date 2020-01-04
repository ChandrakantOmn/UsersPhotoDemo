package com.demo.userphotoalbum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.userphotoalbum.data.local.entities.User
import io.reactivex.Single

/**
 * Created by Chandra Kant on 4/1/20.
 */
@Dao
interface UserDAO {

    @Query("SELECT * FROM user ")
    fun getUsers(): Single<List<User>>


    @Query("DELETE FROM user")
    fun deleteUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(users: List<User>)

}