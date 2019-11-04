/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

/*
 * @author: kshitiz.kapur
 */

package com.okcredit.test.di.modules

import com.okcredit.test.api.NewsAPI
import com.okcredit.test.networking.RetrofitInterceptor
import com.okcredit.test.utils.Constants

import java.io.File

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofitClient(retrofitInterceptor: RetrofitInterceptor, cacheFile: File): Retrofit {

        val cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(retrofitInterceptor)
                .cache(cache)
                .build()


        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideInterceptor(): RetrofitInterceptor {
        return RetrofitInterceptor()
    }

    @Provides
    @Singleton
    internal fun provideNewsService(retrofit: Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }
}
