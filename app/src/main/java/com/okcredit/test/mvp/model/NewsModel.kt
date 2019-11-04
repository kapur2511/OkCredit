/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.model

data class NewsModel(val per_facet:List<String>,
                     val subsection:String,
                     val item_type:String,
                     val org_facet:List<String>,
                     val section:String,
                     val abstract:String,
                     val title:String,
                     val des_facet:List<String>,
                     val url:String,
                     val short_url:String,
                     val material_type_facet:String,
                     val multimedia:List<MultimediaModel>,
                     val geo_facet:List<String>,
                     val updated_date:String,
                     val created_date:String,
                     val byline:String,
                     val published_date:String,
                     val kicker:String)