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


import com.okcredit.test.mvp.view.activities.NewsDetailActivity
import com.okcredit.test.mvp.view.activities.NewsListActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [NewsListActivityModule::class])
    internal abstract fun bindNewsListActivity(): NewsListActivity

    @ContributesAndroidInjector(modules = [NewsDetailActivityModule::class])
    internal abstract fun bindNewsDetailActivity(): NewsDetailActivity
}
