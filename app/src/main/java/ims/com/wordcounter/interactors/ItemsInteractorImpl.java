package ims.com.wordcounter.interactors;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import ims.com.wordcounter.MyApplication;
import ims.com.wordcounter.models.Word;
import ims.com.wordcounter.observables.FileObservableSource;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ItemsInteractorImpl implements ItemsInteractor {

    @Inject
    Context context;

    private Map<String, Integer> frequencies = null;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FileObservableSource fileObservableSource;

    public ItemsInteractorImpl() {
        MyApplication.component().inject(this);
        frequencies = new TreeMap<>();
        fileObservableSource = new FileObservableSource("Railway-Children-by-E-Nesbit.txt",context);
    }
    @Override
    public boolean isfileOK(){
        return fileObservableSource.getWords().size()>0;
    }

    @Override
    public void loadItems(final LoadListener listener) {

        final Observable<String> observable = Observable.defer(this::getFileObservableSource);
        compositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(String::toLowerCase)
                .flatMap(line -> Observable.fromArray(line.split("[_()\\-.,!'?\" ]")))
                .filter(word -> word.matches("[a-zA-Z]+"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        addWord(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorloadingFile(e);
                    }

                    @Override
                    public void onComplete() {
                        finished(listener);
                    }
                }));
    }

    public FileObservableSource getFileObservableSource(){
        return fileObservableSource;
    }
    @Override
    public void unSubscribe(){
        compositeDisposable.clear();
    }
    public static boolean isPrime(int n) {
        if(n == 2) return true;
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    private void finished( LoadListener listener) {
        Log.i("myapp","finished");
        List<Word> items = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: frequencies.entrySet())
        {
            Word word = new Word(entry.getKey(),  entry.getValue().toString(),isPrime(entry.getValue())?"prime":"not prime");
            items.add(word);
        }
        Log.i("myapp","size: "+items.size());
        listener.onLoaded(items);
    }
    private void errorloadingFile(Throwable e) {
    }

    private void addWord(String word){
        
        // Get the old frequency count
        Integer count = frequencies.get(word);

        // If there was none, put 1; otherwise, increment the count
        count = (count == null) ? 1 : ++count;

        frequencies.put(word, count);
    }
}
