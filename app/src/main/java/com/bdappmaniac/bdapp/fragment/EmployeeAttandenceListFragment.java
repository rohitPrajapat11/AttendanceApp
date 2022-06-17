package com.bdappmaniac.bdapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.databinding.FragmentEmployeeAttandenceListBinding;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;

public class EmployeeAttandenceListFragment extends BaseFragment {
    FragmentEmployeeAttandenceListBinding binding;
    String url = ("https://docs.google.com/spreadsheets/d/1ic1AoaqBcuocW_ZL2kL52IAB8RWTs_hH8coIQYqZMFM/edit#gid=970146190");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_attandence_list, container, false);
        binding.backBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigateUp();
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();
                customIntent.setToolbarColor(ContextCompat.getColor(mContext, R.color.prime));
                openCustomTab(getActivity(), customIntent.build(), Uri.parse(url));
            }
        });
        return binding.getRoot();
    }

    public static void openCustomTab(Activity activity, CustomTabsIntent customTabsIntent, Uri uri) {
        String packageName = "com.android.chrome";
        if (packageName != null) {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(activity, uri);
        } else {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }


    public void GetUrlFromIntent(View view) {
//        String url = ("https://docs.google.com/spreadsheets/d/1ic1AoaqBcuocW_ZL2kL52IAB8RWTs_hH8coIQYqZMFM/edit#gid=970146190");
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url));
//        context.startActivity(intent);

//        String data = ("https://docs.google.com/spreadsheets/d/1ic1AoaqBcuocW_ZL2kL52IAB8RWTs_hH8coIQYqZMFM/edit#gid=970146190");
//        Intent defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
//        defaultBrowser.setData(Uri.parse(data));
//        context.startActivity(defaultBrowser);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.white);
    }
}