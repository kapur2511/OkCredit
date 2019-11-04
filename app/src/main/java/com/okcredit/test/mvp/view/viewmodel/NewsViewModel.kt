/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.view.viewmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsViewModel(var title:String = "",
                         var author:String = "",
                         @ColumnInfo(name="thumbnail_url") var thumbnailUrl: String = "",
                         @ColumnInfo(name="cover_image_url") var coverImageUrl: String = "",
                         @ColumnInfo(name="abstract") var abstract: String = "",
                         @ColumnInfo(name="publish_date") var publishDate: String = "",
                         @ColumnInfo(name="article_link") var articleLink: String = "",
                         @ColumnInfo(name="news_type") var newsType: String = ""): ListItem{
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}