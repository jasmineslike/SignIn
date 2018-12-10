package com.example.lili.signin;

import android.app.Activity;

abstract class MultiDexApplication {
    protected void onCreate() {
    }

    public abstract void onActivityStarted(Activity activity);

    public abstract void onActivityStopped(Activity activity);
}
