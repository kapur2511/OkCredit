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

package com.okcredit.test.utils

object Constants {

    val API_KEY = "xI4AA4gcMj9JyFlyQn2dSAj689PGjKjA"
    var BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
    val PREF_CURRENT_TIME = "pref.current.data"
    val PREF_CACHE_DATA = "pref.cache.data"
    val PREF_CURRENT_PAGE = "pref.current.page"
    val PREF_NEXT_PAGE_TOKEN = "pref.next.page.token"
    val PREF_TOTAL_PAGES = "pref.total.pages"
    val IMAGE_URL = "image.url"
    val NEWS_URL = "news.url"
    val RESPONSE_PER_PAGE = 20

    const val SHOULD_USE_CACHE = 100
    const val SHOULD_NOT_USE_CACHE = 101
    const val NO_CACHE_AND_INTERNET = 102
    val ERROR = 103

    val NEWS_TITLE_PARAM = "news.title.param"
    val NEWS_AUTHOR_PARAM = "news.author.param"
    val NEWS_PUBLISH_PARAM = "news.publish.param"
    val NEWS_ABSTRACT_PARAM = "news.abstract.param"
    val NEWS_ARTICLE_LINK_PARAM = "news.article.param"
    val NEWS_COVER_URL_PARAM = "news.cover.url.param"

    val NEWS_DEFAULT_TOPIC = "news.default.topic"

    //Path params
    const val NEWS_TYPE_PARAM = "type"
    //Query params
    const val API_KEY_PARAM = "api-key"
}
