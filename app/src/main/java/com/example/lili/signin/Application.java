package com.example.lili.signin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class Application extends MultiDexApplication {


    private int depth;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the AWS Provider
        AWSProvider.initialize(getApplicationContext());

        registerActivityLifecycleCallbacks(new ActivityLifeCycle());
    }

    private void registerActivityLifecycleCallbacks(ActivityLifeCycle activityLifeCycle) {
    }

    private Context getApplicationContext() {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (depth == 0) {
            Log.d("ActivityLifeCycle", "Application entered foreground");
            AWSProvider.getInstance().getPinpointManager().getSessionClient().startSession();
            AWSProvider.getInstance().getPinpointManager().getAnalyticsClient().submitEvents();
        }
        depth++;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        depth--;
        if (depth == 0) {
            Log.d("ActivityLifeCycle", "Application entered background");
            AWSProvider.getInstance().getPinpointManager().getSessionClient().stopSession();
            AWSProvider.getInstance().getPinpointManager().getAnalyticsClient().submitEvents();
        }
}
