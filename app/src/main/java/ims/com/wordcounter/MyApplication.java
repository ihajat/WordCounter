package ims.com.wordcounter;

import android.app.Application;

import ims.com.wordcounter.components.ApplicationComponent;
import ims.com.wordcounter.components.DaggerApplicationComponent;
import ims.com.wordcounter.modules.AppModule;


public class MyApplication extends Application {

    //use static ApplicationComponent to allow access to context outside of activities/fragments
    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // This setups up the component which is used by other views (activities/fragments/etc., not Android views) for injection.
        // This pulls all modules which have statically declared @Provides methods automatically.

        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static ApplicationComponent component() {
        return component;
    }
}