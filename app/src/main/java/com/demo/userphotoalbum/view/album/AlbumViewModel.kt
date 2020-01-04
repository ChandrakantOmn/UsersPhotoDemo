package com.demo.userphotoalbum.view.album

import androidx.lifecycle.ViewModel
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.data.local.entities.Album
import com.demo.userphotoalbum.utils.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Chandra Kant on 4/1/20.
 */


class AlbumViewModel @Inject constructor(dataManager: DataManager) : ViewModel() {
    private val repo = AlbumRepo(dataManager)
    var stateLiveData: StateLiveData<List<Album>>? = StateLiveData()
    private lateinit var disposableObserver: DisposableObserver<List<Album>>

    fun getAlbums(id: Int?) {
        stateLiveData?.postLoading()
        disposableObserver = object : DisposableObserver<List<Album>>() {
            override fun onComplete() {
            }

            override fun onNext(response: List<Album>) {
                stateLiveData?.postSuccess(response)
            }

            override fun onError(e: Throwable) {
                stateLiveData?.postError(e)
            }
        }
        repo.getAlbums(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }
}