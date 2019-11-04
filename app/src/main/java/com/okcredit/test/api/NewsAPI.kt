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

package com.okcredit.test.api


import com.okcredit.test.mvp.model.ResponseModel
import com.okcredit.test.utils.Constants
import com.okcredit.test.utils.Constants.API_KEY_PARAM
import com.okcredit.test.utils.Constants.NEWS_TYPE_PARAM

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsAPI {

    //News API
    @GET("{type}")
    fun loadNews(@Path(NEWS_TYPE_PARAM) type: String, @Query(API_KEY_PARAM) apiKey: String): Observable<Response<ResponseModel>>
}
