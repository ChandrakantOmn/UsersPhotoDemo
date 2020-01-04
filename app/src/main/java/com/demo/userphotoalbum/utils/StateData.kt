package com.demo.userphotoalbum.utils

/**
 * Created by Chandra Kant on 4/1/20.
 */
class StateData<T> {
    var status: DataStatus
        private set
    var data: T?
        private set
    var error: Throwable?
        private set

    fun loading(): StateData<T> {
        status = DataStatus.LOADING
        data = null
        error = null
        return this
    }

    fun success(data: T): StateData<T> {
        status = DataStatus.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun error(error: Throwable): StateData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error
        return this
    }

    fun complete(): StateData<T> {
        status = DataStatus.COMPLETE
        return this
    }

    enum class DataStatus {
        CREATED, SUCCESS, ERROR, LOADING, COMPLETE
    }

    init {
        status = DataStatus.CREATED
        data = null
        error = null
    }
}