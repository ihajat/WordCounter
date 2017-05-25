package ims.com.wordcounter.presenters;


import ims.com.wordcounter.views.MainViews;


public interface MainPresenter {

    void onResume(MainViews views);

    void onItemClicked(int index);

    void onPause();

    public void unSubscribe();

}