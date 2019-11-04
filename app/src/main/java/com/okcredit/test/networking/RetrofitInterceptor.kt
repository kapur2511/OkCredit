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

package com.okcredit.test.networking

import android.util.Log

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

class RetrofitInterceptor : Interceptor {

    private val TAG = this.javaClass.simpleName

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, chain.request().url().toString())
        return chain.proceed(chain.request())
    }
}
