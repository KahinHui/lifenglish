package com.kahin.lifenglish.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.TextView;

import com.kahin.lifenglish.ContentActivity;
import com.kahin.lifenglish.R;
import com.kahin.lifenglish.data.SPData;
import com.kahin.lifenglish.model.ItemListData;

/**
 * Created by admin on 3/10/2017.
 */

public class ItemListViewModel {

    public final ObservableField<Integer> id = new ObservableField<>();

    public final ObservableField<String> content = new ObservableField<>();



    public ItemListViewModel(int id, String content) {
        this.id.set(id);
        this.content.set(content);
    }

    public void onItemClick(View view) {

        Context context = view.getContext();

        SPData data = SPData.getInstance(context);

        String type = data.getString(SPData.KEY_TYPE, "");

        TextView tv = (TextView) view.findViewById(R.id.content);

        String content = tv.getText().toString();

        ItemListData itemListData = new ItemListData(context);
        String eng = itemListData.queryByCn(type, content);

        Intent i = new Intent(context, ContentActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra(ContentActivity.NAME_ENG, eng);
        i.putExtra(ContentActivity.NAME_CN, content);

        context.startActivity(i);
        
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }
}
