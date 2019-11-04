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

package com.okcredit.test.mvp.presenter

import android.view.View
import com.okcredit.test.mvp.view.renderer.BaseRenderer

abstract class BasePresenter<V> {

    var view: V?=null

    protected val TAG = this.javaClass.simpleName

    fun init(view: V) {
        this.view = view
    }

    abstract fun loadData(stringObjectHashMap: Map<String, Any>)
}
