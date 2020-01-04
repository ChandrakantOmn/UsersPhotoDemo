package com.demo.userphotoalbum.di

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import com.demo.userphotoalbum.BuildConfig
import com.demo.userphotoalbum.data.local.RoomHelper
import com.demo.userphotoalbum.data.remote.ApiHelper
import com.demo.userphotoalbum.data.remote.IApiService
import com.demo.userphotoalbum.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@NonNull context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .addInterceptor(provideHttpLoggingInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(provideOkHttpClient(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@NonNull context: Context): IApiService {
        return provideRetrofit(context).create(IApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRoomHelper(@NonNull context: Context): RoomHelper {
        return RoomHelper(context)
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(@NonNull context: Context): NetworkUtils {
        return NetworkUtils(context)
    }

    @Provides
    @Singleton
    fun provideApiHelper(@NonNull apiService: IApiService): ApiHelper {
        return ApiHelper(apiService)
    }
}