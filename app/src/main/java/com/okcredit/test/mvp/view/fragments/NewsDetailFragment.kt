/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.view.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.okcredit.test.R
import com.okcredit.test.mvp.presenter.NoOpPresenter
import com.okcredit.test.mvp.view.renderer.NewsDetailRenderer
import com.okcredit.test.utils.Constants.NEWS_ABSTRACT_PARAM
import com.okcredit.test.utils.Constants.NEWS_ARTICLE_LINK_PARAM
import com.okcredit.test.utils.Constants.NEWS_AUTHOR_PARAM
import com.okcredit.test.utils.Constants.NEWS_COVER_URL_PARAM
import com.okcredit.test.utils.Constants.NEWS_PUBLISH_PARAM
import com.okcredit.test.utils.Constants.NEWS_TITLE_PARAM
import com.squareup.picasso.Picasso

class NewsDetailFragment: BaseFragment<NoOpPresenter, NewsDetailRenderer>(), NewsDetailRenderer {

    init {
        layoutId = R.layout.fragment_news_detail
    }

    override fun renderNewsDetail(args: Bundle) {
        switchUI(true, false, false)
        txtTitle.text       = args.getString(NEWS_TITLE_PARAM)
        txtAuthor.text      = args.getString(NEWS_AUTHOR_PARAM)
        txtPublish.text     = args.getString(NEWS_PUBLISH_PARAM)
        txtAbstract.text    = args.getString(NEWS_ABSTRACT_PARAM)
        txtArticleLink.text = args.getString(NEWS_ARTICLE_LINK_PARAM)
        txtArticleLink.movementMethod = LinkMovementMethod.getInstance()
        if (args.containsKey(NEWS_COVER_URL_PARAM) && args.getString(NEWS_COVER_URL_PARAM).isNotEmpty())
            Picasso.get().load(args.getString(NEWS_COVER_URL_PARAM)).fit().into(imgCover)
    }

    override fun renderError() {
        switchUI(false, false, true)
    }


    override fun loadData(presenter: NoOpPresenter?) {
        if (args != null && !args!!.isEmpty)
            renderNewsDetail(args!!)
        else
            renderError()
    }

    override fun setArguments(args: Bundle?) {
        Log.d(TAG,"LifeCycle: SetArgs "+this.javaClass.simpleName)
        this.args = args
    }

    private var args:Bundle?=null

    @BindView(R.id.txt_news_title)
    lateinit var txtTitle: TextView

    @BindView(R.id.txt_news_author)
    lateinit var txtAuthor: TextView

    @BindView(R.id.txt_publish)
    lateinit var txtPublish: TextView

    @BindView(R.id.txt_abstract)
    lateinit var txtAbstract: TextView

    @BindView(R.id.txt_article_link)
    lateinit var txtArticleLink: TextView

    @BindView(R.id.img_cover)
    lateinit var imgCover: ImageView
}