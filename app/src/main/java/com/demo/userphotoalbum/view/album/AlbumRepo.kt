package com.demo.userphotoalbum.view.album

import android.util.Log
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.data.local.entities.Album
import io.reactivex.Observable

/**
 * Created by Chandra Kant on 4/1/20.
 */
class AlbumRepo(private val dataManager: DataManager) {
    fun getAlbums(): Observable<List<Album>> {
        val hasConnection = dataManager.networkUtils.isConnectedToInternet()
        var observableFromApi: Observable<List<Album>>? = null
        if (hasConnection) {
            observableFromApi = getAlbumFromServer()
        }
        val observableFromDb = getAlbumsFromDb()
        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    private fun getAlbumsFromDb(): Observable<List<Album>> {
        return dataManager.roomHelper.getDatabase().albumDAO().getAlbums()
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }

    }

    private fun getAlbumFromServer(): Observable<List<Album>>? {
       return Observable.concatArrayEager(dataManager.apiHelper.getAlbums())
           .doOnNext {
               dataManager.roomHelper.getDatabase().albumDAO().addAlbum(it)
               Log.e("REPOSITORY API *** ", it.size.toString())

           }
    }

}
