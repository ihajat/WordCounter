package ims.com.wordcounter.presenters;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import ims.com.wordcounter.interactors.ItemsInteractorImpl;
import ims.com.wordcounter.models.Word;
import ims.com.wordcounter.observables.FileObservableSource;
import ims.com.wordcounter.views.MainViews;

import static org.junit.Assert.assertEquals;

/**
 * Created by iqbalhajat on 25/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest {
    private MainPresenterImpl presenter;
    private List<Word> wordList;
    private List<String> items;
    @Mock
    Context mMockContext;
    @Mock
    MainViews view;
    @Mock
    ItemsInteractorImpl itemsInteractor;
    @Mock
    FileObservableSource fileObservableSource;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenterImpl(itemsInteractor);
        wordList = Arrays.asList(new Word("hello","3","prime"));
        items = Arrays.asList("hello");
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void isPrime(){
        assertEquals(itemsInteractor.isPrime(2), true);
    }
    @Test
    public void isNotPrime(){
        assertEquals(itemsInteractor.isPrime(4), false);
    }
    @Test
    public void testReadFile(){
        Mockito.when(itemsInteractor.isfileOK()).thenReturn(true);
        presenter.onResume(view);
        presenter.onLoaded(wordList);
        Mockito.verify(view).displayWords(wordList);
    }
    @Test
    public void testNoFile(){
        Mockito.when(itemsInteractor.isfileOK()).thenReturn(false);
        presenter.onResume(view);
        Mockito.verify(view).showMessage("Error: Unable to load file");
    }
}