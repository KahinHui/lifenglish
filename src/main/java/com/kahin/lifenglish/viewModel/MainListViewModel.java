// (c)2016 Flipboard Inc, All Rights Reserved.

package com.kahin.lifenglish.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.kahin.lifenglish.BR;
import com.kahin.lifenglish.ItemActivity;
import com.kahin.lifenglish.SQLite.DBHelper;
import com.kahin.lifenglish.data.SPData;

public class MainListViewModel {
    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();

    public MainListViewModel() {}


    public MainListViewModel(String description, String imageUrl) {
        this.description.set(description);
        this.imageUrl.set(imageUrl);
    }

    public void onItemClick(View view) {
        String name = type2TableName(getDescription());

        Context context = view.getContext();

        SPData data = SPData.getInstance(context);
        data.save(SPData.KEY_TYPE, name);

        //if (name.equals("機場")) {
            Intent intent = new Intent(context, ItemActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra(ItemActivity.NAME_TYPE, name);

            context.startActivity(intent);
        //}
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getImageUrl() {
        return imageUrl.get();
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl.set(imageUrl);
    }

    private String type2TableName(String type) {
        String name = "";
        switch (type) {
            case DBHelper.TYPE_AIR:
                name = DBHelper.TABLE_AIR;
                break;
            case DBHelper.TYPE_AIRPORT:
                name = DBHelper.TABLE_AIRPOET;
                break;
            case DBHelper.TYPE_CUSTOMS:
                name = DBHelper.TABLE_CUSTOMS;
                break;
            case DBHelper.TYPE_SHOPPING:
                name = DBHelper.TABLE_SHOPPING;
                break;
            case DBHelper.TYPE_HOTEL:
                name = DBHelper.TABLE_HOTEL;
                break;
            case DBHelper.TYPE_RESTAURANT:
                name = DBHelper.TABLE_RESTAURANT;
                break;
            case DBHelper.TYPE_TICKET:
                name = DBHelper.TABLE_TICKET;
                break;
            case DBHelper.TYPE_TRAFFIC:
                name = DBHelper.TABLE_TRAFFIC;
                break;
        }

        return name;
    }
}
