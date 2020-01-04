package com.demo.userphotoalbum.view.photo

import android.util.Log
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.data.local.entities.Photo
import io.reactivex.Observable

/**
 * Created by Chandra Kant on 4/1/20.
 */
class PhotosRepo(private val dataManager: DataManager) {
    fun getPhotos(id: Int?): Observable<List<Photo>> {
        val hasConnection = dataManager.networkUtils.isConnectedToInternet()
        var observableFromApi: Observable<List<Photo>>? = null
        if (hasConnection) {
            observableFromApi = getPhotoFromServer()
        }
        val observableFromDb = getPhotosFromDb(id)
        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    private fun getPhotosFromDb(id: Int?): Observable<List<Photo>> {
        return dataManager.roomHelper.getDatabase().photosDAO().getPhotos(id)
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }

    }

    private fun getPhotoFromServer(): Observable<List<Photo>>? {
        return Observable.concatArrayEager(dataManager.apiHelper.getPhotos())
            .doOnNext {
                dataManager.roomHelper.getDatabase().photosDAO().addPhotos(it)
                Log.e("REPOSITORY API *** ", it.size.toString())

            }
    }

}
