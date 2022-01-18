package com.bdappmaniac.bdapp.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.Toast;

import com.bdappmaniac.bdapp.R;

import java.util.StringTokenizer;

public class TextToBitmap {
    public static Bitmap textToBitmap(String fullName, Context context , float textSize,int textColor) {
        String first = "", last = "";
        StringTokenizer tokens = new StringTokenizer(fullName, " ");
        first = tokens.nextToken();
        if (tokens.countTokens() != 0) {
            last = tokens.nextToken();
        }
        String fistLetter = String.valueOf(first.charAt(0));
        String secondLetter="";
        if(!last.equals(""))
        {
            secondLetter = String.valueOf(last.charAt(0));
        }
        String getName = fistLetter+secondLetter;
        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap =
                BitmapFactory.decodeResource(resources, R.drawable.profile_gradient_bg);
        android.graphics.Bitmap.Config bitmapConfig =
                bitmap.getConfig();
        // set default bitmap config if none
        if(bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.rgb(255,255,255));
        // text size in pixels
        paint.setTextSize(450);
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(getName, 0, getName.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width())/2;
        int y = (bitmap.getHeight() + bounds.height())/2;
        canvas.drawText(getName, x, y, paint);
        return bitmap;
    }
}
