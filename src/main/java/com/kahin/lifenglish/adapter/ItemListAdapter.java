package com.kahin.lifenglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kahin.lifenglish.R;
import com.kahin.lifenglish.viewModel.MainListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 28/5/2017.
 */

public class ItemListAdapter extends RecyclerView.Adapter {
    List<MainListViewModel> items;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup patent, int viewType) {
        View view = LayoutInflater.from(patent.getContext()).inflate(R.layout.item_mainlist, patent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder)holder;
        MainListViewModel item = items.get(position);
        //To do something

        if (debounceViewHolder.descriptionTv != null)
            debounceViewHolder.descriptionTv.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<MainListViewModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageIv)
        ImageView imageIv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;
        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
