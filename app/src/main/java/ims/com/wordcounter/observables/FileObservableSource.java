package ims.com.wordcounter.observables;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;

/**
 * Created by iqbalhajat on 24/05/2017.
 */

public class FileObservableSource implements ObservableSource<String> {

    private List<String> lines;

    public FileObservableSource(String filename, Context context) {
        lines = new ArrayList<>();

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String line = bReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bReader.readLine();
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getWords(){
        return lines;
    }

    @Override
    public void subscribe(Observer<? super String> observer) {
        for(String string: lines){
            observer.onNext(string);
        }
        observer.onComplete();
    }
}
