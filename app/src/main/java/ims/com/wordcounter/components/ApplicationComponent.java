package ims.com.wordcounter.components;

/**
 * Created by iqbalhajat on 24/05/2017.
 */


import javax.inject.Singleton;

import dagger.Component;
import ims.com.wordcounter.MyApplication;
import ims.com.wordcounter.activities.MainActivity;
import ims.com.wordcounter.interactors.ItemsInteractorImpl;
import ims.com.wordcounter.modules.AppModule;
import ims.com.wordcounter.modules.InteractorModule;
import ims.com.wordcounter.modules.PresenterModule;

/**
 * Newly added modules must be added to the @Component annotation here. You must also provide
 * further inject() methods for new classes that want to perform injection.
 */
@Singleton
@Component(modules = {InteractorModule.class, PresenterModule.class, AppModule.class})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
    void inject(MainActivity mainActivity);
    void inject(ItemsInteractorImpl itemsInteractor);
}