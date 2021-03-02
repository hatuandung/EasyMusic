package com.example.easymusic.ui.screen.protect.hidden.video.choosevideo;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public interface HiddenVideoListener<T extends BaseModel> extends BaseBindingAdapter.BaseBindingListener {

    public void onVideoHiddenClicked(T item);
    public void onAddVideoClicked();
}
