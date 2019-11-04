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

package com.okcredit.test.mvp.view.activities;

import android.os.Bundle;
import android.widget.FrameLayout;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.okcredit.test.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        unbinder = ButterKnife.bind(this);
        Fragment fragment = viewFragment();
        if(fragment!=null)
            startFragment(fragment);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        getLayoutInflater().inflate(layoutResID, frameLayout);
    }

    protected Fragment buildFragment(Bundle args, Class targetClass) {
        Fragment frag =
                Fragment.instantiate(this, targetClass.getName());
        if (args != null) {
            frag.setArguments(new Bundle(args));
        }
        return frag;
    }

    private void startFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, fragment)
                .commit();
    }

    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract Fragment viewFragment();

    private Unbinder unbinder;
    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @BindView(R.id.frame_container)
    FrameLayout frameLayout;
}
