/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test;

import android.app.Activity;
import android.app.Application;

import com.amitshekhar.DebugDB;
import com.okcredit.test.di.component.ApplicationComponent;
import com.okcredit.test.di.component.DaggerApplicationComponent;
import com.okcredit.test.room.CacheManager;

import java.io.File;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class OkCreditApplication extends Application implements HasActivityInjector {


    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .cache(cacheFile).application(this)
                .build();
        applicationComponent.inject(this);
        cacheManager.clearAllCache();
        DebugDB.getAddressLog();
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Inject
    CacheManager cacheManager;

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
}
