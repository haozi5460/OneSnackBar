package com.android.onesnackbar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.android.onesnackbarlib.OneSnackBar;

import org.greenrobot.eventbus.EventBus;

/**
 * For the reason of permission of Toast,we change to use SnackBar instead of Toast
 */
public class SnackBarUtil {
    private static Context context;
    private static SnackBarUtil mToastUtils = null;
    private String messageText = null;
    private static Context lastmContext = null;
    private static String lastMessage = null;
    private long lastShowTime = 0;
    private static final long MAX_CONTROL_INTERVAL = 500;

    public synchronized static SnackBarUtil create(Context context) {
        SnackBarUtil.context = context;
        if (mToastUtils == null) {
            mToastUtils = new SnackBarUtil();
        }
        return mToastUtils;
    }

    public SnackBarUtil setMessage(int resId) {
        return setMessage(context.getString(resId));
    }

    public SnackBarUtil setMessage(final String message) {
        try {
            if (!Utils.isNullOrEmpty(message)) {
                messageText = message;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void show() {
        try {
            if(canShowToast()) {
                lastmContext = context;
                lastMessage = messageText;
                lastShowTime = System.currentTimeMillis();
                EventBus.getDefault().post(new ShowToastEvent(messageText));
            }
        } catch (Exception e) {
            Log.e("TAG",e.getMessage());
        }
    }

    public void show(View view){
        OneSnackBar snackBar = OneSnackBar.make(view, messageText, OneSnackBar.LENGTH_SHORT, OneSnackBar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.setBackgroundColor(Color.parseColor("ffc617"));
//        snackBar.addIcon(R.mipmap.item_toast_icon);
        snackBar.show();
    }

    private boolean canShowToast() {
        if(lastmContext == null || Utils.isNullOrEmpty(lastMessage)){
            return true;
        } else if(lastmContext == context && lastMessage.equalsIgnoreCase(messageText)
                && (System.currentTimeMillis() - lastShowTime) < MAX_CONTROL_INTERVAL){
            return false;
        } else if(lastmContext != context || (System.currentTimeMillis() - lastShowTime) >= MAX_CONTROL_INTERVAL
            || !lastMessage.equalsIgnoreCase(messageText)){
            lastMessage = null;
            lastShowTime = 0;
            return true;
        }
        return false;
    }
}

