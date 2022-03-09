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
        StatusBarUtils.statusBarColor(this, R.color._FF99D131);
        setTheme(R.style.Theme_BdApp_Launcher);
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade);
        binding.logo.setAnimation(bottom_animation);
        binding.appName.setAnimation(top_animation);
        SharedPref.init(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.description.setVisibility(View.VISIBLE);
            }
        }, 1800);
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