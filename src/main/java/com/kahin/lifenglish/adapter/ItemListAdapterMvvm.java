package com.kahin.lifenglish.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kahin.lifenglish.viewModel.MainListViewModel;

import java.util.List;

/**
 * Created by admin on 16/9/2017.
 */

public class ItemListAdapterMvvm extends RecyclerView.Adapter<ItemListAdapterMvvm.CommonHolder> {

    private Context mContext;

    private List<MainListViewModel> mDatas;

    private int mLayoutId;
    private LayoutInflater mInflater;
    //mvvm綁定的viedModel引用
    private int mVariableId;

    public ItemListAdapterMvvm(List data, int variableId, Context context, int LayoutId) {
        mContext = context;
        mDatas = data;
        mLayoutId = LayoutId;
        mInflater = LayoutInflater.from(mContext);
        mVariableId = variableId;
    }

    public List getmDatas() {
        return mDatas;
    }

    public void setmDatas(List mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();

    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mLayoutId, parent, false);
        CommonHolder mHolder = new CommonHolder(binding.getRoot());
        mHolder.setDataBinding(binding);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder holder, int position) {
        MainListViewModel data = mDatas.get(position);
        holder.mDataBinding.setVariable(mVariableId, data);
        holder.mDataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    class CommonHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mDataBinding;

        public CommonHolder(View view) {
            super(view);
        }

        public ViewDataBinding getDataBinding() {
            return mDataBinding;
        }

        public void setDataBinding(ViewDataBinding binding) {
            this.mDataBinding = binding;
        }

    }
}
