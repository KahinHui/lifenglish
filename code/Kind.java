package com.example.administrator.myapplication;

import android.databinding.ObservableField;

/**
 * Created by Administrator on 2017/9/18.
 */

public class Kind {

    private final ObservableField<String> name = new ObservableField<>();
    private final ObservableField<String> imgUrl = new ObservableField<>();

    public Kind(String name, String imgUrl) {
        this.name.set(name);
        this.imgUrl.set(imgUrl);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getImgUrl() {
        return imgUrl.get();
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl.set(imgUrl);
    }

}
