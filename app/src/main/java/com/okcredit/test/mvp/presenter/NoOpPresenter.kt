/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.presenter

import javax.inject.Inject

class NoOpPresenter @Inject constructor(): BasePresenter<Any>() {
    override fun loadData(stringObjectHashMap: Map<String, Any>) {
        //Not performing any operation
    }
}