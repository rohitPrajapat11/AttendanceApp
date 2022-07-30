package com.bdappmaniac.bdapp.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.bdappmaniac.bdapp.interfaces.OnChangeConnectivityListener;

import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConnectivityReceiver extends BroadcastReceiver {
    private static final String TAG = "ConnectivityReceiver";
    private boolean accessible;
    private static OnChangeConnectivityListener onChangeConnectivity;

    public static void setConnectivityListener(OnChangeConnectivityListener connectivity) {
        onChangeConnectivity = connectivity;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        //Log.v(TAG, "onReceiveAction = " + intent.getAction());
        if (intent.getExtras() != null) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert cm != null;
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            if (onChangeConnectivity != null) {
                onChangeConnectivity.onChanged(isConnected && isInternetAvailable());
            }

        }
    }

    public boolean isInternetAvailable() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> result = executor.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                InetAddress address = InetAddress.getByName("www.google.com");
                Log.d(TAG, "call: accessible = " + !address.toString().equals(""));
                return !address.toString().equals("");
            }
        });

        try {
            return result.get();
        } catch (Exception exception) {
            //handle exception
            Log.d(TAG, "isInternetAvailable: " + exception.getMessage());
            return false;
        }
    }
}









