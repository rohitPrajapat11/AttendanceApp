package com.bdappmaniac.bdapp.application;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.StringUtils;

import java.util.HashMap;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class BdApp extends MultiDexApplication {
    private static final String TAG = "KwikkApplication";
    private static BdApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

    }

    public static BdApp getInstance() {
        return instance;
    }
}
