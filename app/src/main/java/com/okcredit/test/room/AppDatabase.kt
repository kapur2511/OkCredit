/*
 * Copyright (C) 03-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.okcredit.test.mvp.view.viewmodel.NewsViewModel

@Database(entities = [NewsViewModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDAO(): NewsDAO
}