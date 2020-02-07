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

package com.okcredit.test.mvp.view.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.okcredit.test.mvp.view.fragments.NewsListFragment


class NewsListActivity : BaseActivity() {

    override fun viewFragment(): Fragment? {
        val bundle = Bundle()
        return buildFragment(bundle, NewsListFragment::class.java)
    }
}
