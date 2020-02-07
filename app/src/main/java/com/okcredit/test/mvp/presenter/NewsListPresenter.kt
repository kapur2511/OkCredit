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

package com.okcredit.test.mvp.presenter

import android.util.Log


import com.okcredit.test.api.NewsAPI
import com.okcredit.test.mvp.model.MultimediaModel
import com.okcredit.test.mvp.model.NewsModel
import com.okcredit.test.mvp.model.ResponseModel
import com.okcredit.test.mvp.view.renderer.NewsListRenderer
import com.okcredit.test.mvp.view.viewmodel.ListItem
import com.okcredit.test.mvp.view.viewmodel.NewsViewModel

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

import com.okcredit.test.utils.Constants.API_KEY_PARAM
import com.okcredit.test.utils.Constants.NEWS_TYPE_PARAM
import java.text.SimpleDateFormat

/**
 * Presenter for NewsListFragment for handling the request and response.
 * */
class NewsListPresenter @Inject constructor() : BasePresenter<NewsListRenderer>() {

    override fun loadData(stringObjectHashMap: Map<String, Any>) {
        Log.d(TAG, "Loading News")
        newsType = stringObjectHashMap[NEWS_TYPE_PARAM].toString().substring(0,stringObjectHashMap[NEWS_TYPE_PARAM].toString().length-5)
        newsAPI.loadNews(stringObjectHashMap[NEWS_TYPE_PARAM].toString(), stringObjectHashMap[API_KEY_PARAM].toString())
                .subscribeOn(Schedulers.io())
                .compose(NewsListTransformer(
                        
                ))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(NewsListObserver())
    }

    private inner class NewsListTransformer : ObservableTransformer<Response<ResponseModel>, List<ListItem>> {


        override fun apply(responseObservable: Observable<Response<ResponseModel>>): ObservableSource<List<ListItem>> {
            return responseObservable.filter(Predicate<Response<ResponseModel>> {
                return@Predicate it.body() != null && it.body()?.results != null && it.body()!!.results.isNotEmpty()
            }).flatMapIterable { responseObservable ->  return@flatMapIterable responseObservable.body()!!.results}
                    .flatMap(Function <NewsModel, Observable<ListItem>>{
                        var thumbnailURL = ""
                        var coverImageURL = ""
                        for (i in 0 until it.multimedia.size) {
                            val format = it.multimedia[i].format
                            if (format.equals("mediumThreeByTwo210", ignoreCase = true)) {
                                coverImageURL = it.multimedia[i].url
                            } else if (format.equals("Standard Thumbnail", ignoreCase = true) || format.equals("thumbLarge", ignoreCase = true)) {
                                thumbnailURL = it.multimedia[i].url
                            }
                        }

                        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        val output = SimpleDateFormat("dd-MM-yyyy HH:mm")
                        val d = sdf.parse(it.published_date)
                        val formattedTime = output.format(d)

                        val newsViewModel = NewsViewModel(it.title, it.byline, thumbnailURL, coverImageURL, it.abstract, formattedTime, it.url, newsType)
                        return@Function Observable.just(newsViewModel)
                    }).toList().flatMapObservable { Observable.just(it) }
        }
    }


    private inner class NewsListObserver : Observer<List<ListItem>> {


        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(newsViewModels: List<ListItem>) {
            view!!.renderNewsList(newsViewModels)
        }


        override fun onError(e: Throwable) {
            view!!.renderError()
        }

        override fun onComplete() {
            Log.d(TAG, "ON_COMPLETE")
        }
    }

    lateinit var newsType:String

    @Inject
    lateinit var newsAPI: NewsAPI
}
