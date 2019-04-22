package com.android.onesnackbarlib;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class AnimationUtil {
    static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearInterpolator();

    public static final int ANIMATION_PULL = 0X003;
    public static final int ANIMATION_HIDE = 0X004;
    public final static int OTHER_HEIGHT = 40;
    public static AnimatorSet getShowAnimation(View mView, int animationType, int height){
        switch (animationType){
            case ANIMATION_PULL:
                AnimatorSet pullSet = new AnimatorSet();
                pullSet.playTogether(
                        ObjectAnimator.ofFloat(mView, "translationY",
                                -height-OTHER_HEIGHT, 0,-OTHER_HEIGHT),
                        ObjectAnimator.ofFloat(mView, "alpha",
                                0.9f,1.0f)
                );
                pullSet.setDuration(800);
                return pullSet;

            case ANIMATION_HIDE:
                AnimatorSet hideSet = new AnimatorSet();
                hideSet.playTogether(
                        ObjectAnimator.ofFloat(mView, "translationY",
                                -OTHER_HEIGHT, -height)
                );
                hideSet.setDuration(300);
                return hideSet;

            default:
                return new AnimatorSet();
        }
    }
}