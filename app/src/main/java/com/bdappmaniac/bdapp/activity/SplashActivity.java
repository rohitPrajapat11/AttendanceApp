package com.bdappmaniac.bdapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.ActivitySplashBinding;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    Animation top_animation, bottom_animation, fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        StatusBarUtils.statusBarColor(this, R.color.transparent);
        setTheme(R.style.Theme_BdApp_Launcher);
        SharedPref.init(this);

   top_animation  = AnimationUtils.loadAnimation(this,R.anim.fade_in);

        top_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.getStarted.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.getStarted.startAnimation(top_animation);



                new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPref.getUserDetails() == null) {
                    Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                    startActivity(intent);
                    finish();
                } else if (SharedPref.getUserDetails().getType().equals("employee")) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (SharedPref.getUserDetails().getType().equals("admin")) {
                    Intent intent = new Intent(SplashActivity.this, AdminActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2500);
    }
}