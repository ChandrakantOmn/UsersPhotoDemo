package com.demo.userphotoalbum.view.user

import android.util.Log
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.data.local.entities.User
import io.reactivex.Observable

/**
 * Created by Chandra Kant on 4/1/20.
 */
class UserRepo(private val dataManager: DataManager) {
    fun getUsers(): Observable<List<User>> {
        val hasConnection = dataManager.networkUtils.isConnectedToInternet()
        var observableFromApi: Observable<List<User>>? = null
        if (hasConnection) {
            observableFromApi = getUserFromServer()
        }
        val observableFromDb = getUsersFromDb()
        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    private fun getUsersFromDb(): Observable<List<User>> {
        return dataManager.roomHelper.getDatabase().userDAO().getUsers()
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }

    }

    private fun getUserFromServer(): Observable<List<User>>? {
       return Observable.concatArrayEager(dataManager.apiHelper.getUsers())
           .doOnNext {
               dataManager.roomHelper.getDatabase().userDAO().addUser(it)
               Log.e("REPOSITORY API *** ", it.size.toString())

           }
    }

}
