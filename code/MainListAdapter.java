// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.administrator.myapplication;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.administrator.myapplication.R.id.image;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.DebounceViewHolder> {

    protected Context mContext;

    List<Kind> kinds;
    int mLayoutId;
    LayoutInflater mInflater;

    private int mVariableId;

    public MainListAdapter(List datas, int variableId, Context context, int layoutId) {
        mContext = context;
        kinds = datas;
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
        mVariableId = variableId;
    }

    public List getKinds() {
        return kinds;
    }

    public void setKinds(List mDatas) {
        this.kinds = mDatas;
    }

    @Override
    public DebounceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mLayoutId, parent, false);
        DebounceViewHolder holder = new DebounceViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(DebounceViewHolder holder, int position) {
        Kind kind = kinds.get(position);
        holder.binding.setVariable(mVariableId, kind);
        holder.binding.executePendingBindings();
        //Glide.with(holder.itemView.getContext()).load(image.image_url).into(debounceViewHolder.imageIv);
        //debounceViewHolder.descriptionTv.setText(image.description);
    }

    @Override
    public int getItemCount() {
        return kinds == null ? 0 : kinds.size();
    }

    public void setImages(List<Kind> kinds) {
        this.kinds = kinds;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public DebounceViewHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

}
