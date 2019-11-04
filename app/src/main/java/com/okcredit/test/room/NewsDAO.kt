/*
 * Copyright (C) 03-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.okcredit.test.mvp.view.viewmodel.NewsViewModel

@Dao
interface NewsDAO {

    @Query("SELECT * FROM news_table WHERE news_type = :newsType")
    fun getNewsViewModels(newsType:String): List<NewsViewModel>

    @Query("DELETE FROM news_table WHERE news_type = :newsType")
    fun deleteNewsType(newsType: String)

    @Insert
    fun saveNewsViewModels(list: List<NewsViewModel>)
}