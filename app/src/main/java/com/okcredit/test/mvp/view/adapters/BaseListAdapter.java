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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.okcredit.test.mvp.view.viewholders.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseListAdapter<H> extends RecyclerView.Adapter<BaseViewHolder<H>> {

    BaseListAdapter(List<H> list){
        this.list = list;
    }

    @NonNull
    @Override
    public BaseViewHolder<H> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(i), viewGroup,false);
            return callViewHolder(view, i);
        }catch (RuntimeException e){
            e.printStackTrace();
            Log.e("ERROR: ","NO VIEW TYPE FOUND");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<H> hBaseViewHolder, int i) {
        onBind(hBaseViewHolder, i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<H> list){
        if(this.list!=null && this.list.size()==0){
            this.list = list;
            notifyItemRangeInserted(0, list.size());
        }else {
            int startPos = this.list.size();
            this.list.addAll(startPos,list);
            notifyItemRangeInserted(startPos, list.size());
        }
    }

    public void refactorItems(List<H> list){
        if(this.list!=null){
            this.list.clear();
            this.list = list;
            notifyDataSetChanged();
        }
    }

    protected abstract int getLayoutId(int viewType) throws RuntimeException;

    protected abstract BaseViewHolder<H> callViewHolder(View view, int viewType);

    protected abstract void onBind(BaseViewHolder<H> viewHolder, int position);

    protected Map<Integer, Integer> viewMap = new HashMap<>();
    protected List<H> list;
}
