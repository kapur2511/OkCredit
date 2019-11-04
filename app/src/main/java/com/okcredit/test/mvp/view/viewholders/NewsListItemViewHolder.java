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

package com.okcredit.test.mvp.view.viewholders;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.okcredit.test.R;
import com.okcredit.test.mvp.view.viewmodel.ListItem;
import com.okcredit.test.mvp.view.viewmodel.NewsViewModel;
import com.okcredit.test.utils.ClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class NewsListItemViewHolder<H extends ListItem> extends BaseViewHolder<ListItem> implements View.OnClickListener{

    public NewsListItemViewHolder(@NonNull View itemView, ClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(ListItem data) {
        this.listItem = data;
        NewsViewModel newsViewModel = (NewsViewModel) data;
        if(!newsViewModel.getThumbnailUrl().isEmpty())
            Picasso.get().load(newsViewModel.getThumbnailUrl()).fit().into(imageViewThumbnail);
        textViewTitle.setText(newsViewModel.getTitle());
        textViewAuthor.setText(newsViewModel.getAuthor());
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(listItem);
    }

    private ClickListener clickListener;
    private ListItem listItem;

    @BindView(R.id.img_news)
    ImageView imageViewThumbnail;

    @BindView(R.id.txt_title)
    TextView textViewTitle;

    @BindView(R.id.txt_author)
    TextView textViewAuthor;

}
