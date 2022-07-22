package com.bdappmaniac.bdapp.employee.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentGlideLoanSubmitBinding;
import com.bdappmaniac.bdapp.fragment.BaseFragment;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Timer;
import java.util.TimerTask;

public class GlideLoanSubmitFragment extends BaseFragment {
    FragmentGlideLoanSubmitBinding binding;
    private Object GifDrawable;
    private Object GlideLoanSubmitFragment;
    CountDownTimer countDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_glide_loan_submit, container, false);
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);

//        Glide.with(this).load(R.raw.tick).into(binding.image);

        Glide.with(this).asGif().load(R.raw.swoosh).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(1);
                resource.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        //do whatever after specified number of loops complete
                        Navigation.findNavController(getView()).navigate(R.id.empLoanfragment);
                    }
                });
                return false;
            }
        }).into(binding.image);
        hPointCounter();

//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Navigation.findNavController(getView()).navigate(R.id.empLoanfragment);
//            }
//        } , 4000 );


        return binding.getRoot();

    }
    private void hPointCounter() {
        countDownTimer = new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
               binding.appliedTv.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}