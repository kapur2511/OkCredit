/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.view.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.okcredit.test.mvp.view.fragments.NewsDetailFragment
import com.okcredit.test.utils.Constants.NEWS_ABSTRACT_PARAM
import com.okcredit.test.utils.Constants.NEWS_ARTICLE_LINK_PARAM
import com.okcredit.test.utils.Constants.NEWS_AUTHOR_PARAM
import com.okcredit.test.utils.Constants.NEWS_COVER_URL_PARAM
import com.okcredit.test.utils.Constants.NEWS_PUBLISH_PARAM
import com.okcredit.test.utils.Constants.NEWS_TITLE_PARAM

class NewsDetailActivity: BaseActivity() {

    override fun viewFragment(): Fragment {
        val bundle = Bundle()
        bundle.putString(NEWS_TITLE_PARAM, intent.getStringExtra(NEWS_TITLE_PARAM))
        bundle.putString(NEWS_AUTHOR_PARAM, intent.getStringExtra(NEWS_AUTHOR_PARAM))
        bundle.putString(NEWS_PUBLISH_PARAM, intent.getStringExtra(NEWS_PUBLISH_PARAM))
        bundle.putString(NEWS_ABSTRACT_PARAM, intent.getStringExtra(NEWS_ABSTRACT_PARAM))
        bundle.putString(NEWS_ARTICLE_LINK_PARAM, intent.getStringExtra(NEWS_ARTICLE_LINK_PARAM))
        bundle.putString(NEWS_COVER_URL_PARAM, intent.getStringExtra(NEWS_COVER_URL_PARAM))
        return buildFragment(bundle, NewsDetailFragment::class.java)
    }
}