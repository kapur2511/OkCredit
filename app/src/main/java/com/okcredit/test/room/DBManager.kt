/*
 * Copyright (C) 03-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.room

import android.content.Context
import androidx.room.Room
import javax.inject.Inject


class DBManager @Inject constructor(val context: Context){

    private var INSTANCE: AppDatabase? = null

    fun getAppDatabase(): AppDatabase? {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "news_database")
                    .build()
        }
        return INSTANCE
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}