/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import com.okcredit.test.mvp.view.viewmodel.ListItem

import com.okcredit.test.utils.Constants.NO_CACHE_AND_INTERNET
import com.okcredit.test.utils.Constants.SHOULD_NOT_USE_CACHE
import com.okcredit.test.utils.Constants.SHOULD_USE_CACHE


object CommonUtils {

    fun isConnected(context: Context?): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected
    }

    fun getCacheStatus(isConnected: Boolean, listItems: List<ListItem>?): Int {
        return if (isConnected && (listItems == null || listItems.isEmpty()))
            SHOULD_NOT_USE_CACHE
        else if (listItems != null && listItems.isNotEmpty())
            SHOULD_USE_CACHE
        else
            NO_CACHE_AND_INTERNET
    }

    private fun getNetworkInfo(context: Context?): NetworkInfo? {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo
    }

}
