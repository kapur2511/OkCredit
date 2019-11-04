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

package com.okcredit.test.mvp.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.okcredit.test.R
import com.okcredit.test.mvp.presenter.NewsListPresenter
import com.okcredit.test.mvp.view.activities.NewsDetailActivity
import com.okcredit.test.mvp.view.adapters.NewsListAdapter
import com.okcredit.test.mvp.view.renderer.NewsListRenderer
import com.okcredit.test.mvp.view.viewmodel.ListItem
import com.okcredit.test.mvp.view.viewmodel.NewsViewModel
import com.okcredit.test.room.CacheManager
import com.okcredit.test.utils.ClickListener
import com.okcredit.test.utils.CommonUtils
import com.okcredit.test.utils.Constants.API_KEY
import com.okcredit.test.utils.Constants.API_KEY_PARAM
import com.okcredit.test.utils.Constants.NEWS_ABSTRACT_PARAM
import com.okcredit.test.utils.Constants.NEWS_ARTICLE_LINK_PARAM
import com.okcredit.test.utils.Constants.NEWS_AUTHOR_PARAM
import com.okcredit.test.utils.Constants.NEWS_COVER_URL_PARAM
import com.okcredit.test.utils.Constants.NEWS_DEFAULT_TOPIC
import com.okcredit.test.utils.Constants.NEWS_PUBLISH_PARAM
import com.okcredit.test.utils.Constants.NEWS_TITLE_PARAM
import com.okcredit.test.utils.Constants.NEWS_TYPE_PARAM
import com.okcredit.test.utils.Constants.NO_CACHE_AND_INTERNET
import com.okcredit.test.utils.Constants.SHOULD_NOT_USE_CACHE
import com.okcredit.test.utils.Constants.SHOULD_USE_CACHE
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject


class NewsListFragment : RecyclerFragment<NewsListPresenter, NewsListRenderer, NewsListAdapter<ListItem>>(), NewsListRenderer, ClickListener {

    init {
        layoutId = R.layout.fragment_news_list
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_news_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_refresh -> {
                forceLoad = true
                reloadData()
            }
            R.id.action_dialog -> createDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createDialog(){
        var alertDialog: AlertDialog.Builder = AlertDialog.Builder(activity)
        alertDialog.setTitle("Choose a news type: ")
        alertDialog.setItems(R.array.news_types, DialogInterface.OnClickListener{ dialog, which ->
            when(which){
                0 -> {
                    sharedPreferences.edit().putString(NEWS_DEFAULT_TOPIC, "world").apply()
                    currentDefaultTopic = "world"
                    (activity as AppCompatActivity).supportActionBar?.title = "${currentDefaultTopic.capitalize()} News"
                    loadData(presenter)
                    dialog.dismiss()
                }

                1-> {
                    sharedPreferences.edit().putString(NEWS_DEFAULT_TOPIC, "science").apply()
                    currentDefaultTopic = "science"
                    (activity as AppCompatActivity).supportActionBar?.title = "${currentDefaultTopic.capitalize()} News"
                    loadData(presenter)
                    dialog.dismiss()
                }

                2-> {
                    sharedPreferences.edit().putString(NEWS_DEFAULT_TOPIC, "technology").apply()
                    currentDefaultTopic = "technology"
                    (activity as AppCompatActivity).supportActionBar?.title = "${currentDefaultTopic.capitalize()} News"
                    loadData(presenter)
                    dialog.dismiss()
                }

                3-> {
                    sharedPreferences.edit().putString(NEWS_DEFAULT_TOPIC, "business").apply()
                    currentDefaultTopic = "business"
                    (activity as AppCompatActivity).supportActionBar?.title = "${currentDefaultTopic.capitalize()} News"
                    loadData(presenter)
                    dialog.dismiss()
                }

                4-> {
                    sharedPreferences.edit().putString(NEWS_DEFAULT_TOPIC, "movies").apply()
                    currentDefaultTopic = "movies"
                    (activity as AppCompatActivity).supportActionBar?.title = "${currentDefaultTopic.capitalize()} News"
                    loadData(presenter)
                    dialog.dismiss()
                }

                5-> {
                    sharedPreferences.edit().putString(NEWS_DEFAULT_TOPIC, "travel").apply()
                    currentDefaultTopic = "travel"
                    (activity as AppCompatActivity).supportActionBar?.title = "${currentDefaultTopic.capitalize()} News"
                    loadData(presenter)
                    dialog.dismiss()
                }
            }
        })
        alertDialog.setCancelable(false)
        alertDialog.create().show()
    }

    override fun loadData(presenter: NewsListPresenter) {
        if(currentDefaultTopic.isNullOrEmpty())
            createDialog()
        else{
            switchUI(false,true,false)
            if(!fromBG || (forceLoad!=null && forceLoad!!)){
                cacheManager.clearSelectiveCache(currentDefaultTopic)
                forceLoad = false
                fromBG = true
                stringObjectHashMap[NEWS_TYPE_PARAM] = "$currentDefaultTopic.json"
                stringObjectHashMap[API_KEY_PARAM] = API_KEY
                presenter.loadData(stringObjectHashMap)
            }else{
                cacheManager.getNewsData(currentDefaultTopic, CacheObserver())
            }
        }
    }



    override fun renderNewsList(listItems: List<ListItem>) {
        if(adapter!=null){
            adapter!!.refactorItems(listItems)
            switchUI(true, false, false)
            cacheManager.saveNewsData(listItems)
        }else
            switchUI(false, false, true)

    }

    override fun renderError() {
        switchUI(false, false, true)
    }


    override fun onClick(listItem: ListItem) {
        if (listItem is NewsViewModel) {
            val (title, author, _, coverImageUrl, abstract, publishDate, articleLink) = listItem
            val intent = Intent(activity, NewsDetailActivity::class.java)
            intent.putExtra(NEWS_TITLE_PARAM, title)
            intent.putExtra(NEWS_AUTHOR_PARAM, author)
            intent.putExtra(NEWS_PUBLISH_PARAM, publishDate)
            intent.putExtra(NEWS_ABSTRACT_PARAM, abstract)
            intent.putExtra(NEWS_ARTICLE_LINK_PARAM, articleLink)
            intent.putExtra(NEWS_COVER_URL_PARAM, coverImageUrl)
            startActivity(intent)
        }
    }

    override fun setupRecyclerViewAdapter() {
        adapter = NewsListAdapter(ArrayList(), this)
    }

    override fun fetchRecyclerView(): RecyclerView {
        return recyclerView
    }

    private inner class CacheObserver: Observer<List<NewsViewModel>>{
        override fun onComplete() {
            Log.i(TAG, "ON_COMPLETE_CACHE")
        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: List<NewsViewModel>) {
            when (CommonUtils.getCacheStatus(CommonUtils.isConnected(context), t)) {
                SHOULD_USE_CACHE -> {
                    if(t.isNotEmpty()){
                        adapter?.refactorItems(t)
                        switchUI(true, false, false)
                    }else
                        switchUI(false, false, true)
                }
                SHOULD_NOT_USE_CACHE -> {
                    fromBG = true
                    stringObjectHashMap[NEWS_TYPE_PARAM] = "$currentDefaultTopic.json"
                    stringObjectHashMap[API_KEY_PARAM] = API_KEY
                    presenter.loadData(stringObjectHashMap)
                }
                NO_CACHE_AND_INTERNET -> switchUI(false, false, true)
                else -> switchUI(false, false, true)
            }
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, e.message)
        }

    }


    private val stringObjectHashMap = HashMap<String, Any>()
    private var currentDefaultTopic:String = ""
    private var forceLoad:Boolean?=null

    @BindView(R.id.rv_list)
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var cacheManager: CacheManager
}
