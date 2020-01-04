package com.demo.userphotoalbum.data.remote

import com.demo.userphotoalbum.data.local.entities.Album
import com.demo.userphotoalbum.data.local.entities.Photo
import com.demo.userphotoalbum.data.local.entities.User
import io.reactivex.Observable
import retrofit2.http.GET

interface IApiService {

    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("albums")
    fun getAlbums(): Observable<List<Album>>

    @GET("photos")
    fun getPhotos(): Observable<List<Photo>>

}