package ims.com.wordcounter;

import android.support.test.espresso.IdlingResource;

import ims.com.wordcounter.activities.MainActivity;

/**
 * Created by iqbalhajat on 25/05/2017.
 */

public class MainActivityIdlingResource implements IdlingResource {

    private MainActivity activity;
    private ResourceCallback callback;

    public MainActivityIdlingResource(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public String getName() {
        return "MainActivityIdleName";
    }

    @Override
    public boolean isIdleNow() {
        Boolean idle = isIdle();
        if (idle) callback.onTransitionToIdle();
        return idle;
    }

    public boolean isIdle() {
        return activity != null && callback != null && activity.isSyncFinished();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.callback = resourceCallback;
    }
}