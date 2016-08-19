package com.mszalek.szalkoczat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Marcinus on 2016-08-18.
 */
public class MiniChatApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
