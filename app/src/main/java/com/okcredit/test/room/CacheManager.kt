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