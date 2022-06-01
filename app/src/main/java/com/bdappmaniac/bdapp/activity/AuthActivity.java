package com.bdappmaniac.bdapp.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.application.BdApp;
import com.bdappmaniac.bdapp.fragment.LogInFragment;
import com.bdappmaniac.bdapp.helper.AppLoader;
import com.bdappmaniac.bdapp.helper.ConnectivityReceiver;
import com.bdappmaniac.bdapp.interfaces.OnChangeConnectivityListener;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

import java.util.List;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        StatusBarUtils.statusBarColor(this, R.color.white);
        ConnectivityReceiver.setConnectivityListener(new OnChangeConnectivityListener() {
            @Override
            public void onChanged(boolean status) {
                if (!BdApp.getInstance().isInternet(AuthActivity.this)) {
                    noInternetDialog();
                } else {
                    if (noInternetdialog != null) {
                        noInternetdialog.dismiss();
                        AppLoader.hideLoaderDialog();
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.auth_nav);
        if (navHostFragment != null) {
            List<Fragment> fragmentList = navHostFragment.getChildFragmentManager().getFragments();
            boolean isLoginFragment = false;
            for (Fragment fragment1 : fragmentList) {
                if (fragment1 instanceof LogInFragment) {
                    isLoginFragment = true;
                    break;
                }
            }
            if (isLoginFragment) {
                finish();
            }
        }
    }

}