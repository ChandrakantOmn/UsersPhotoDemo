package com.demo.userphotoalbum.view.album

import android.util.Log
import androidx.lifecycle.ViewModel
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.data.local.entities.Album
import com.demo.userphotoalbum.utils.StateLiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Chandra Kant on 4/1/20.
 */


class AlbumViewModel @Inject constructor(dataManager: DataManager) : ViewModel() {
    private val repo = AlbumRepo(dataManager)
    var stateLiveData: StateLiveData<List<Album>>? = StateLiveData()

    fun getAlbums(id: Int?) {
        stateLiveData?.postLoading()
        val l = repo.getAlbums()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .flatMapIterable {
                Log.d("Data", it.size.toString())
                stateLiveData?.postSuccess(it)
                return@flatMapIterable it
            }
            .filter {
                Log.d("Data filter", it.userId.toString())
                it.userId!! == id
            }
            .toList()
            .subscribe(object : SingleObserver<List<Album>> {
                override fun onSuccess(response: List<Album>) {
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


    }
}