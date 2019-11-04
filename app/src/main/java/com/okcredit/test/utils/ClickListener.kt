/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.utils


import com.okcredit.test.mvp.view.viewmodel.ListItem

interface ClickListener {

    fun onClick(listItem: ListItem)
}
