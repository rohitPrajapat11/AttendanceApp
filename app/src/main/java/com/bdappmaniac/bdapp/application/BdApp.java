package com.bdappmaniac.bdapp.application;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.BaseActivity;
import com.bdappmaniac.bdapp.helper.ConnectivityReceiver;

import java.util.Objects;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class BdApp extends MultiDexApplication {
    private static final String TAG = "BD App";
    private static BdApp instance;
    private ConnectivityReceiver receiver;

    public BdApp() {
        super();
    }

    public static BdApp getInstance() {
        if (instance == null) {
            instance = new BdApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerConnectivityReceiver();
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

    }

    private ConnectivityReceiver getReceiverObserver() {
        if (receiver == null)
            receiver = new ConnectivityReceiver();
        return receiver;
    }

    public boolean isInternetConnected(Context context) {
        if (new CheckInternetConnection(context).isConnected())
            return true;
        else {
            ((BaseActivity) context).noInternetDialog();
        }
        return false;
    }

    public boolean isInternet(Context context) {
        if (new CheckInternetConnection(context).isConnected())
            return true;
        return false;
    }

    private void registerConnectivityReceiver() {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(getReceiverObserver(), filter);
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }
}
