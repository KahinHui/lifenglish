package com.kahin.lifenglish;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kahin.lifenglish.databinding.ActivityContentBinding;
import com.kahin.lifenglish.viewModel.ContentViewModel;

public class ContentActivity extends AppCompatActivity {

    Context mContext = this;

    ActivityContentBinding binding;

    Intent dataIntent;

    public static final String NAME_ENG = "eng";
    public static final String NAME_CN = "cn";

    String eng, cn;
    ContentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_content);

        initData();
    }

    private void initData() {
        dataIntent = getIntent();

        eng = dataIntent.getStringExtra(NAME_ENG);
        cn = dataIntent.getStringExtra(NAME_CN);

        viewModel = new ContentViewModel(eng, cn);

        binding.setVariable(BR.content, viewModel);

    }
}
