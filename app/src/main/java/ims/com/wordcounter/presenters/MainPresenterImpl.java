package ims.com.wordcounter.presenters;

import java.util.List;

import javax.inject.Inject;

import ims.com.wordcounter.interactors.ItemsInteractor;
import ims.com.wordcounter.models.Word;
import ims.com.wordcounter.views.MainViews;


public class MainPresenterImpl implements MainPresenter, ItemsInteractor.LoadListener {

    private ItemsInteractor itemsInteractor;
    private MainViews views;
    /**
     * The parameter here is auto-injected by Dagger, when dagger uses this @Inject annotated constructor.
     * The ItemsInteractor is pulled from the respective module.
     */
    @Inject
    public MainPresenterImpl(ItemsInteractor itemsInteractor) {
        this.itemsInteractor = itemsInteractor;
    }
    @Override
    public void onResume(MainViews views) {
        this.views = views;
        if(itemsInteractor.isfileOK())
        {
            itemsInteractor.loadItems(this);
        }
        else
        {
            views.showMessage("Error: Unable to load file");
        }
    }
    @Override
    public void unSubscribe(){
        itemsInteractor.unSubscribe();
    }

    @Override
    public void onItemClicked(int index) {
        views.showMessage("Clicked item: " + index);
    }

    @Override
    public void onPause() {
        views = null;
    }

    @Override
    public void onLoaded(List<Word> items) {
        views.displayWords(items);
    }

}