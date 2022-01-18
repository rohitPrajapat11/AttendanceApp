package com.bdappmaniac.bdapp.Utils;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class StatusBarUtils {
    public static void statusBarColor(Activity context,int color){
        context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        context.getWindow().setStatusBarColor(ContextCompat.getColor(context, color));//
        context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }
}
