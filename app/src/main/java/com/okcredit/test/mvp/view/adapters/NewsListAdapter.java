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

package com.okcredit.test.mvp.view.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.okcredit.test.R;
import com.okcredit.test.mvp.view.viewholders.BaseViewHolder;
import com.okcredit.test.mvp.view.viewholders.NewsListItemViewHolder;
import com.okcredit.test.mvp.view.viewmodel.ListItem;
import com.okcredit.test.utils.ClickListener;

import java.util.List;

public class NewsListAdapter<H extends ListItem> extends BaseListAdapter<H>{

    public NewsListAdapter(List<H> list, ClickListener clickListener) {
        super(list);
        this.clickListener = clickListener;
        viewMap.put(VIEW_TYPE_NEWS,  R.layout.view_news_item);
    }

    @Override
    protected BaseViewHolder<H> callViewHolder(View view, int viewType) {
        return new NewsListItemViewHolder(view, clickListener);
    }

    @Override
    protected void onBind(BaseViewHolder<H> viewHolder, int position) {
        viewHolder.setData(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NEWS;
    }

    @Override
    protected int getLayoutId(int viewType) {
        if(viewMap.containsKey(viewType))
            return viewMap.get(viewType);
        else
            throw new RuntimeException("No view type found!!");
    }

    private ClickListener clickListener;
    private final int VIEW_TYPE_NEWS  = 1;
}
