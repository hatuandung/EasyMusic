package com.example.easymusic.ui.screen.protect.hidden.image.choosefile;

import android.view.View;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public interface FileListener<T extends BaseModel> extends BaseBindingAdapter.BaseBindingListener{

    public void onFileClicked(T item);
    public void onAddClicked();
}
