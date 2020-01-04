package com.demo.userphotoalbum.view.photo

import androidx.lifecycle.ViewModel
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.data.local.entities.Photo
import com.demo.userphotoalbum.utils.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Chandra Kant on 4/1/20.
 */


class PhotosViewModel @Inject constructor(dataManager: DataManager) : ViewModel() {
    private val repo = PhotosRepo(dataManager)
    var stateLiveData: StateLiveData<List<Photo>>? = StateLiveData()
    private lateinit var disposableObserver: DisposableObserver<List<Photo>>

    fun getPhotos(id: Int?) {
        stateLiveData?.postLoading()
        disposableObserver = object : DisposableObserver<List<Photo>>() {
            override fun onComplete() {
            }
            override fun onNext(response: List<Photo>) {
                stateLiveData?.postSuccess(response)
            }
            override fun onError(e: Throwable) {
                stateLiveData?.postError(e)
            }
        }
        repo.getPhotos(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)

/*
        repo.getPhotos()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .flatMapIterable {
                Log.d("Data", it.size.toString())
                return@flatMapIterable it
            }
            .filter {
                Log.d("Data filter", it.albumId.toString())
                it.albumId!! == id
            }
            .toList()
            .subscribe(object : SingleObserver<List<Photo>> {
                override fun onSuccess(response: List<Photo>) {
                    Log.d("Data", response.size.toString())
                    stateLiveData?.postSuccess(response)
                }
                override fun onSubscribe(d: Disposable) {
                    Log.d("Data", "onSubscribe")

                }
                override fun onError(e: Throwable) {
                    stateLiveData?.postError(e)
                    Log.d("Data", e.message)
                }
            })
*/
    }
}