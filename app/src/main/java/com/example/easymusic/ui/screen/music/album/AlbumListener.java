package com.example.easymusic.ui.screen.music.album;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public interface AlbumListener <T extends BaseModel> extends BaseBindingAdapter.BaseBindingListener {
    public void onAlbumItemListener(T item);
}
