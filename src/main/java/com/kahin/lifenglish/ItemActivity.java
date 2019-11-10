package com.kahin.lifenglish;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kahin.lifenglish.model.ItemListData;
import com.kahin.lifenglish.module.ItemListFragment;
import com.kahin.lifenglish.module.MainListFragment;

public class ItemActivity extends AppCompatActivity {

    public static final String NAME_TYPE = "name_type";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    ItemListFragment mItemListFragment;

    Intent dataIntent;

    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //ItemListData itemListData = new ItemListData(this);
        //itemListData.instert();

        dataIntent = getIntent();

        type = dataIntent.getStringExtra(NAME_TYPE);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString(NAME_TYPE, type);

        mItemListFragment = new ItemListFragment();
        mItemListFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.itemlist_frame, mItemListFragment, "");
        fragmentTransaction.commit();


    }
}
