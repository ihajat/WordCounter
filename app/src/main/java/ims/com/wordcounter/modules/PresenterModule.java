package ims.com.wordcounter.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ims.com.wordcounter.interactors.ItemsInteractor;
import ims.com.wordcounter.presenters.MainPresenter;
import ims.com.wordcounter.presenters.MainPresenterImpl;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    static MainPresenter providesMain(ItemsInteractor itemsInteractor) {
        // The parameter here is injected by dagger also, no need to provide it.
        return new MainPresenterImpl(itemsInteractor);
    }
}