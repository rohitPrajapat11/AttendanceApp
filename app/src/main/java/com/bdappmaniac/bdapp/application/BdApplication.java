package com.bdappmaniac.bdapp.application;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.multidex.MultiDexApplication;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.utils.PreferenceManger;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.StringUtils;

import java.util.HashMap;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class BdApplication extends MultiDexApplication {

    private static final String TAG = "BdApplication";
    public static BdApplication instance = new BdApplication();
    private static PreferenceManger preferenceManger;
    public static BdApplication getInstance() {
        return instance;
    }
    public static PreferenceManger getPreferenceManger() {
        if (preferenceManger == null && getInstance() != null) {
            preferenceManger = new PreferenceManger(getInstance().getSharedPreferences(PreferenceManger.PREF_KEY, Context.MODE_PRIVATE));
        }
        return preferenceManger;
    }

    public static HashMap<String, Object> getDefaultHeaders() {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (preferenceManger != null && !TextUtils.isEmpty(preferenceManger.getAuthToken())) {
            hashMap.put("Authorization", StringHelper.capitalize(preferenceManger.getAuthToken()));
        }
        hashMap.put("Accept", "application/json");
        return hashMap;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        ViewPump.init(ViewPump.builder()
//                .addInterceptor(new CalligraphyInterceptor(
//                        new CalligraphyConfig.Builder()
//                                .setFontAttrId(R.attr.fontPath)
//                                .build()))
//                .build());

    }

}
