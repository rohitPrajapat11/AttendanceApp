package com.bdappmaniac.bdapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StringHelper;
import com.google.android.material.snackbar.Snackbar;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BaseFragment extends Fragment {
    public Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void openLinkWithColor(String str, TextView text, String color) {
        Link link = new Link(str)
                .setTextColor(Color.parseColor(color))
                .setHighlightAlpha(.4f);
        LinkBuilder.on(text)
                .addLink(link)
                .build();
    }

    public String getToken() {
        SharedPref.init(mContext);
        String getToken = "Bearer " + SharedPref.getUserDetails().getAccessToken();
        return getToken;
    }

    public RequestBody toRequestBody(String val) {
        RequestBody requestBody = null;
        if (getActivity() != null) {
            requestBody = toRequestBodyPart(val);
        }
        return requestBody;
    }

    public void takeMeHome() {
        Navigation.findNavController(getView()).navigate(R.id.homeFragment);
    }

    public RequestBody toRequestBodyPart(String value) {
        return !StringHelper.isEmpty(value) ? RequestBody.create(MediaType.parse("text/plain"), value) : null;
    }
}
