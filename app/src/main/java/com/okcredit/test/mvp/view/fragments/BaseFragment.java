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

package com.okcredit.test.mvp.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.okcredit.test.R;
import com.okcredit.test.mvp.presenter.BasePresenter;
import com.okcredit.test.mvp.view.renderer.BaseRenderer;
import com.okcredit.test.mvp.view.viewmodel.ListItem;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<P extends BasePresenter, V extends BaseRenderer> extends Fragment {

    public BaseFragment(){}

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        Log.i("LifeCycle","onAttach "+this.getClass().getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LifeCycle","onCreate "+this.getClass().getSimpleName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("LifeCycle","onCreateView "+this.getClass().getSimpleName()+" layoutid: "+layoutId);
        View fragmentView = inflater.inflate(layoutId, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("LifeCycle","onStart "+this.getClass().getSimpleName());
        setupPresenter();
        switchUI(false, true, false);
        loadData(presenter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        fromBG = false;
        super.onDestroy();
    }

    protected void reloadData(){
        switchUI(false, true, false);
        loadData(presenter);
    }

    private void setupPresenter(){
        if(presenter!=null){
            presenter.init(this);
        }
    }

    protected void switchUI(boolean contentUI, boolean loadingUI, boolean errorUI){
        if(loadingUI)
            startLoadingAnimation();
        else
            stopLoadingAnimation();
        constraintLayoutLoading.setVisibility(loadingUI ? View.VISIBLE : View.GONE);
        constraintLayoutContent.setVisibility(contentUI ? View.VISIBLE : View.GONE);
        constraintLayoutError.setVisibility(errorUI ? View.VISIBLE : View.GONE);
    }

    protected void startLoadingAnimation(){
        imgLoading.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_animation));
    }

    protected void stopLoadingAnimation(){
        imgLoading.clearAnimation();
    }

    @OnClick(R.id.btn_error)
    void onRetry(View view){
        forceLoad = true;
        switchUI(false, true, false);
        loadData(presenter);
    }

    protected abstract void loadData(P presenter);

    protected final String TAG = this.getClass().getSimpleName();


    @Inject
    SharedPreferences sharedPreferences;

    @BindView(R.id.cl_main_content)
    ConstraintLayout constraintLayoutContent;

    @BindView(R.id.cl_loading)
    ConstraintLayout constraintLayoutLoading;

    @BindView(R.id.cl_error)
    ConstraintLayout constraintLayoutError;

    @BindView(R.id.img_loading)
    ImageView imgLoading;

    @Inject
    P presenter;

    private Unbinder unbinder;
    protected boolean fromBG;
    protected int layoutId;
    protected boolean forceLoad;

}
