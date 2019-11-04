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

package com.okcredit.test.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.okcredit.test.room.AppDatabase;
import com.okcredit.test.room.CacheManager;
import com.okcredit.test.room.DBManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences("OkCredit",Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public static DBManager provideDBManager(Context context){
        return new DBManager(context);
    }

    @Provides
    @Singleton
    public static CacheManager provideCacheManager(DBManager dbManager){
        return new CacheManager(dbManager);
    }

}
