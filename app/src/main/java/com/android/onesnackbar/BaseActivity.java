package com.android.onesnackbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.android.onesnackbarlib.OneSnackBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public abstract class BaseActivity extends AppCompatActivity {
    protected View mContentView = null;
    protected FrameLayout mContentLayout = null;
    OneSnackBar snackBar = null;
    private boolean isActivityFront = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            mContentLayout = new FrameLayout(this);
            mContentView = LayoutInflater.from(this).inflate(getLayoutId(), null);
            mContentLayout.addView(mContentView);
            setContentView(mContentLayout);
        }
        initSnackBar();
        initData();
    }

    private void initSnackBar() {
        snackBar = OneSnackBar.make(getWindow().getDecorView(), "", OneSnackBar.LENGTH_SHORT, OneSnackBar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.setBackgroundColor(Color.parseColor("ffc617"));
//        snackBar.addIcon(R.mipmap.item_toast_icon);
    }

    public abstract int getLayoutId();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void actionActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getApplicationContext(), cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    public void actionActivity(Class<?> cls, Bundle bundle, int code) {
        Intent intent = new Intent(getApplicationContext(), cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, code);
    }

    @Subscribe
    public void showToast(final ShowToastEvent showToastEvent){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isActivityFront || Utils.isActivityForeground(BaseActivity.this)) {
                    snackBar.setText(showToastEvent.getMessage());
                    snackBar.show();
                }
            }
        },50);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.isActivityFront = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.isActivityFront = false;
        if(snackBar.isShownOrQueued()){
            Utils.dismissSnackBar(snackBar);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
