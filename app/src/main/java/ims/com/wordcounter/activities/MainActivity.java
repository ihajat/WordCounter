package ims.com.wordcounter.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ims.com.wordcounter.MyApplication;
import ims.com.wordcounter.R;
import ims.com.wordcounter.adapters.DividerItemDecoration;
import ims.com.wordcounter.adapters.MainAdapter;
import ims.com.wordcounter.models.Word;
import ims.com.wordcounter.presenters.MainPresenter;
import ims.com.wordcounter.views.MainViews;

public class MainActivity extends Activity implements MainViews, MainAdapter.ClickListener {

    @Inject
    MainPresenter presenter;
    MainAdapter adapter;
    Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The component setup in the ExampleApplication takes all Module classes and fills in @Inject
        // annotated fields for you automatically.
        ((MyApplication) getApplication()).component().inject(this);

        adapter = new MainAdapter(this);

        // Dagger does not provide View injection, so we use ButterKnife for that.
        unbinder = ButterKnife.bind(this);

        InitializeRecyclerView();
    }

    private void InitializeRecyclerView(){
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Tell the main Presenter that the View layer is visible.
        presenter.onResume(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the main Presenter that the View layer is going away.
        presenter.onPause();

        if (isFinishing()) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void displayWords(List<Word> items) {
        adapter.setItems(items);
        loaded = true;
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    // This click listener is from the RecyclerView adapter that we setup.
    @Override
    public void onClick(int index) {
        presenter.onItemClicked(index);
    }

    public boolean isSyncFinished() {
        return loaded;
    }
}