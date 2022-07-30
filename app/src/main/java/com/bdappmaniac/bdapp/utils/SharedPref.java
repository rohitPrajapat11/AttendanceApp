package com.bdappmaniac.bdapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.bdappmaniac.bdapp.Api.response.LoginResponse;
import com.google.gson.Gson;

public class SharedPref {

    private static SharedPreferences mSharedPref;
    public static final String USER_DETAILS = "user_details";
    public static final String STATUS = "status";
    public static final String ON_STOP_TIME ="on_stop_time";
    public static final String ON_STOP_TIMER ="on_stop_timer";
    public static final String CHECK_IN_TIME = "checkIn_time";
    public static final String CHECK_OUT_TIME = "checkout_time";

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }

    private static SharedPreferences.Editor getEditor() {
        return mSharedPref.edit();
    }

    public static String getStringValue(String key) {
        return mSharedPref.getString(key, "");
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public static void putUserDetails(LoginResponse userDetails) {
        Gson gson = new Gson();
        String json = gson.toJson(userDetails);
        putString(USER_DETAILS, json);
    }

    public static LoginResponse getUserDetails() {
        Gson gson = new Gson();
        String json = getStringValue(USER_DETAILS);
        return gson.fromJson(json, LoginResponse.class);
    }



}