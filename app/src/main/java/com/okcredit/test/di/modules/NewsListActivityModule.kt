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

import com.okcredit.test.mvp.view.fragments.NewsListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsListActivityModule {

    @ContributesAndroidInjector
    internal abstract fun newsListFragment(): NewsListFragment
}
