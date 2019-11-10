package com.kahin.lifenglish.viewModel;

import android.databinding.ObservableField;
import android.view.View;

import com.kahin.lifenglish.TTSUtil;

import io.reactivex.internal.operators.observable.ObservableWindow;

/**
 * Created by admin on 9/10/2017.
 */

public class ContentViewModel {

    public final ObservableField<String> eng = new ObservableField<>();

    public final ObservableField<String> cn = new ObservableField<>();

    public ContentViewModel(String eng, String cn) {
        this.eng.set(eng);
        this.cn.set(cn);
    }

    public void onSpeaking(View view) {
        TTSUtil.Speaking(view.getContext(), eng.get());
    }

    public String getEng() { return eng.get(); }

    public void setEng(String eng) { this.eng.set(eng); }

    public String getCn() { return  cn.get(); }

    public void setCn(String cn) { this.cn.set(cn);}
}
