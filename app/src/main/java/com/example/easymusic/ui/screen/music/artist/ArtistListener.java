package com.example.easymusic.ui.screen.music.artist;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public interface ArtistListener <T extends BaseModel> extends BaseBindingAdapter.BaseBindingListener{
    public void onArtistItemListener(T item);
}
