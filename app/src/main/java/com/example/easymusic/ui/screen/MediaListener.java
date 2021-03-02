package com.example.easymusic.ui.screen;

import android.view.View;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public interface MediaListener<T extends BaseModel> extends BaseBindingAdapter.BaseBindingListener {
    void onItemMediaClicked(T item);
    void onMenuClicked(T item, View view);
    void onFavoriteClicked(T item, View view);
    void onHiddenImageClicked(T item, View view);
}
