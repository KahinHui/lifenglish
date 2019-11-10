package com.kahin.lifenglish.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kahin.lifenglish.R;
import com.kahin.lifenglish.model.ItemListData;
import com.kahin.lifenglish.module.ItemListFragment.OnListFragmentInteractionListener;
import com.kahin.lifenglish.module.dummy.DummyContent.DummyItem;
import com.kahin.lifenglish.viewModel.ItemListViewModel;
import com.kahin.lifenglish.viewModel.MainListViewModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    private List<ItemListViewModel> mDatas;

    private int mLayoutId;
    private LayoutInflater mInflater;
    //mvvm綁定的viedModel引用
    private int mVariableId;

    public ItemListRecyclerViewAdapter(List data, int variableId, Context context, int LayoutId) {
        mContext = context;
        mDatas = data;
        mLayoutId = LayoutId;
        mInflater = LayoutInflater.from(context);
        mVariableId = variableId;
    }

    public List getDatas() { return mDatas; }

    public void setDatas(List mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mLayoutId, parent, false);
        ViewHolder mHolder = new ViewHolder(binding.getRoot());
        mHolder.setDataBinding(binding);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ItemListViewModel data = mDatas.get(position);
        holder.mDataBinding.setVariable(mVariableId, data);
        holder.mDataBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public ViewHolder(View view) {
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
