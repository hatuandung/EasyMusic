package com.example.easymusic.ui.screen.video;

import android.view.View;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public interface VideoListener<T extends BaseModel> extends BaseBindingAdapter.BaseBindingListener {
    void onItemVideoClicked(T item);
}
