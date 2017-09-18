// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.administrator.myapplication;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.myapplication.BR;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView gridRv;
    List<Kind> mDatas;

    /*Observer<List<ZhuangbiImage>> observer = new Observer<List<ZhuangbiImage>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<ZhuangbiImage> images) {
            swipeRefreshLayout.setRefreshing(false);
        }
    };*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        gridRv = (RecyclerView) view.findViewById(R.id.gridRv);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mDatas = getmDatas();
        gridRv.setAdapter(new MainListAdapter(mDatas, BR.kind, getActivity(), R.layout.mainlist_item));
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private List<Kind> getmDatas() {
        mDatas = new ArrayList<>();
        mDatas.add(new Kind("airport", ""));
        mDatas.add(new Kind("arrive", ""));
        mDatas.add(new Kind("checkin", ""));

        return mDatas;
    }

}
