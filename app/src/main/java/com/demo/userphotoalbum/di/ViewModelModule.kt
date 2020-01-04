package com.demo.userphotoalbum.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.userphotoalbum.view.base.BaseViewModelFactory
import com.demo.userphotoalbum.view.album.AlbumViewModel
import com.demo.userphotoalbum.view.photo.PhotosViewModel
import com.demo.userphotoalbum.view.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Chandra Kant on 4/1/20.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun userViewModel(viewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumViewModel::class)
    internal abstract fun ulbumViewModel(viewModel: AlbumViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel::class)
    internal abstract fun photosViewModel(viewModel: PhotosViewModel): ViewModel
}