package com.demo.userphotoalbum.di
import com.demo.userphotoalbum.view.album.AlbumActivity
import com.demo.userphotoalbum.view.photo.PhotosActivity
import com.demo.userphotoalbum.view.user.UsersActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeUsersActivity(): UsersActivity

    @ContributesAndroidInjector
    internal abstract fun contributeAlbumActivity(): AlbumActivity

    @ContributesAndroidInjector
    internal abstract fun contributePhotosActivity(): PhotosActivity

}