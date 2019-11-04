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

package com.okcredit.test.di.component;

import android.content.Context;

import com.okcredit.test.OkCreditApplication;
import com.okcredit.test.di.modules.ActivityBuilderModule;
import com.okcredit.test.di.modules.ApplicationModule;
import com.okcredit.test.di.modules.NetworkModule;

import java.io.File;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        NetworkModule.class,
        ActivityBuilderModule.class,
        })
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder cache(File cache);
        @BindsInstance
        Builder application(Context context);
        ApplicationComponent build();
    }

    void inject(OkCreditApplication app);
}
