package com.bdappmaniac.bdapp.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.HomeActivity;

public class ForegroundService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    int greenColorValue = Color.GREEN;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getString(R.string.working_status))
                .setContentText(input)
                .setSmallIcon(R.drawable.bd_app)
                .setColor(greenColorValue)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

//    public static void goToNotificationSettings(Context context) {
//        Intent intent = new Intent();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            intent.setData(Uri.fromParts(SCHEME, context.getPackageName(), null));
//        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
//            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
//            intent.putExtra("app_package", context.getPackageName());
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
//            intent.putExtra("app_package", context.getPackageName());
//            intent.putExtra("app_uid", context.getApplicationInfo().uid);
//        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            intent.setData(Uri.parse("package:" + context.getPackageName()));
//        } else {
//            return;
//        }
//        context.startActivity(intent);
//    }
}