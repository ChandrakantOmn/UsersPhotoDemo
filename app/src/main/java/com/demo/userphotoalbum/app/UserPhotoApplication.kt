package com.demo.userphotoalbum.app

import android.content.Context
import com.demo.userphotoalbum.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class UserPhotosApplication : DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()
    lateinit var context: Context

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }


}
