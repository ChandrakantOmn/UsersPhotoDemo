package com.demo.userphotoalbum.view.user

import androidx.lifecycle.ViewModel
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.data.local.entities.User
import com.demo.userphotoalbum.utils.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Chandra Kant on 4/1/20.
 */


class UserViewModel @Inject constructor(dataManager: DataManager) : ViewModel() {
    private val repo = UserRepo(dataManager)
    private lateinit var disposableObserver: DisposableObserver<List<User>>
    var stateLiveData: StateLiveData<List<User>>? = StateLiveData()

    fun getUsers() {
        stateLiveData?.postLoading()
        disposableObserver = object : DisposableObserver<List<User>>() {
            override fun onComplete() {
            }

            override fun onNext(response: List<User>) {
                stateLiveData?.postSuccess(response)

            }

            override fun onError(e: Throwable) {
                stateLiveData?.postError(e)
            }
        }
        repo.getUsers()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)

    }
}