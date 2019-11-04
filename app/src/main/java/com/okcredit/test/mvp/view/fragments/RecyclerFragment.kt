/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okcredit.test.mvp.presenter.BasePresenter
import com.okcredit.test.mvp.view.adapters.BaseListAdapter
import com.okcredit.test.mvp.view.renderer.BaseRenderer

abstract class RecyclerFragment< P : BasePresenter<*>, V : BaseRenderer, A : BaseListAdapter<*>>: BaseFragment<P, V>() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setupRecyclerViewAdapter()
        setupRecyclerView(fetchRecyclerView())
        return view
    }

    protected fun setupRecyclerView(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    protected abstract fun setupRecyclerViewAdapter()

    protected abstract fun fetchRecyclerView(): RecyclerView

    protected var adapter: A?=null
}