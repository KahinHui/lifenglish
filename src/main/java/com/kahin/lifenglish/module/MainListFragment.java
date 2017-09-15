package com.kahin.lifenglish.module;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kahin.lifenglish.R;
import com.kahin.lifenglish.adapter.ItemListAdapter;
import com.kahin.lifenglish.model.MainListData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 15/5/2017.
 */

public class MainListFragment extends Fragment {

    Unbinder mUnbinder;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    ItemListAdapter mItemListAdapter = new ItemListAdapter();

    Observer<List<Item>> mObserver = new Observer<List<Item>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<Item> value) {
            mSwipeRefreshLayout.setRefreshing(false);
            mItemListAdapter.setItems(value);
        }

        @Override
        public void onError(Throwable e) {
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), "data loading fail"+e.toString(), Toast.LENGTH_LONG).show();
            Log.e("tag", e.toString());
        }

        @Override
        public void onComplete() {

        }
    };

    public  void onCreate(Bundle savedInsatnceState) {
        super.onCreate(savedInsatnceState);

    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInsatnceState) {

        //dosomething();

        if (container == null) {
            return  null;
        }

        View view = layoutInflater.inflate(R.layout.mainlist_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        //mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        if (mRecyclerView != null && mSwipeRefreshLayout != null) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 2));
            mRecyclerView.setAdapter(mItemListAdapter);

            mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
            mSwipeRefreshLayout.setEnabled(false);
        } else {
            Log.e("Tag", "ssss");
        }

        return view;
    }

    public void dosomething() {

        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                MainListData mainList = new MainListData(getActivity().getApplicationContext());
                List<Item> items = mainList.querMainList();
                e.onNext(items);
                e.onComplete();
            }
        });
        observable.subscribeOn(Schedulers.io())//AndroidSchedulers.mainThread())//
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(mObserver);
    }

    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
