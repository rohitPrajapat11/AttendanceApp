package com.bdappmaniac.bdapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.employee.fragment.LogInFragment;

import java.util.List;

public class AuthActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
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