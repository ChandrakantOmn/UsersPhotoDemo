package com.demo.userphotoalbum.data.remote

import com.demo.userphotoalbum.data.local.entities.Album
import com.demo.userphotoalbum.data.local.entities.Photo
import com.demo.userphotoalbum.data.local.entities.User
import io.reactivex.Observable

/**
 * Created by Chandra Kant on 4/1/20.
 */

class ApiHelper(private val apiService: IApiService) {
    fun getUsers(): Observable<List<User>> {
      return apiService.getUsers()
    }
    fun getAlbums(): Observable<List<Album>> {
        return apiService.getAlbums()
    }
    fun getPhotos(): Observable<List<Photo>> {
        return apiService.getPhotos()
    }

}
