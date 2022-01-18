package com.bdappmaniac.bdapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;


public class BaseFragment extends Fragment {
    Context mContext;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }
    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
    public void openLinkWithColor(String str, TextView text , String color) {
        Link link = new Link(str)
                .setTextColor(Color.parseColor(color))
                .setHighlightAlpha(.4f);
        LinkBuilder.on(text)
                .addLink(link)
                .build();
    }



}
