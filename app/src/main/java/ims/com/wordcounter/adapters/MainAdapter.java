package ims.com.wordcounter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ims.com.wordcounter.R;
import ims.com.wordcounter.models.Word;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainVH> {

    public interface ClickListener {
        void onClick(int index);
    }

    private ClickListener listener;
    private List<Word> items;

    public MainAdapter(ClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<Word> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_list_row, parent, false);
        return new MainVH(view, this);
    }

    @Override
    public void onBindViewHolder(MainVH holder, int position) {
        Word word = items.get(position);
        holder.word.setText(word.getWord());
        holder.frequency.setText(word.getFrequency());
        holder.primeNo.setText(word.getPrimeNo());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class MainVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        final MainAdapter adapter;
        @BindView(R.id.frequency)
        TextView frequency;
        @BindView(R.id.primeNo)
        TextView primeNo;
        @BindView(R.id.word)
        TextView word;

        MainVH(View itemView, MainAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (adapter != null && adapter.listener != null) {
                adapter.listener.onClick(getAdapterPosition());
            }
        }
    }
}