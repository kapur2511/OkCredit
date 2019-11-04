/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.okcredit.test.mvp.view.viewmodel.ListItem;

import java.util.List;

import static com.okcredit.test.utils.Constants.NO_CACHE_AND_INTERNET;
import static com.okcredit.test.utils.Constants.SHOULD_NOT_USE_CACHE;
import static com.okcredit.test.utils.Constants.SHOULD_USE_CACHE;


public class CommonUtils {

    public static boolean isConnected(Context context){
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    public static int getCacheStatus(boolean isConnected, List<ListItem> listItems){
        if(isConnected && (listItems==null || listItems.size() == 0))
            return SHOULD_NOT_USE_CACHE;
        else if(listItems!=null && listItems.size()>0)
            return SHOULD_USE_CACHE;
        else
            return NO_CACHE_AND_INTERNET;
    }

    private static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

}
