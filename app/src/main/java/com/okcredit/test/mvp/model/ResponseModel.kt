/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.model

data class ResponseModel(val copyright:String,
                         val last_updated:String,
                         val section:String,
                         val results:List<NewsModel>,
                         val num_results:String,
                         val status: String)