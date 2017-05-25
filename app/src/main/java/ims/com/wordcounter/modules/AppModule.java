package ims.com.wordcounter.modules;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application mApplication;
    public AppModule(Application app) {
        mApplication = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }

}