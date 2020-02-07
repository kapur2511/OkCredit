/*
 * Copyright (C) 04-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.room

import com.okcredit.test.mvp.view.viewmodel.ListItem
import com.okcredit.test.mvp.view.viewmodel.NewsViewModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * CACHE MECHANISM
---------------

It is designed in such a way that once a particular news type response has been received it'll cache that with the help of room. Once the user goes to background and then comes back into the app then the cached data is used. If it's a fresh launch all the cached data is deleted and fresh cache is stored from the response. If you switch between the news types then it'll check if the cached data is there for that news type, if it's there then it'll use that data else a new request will be made.
 * */
class CacheManager @Inject constructor(val dbManager: DBManager){

    fun clearAllCache(){
        Observable.just(dbManager.getAppDatabase()).flatMap(Function<AppDatabase?, ObservableSource<String>> {
            it.clearAllTables()
            return@Function Observable.just("")
        }).subscribeOn(Schedulers.io()).subscribe()
    }

    fun clearSelectiveCache(newsType: String){
        Observable.just(dbManager.getAppDatabase()?.getDAO()).flatMap(Function<NewsDAO?, ObservableSource<String>>
        {
            it.deleteNewsType(newsType)
            return@Function Observable.just("")
        }).subscribeOn(Schedulers.io()).subscribe()
    }

    fun saveNewsData(listItems: List<ListItem>){
        Observable.just(dbManager.getAppDatabase()?.getDAO()).flatMap(Function<NewsDAO?, ObservableSource<String>> {
            t: NewsDAO -> t.saveNewsViewModels(listItems as List<NewsViewModel>)
            return@Function Observable.just("")
        }).subscribeOn(Schedulers.io()).subscribe()
    }

    fun getNewsData(newsType: String, cacheObserver: Observer<List<NewsViewModel>>){
        Observable.just(dbManager.getAppDatabase()?.getDAO())
                .flatMap(Function<NewsDAO?, ObservableSource<List<NewsViewModel>>> {
                    return@Function Observable.just(it.getNewsViewModels(newsType))
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(cacheObserver)
    }
}