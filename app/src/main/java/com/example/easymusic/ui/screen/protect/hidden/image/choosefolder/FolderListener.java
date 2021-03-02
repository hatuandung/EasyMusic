package com.example.easymusic.ui.screen.protect.hidden.image.choosefolder;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.ui.base.BaseBindingAdapter;

public interface FolderListener<T extends BaseModel> extends BaseBindingAdapter.BaseBindingListener{

    void onItemFolderClicked(T item);

}
