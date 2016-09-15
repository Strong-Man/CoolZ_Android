package com.cool.zpw10018.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zpw on 2016/9/15.
 */

public class WelcomeActivity extends Activity {

    @BindView(R.id.welcome_backdrop_iv)
    ImageView mBackdropIv;
    @BindView(R.id.welcome_logo_iv)
    ImageView mLogoIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initAnimation();
    }

    private void initAnimation() {
        startBackdropAnimation();
        //后续加点特效
    }

    private void startBackdropAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_top_in);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mBackdropIv.startAnimation(animation);
    }

    private void toMainActivity() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        overridePendingTransition(0, android.R.anim.fade_out);
                        finish();
                    }
                });
    }

}
