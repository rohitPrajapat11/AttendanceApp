package com.bdappmaniac.bdapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
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
        StatusBarUtils.transparentStatusAndNavigation(this);
        setTheme(R.style.Theme_BdApp_Launcher);
        SharedPref.init(this);
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade);
        binding.logo.setAnimation(top_animation);
        binding.getStarted.setAnimation(bottom_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPref.getUserDetails() == null) {
                    callAuth();
                } else if (SharedPref.getUserDetails().getType().equals("employee")) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (SharedPref.getUserDetails().getType().equals("admin")) {
                    Intent intent = new Intent(SplashActivity.this, TestActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2500);
    }

    public void callAuth() {
        binding.getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}