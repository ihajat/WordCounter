package ims.com.wordcounter.modules;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ims.com.wordcounter.interactors.ItemsInteractor;
import ims.com.wordcounter.interactors.ItemsInteractorImpl;

@Module
public class InteractorModule {
    private final Application mApplication;
    public InteractorModule(Application app) {
        mApplication = app;
    }

    @Provides
    @Singleton
    static ItemsInteractor provideItems() {
        return new ItemsInteractorImpl();
    }

}