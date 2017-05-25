package ims.com.wordcounter.interactors;

import java.util.List;

import ims.com.wordcounter.models.Word;

public interface ItemsInteractor {

    interface LoadListener {
        void onLoaded(List<Word> items);
    }

    void loadItems(LoadListener listener);

    public void unSubscribe();

    public boolean isfileOK();

}