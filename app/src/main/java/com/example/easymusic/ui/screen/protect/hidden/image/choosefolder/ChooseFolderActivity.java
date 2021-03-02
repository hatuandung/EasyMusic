package com.example.easymusic.ui.screen.protect.hidden.image.choosefolder;

import android.content.Intent;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityChooseFolderBinding;
import com.example.easymusic.models.ImageFolder;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.screen.protect.hidden.HiddenActivity;
import com.example.easymusic.ui.screen.protect.hidden.image.ImageHiddenFragment;
import com.example.easymusic.ui.screen.protect.hidden.image.choosefile.ChooseFileActivity;

public class ChooseFolderActivity extends BaseActivity<ActivityChooseFolderBinding, ChooseFolderViewModel> implements FolderListener<ImageFolder> {

    private BaseBindingAdapter<ImageFolder> adapter;

    @Override
    protected Class<ChooseFolderViewModel> getViewModeClass() {
        return ChooseFolderViewModel.class;
    }

    @Override
    protected void init() {
        adapter = new BaseBindingAdapter<>(R.layout.item_choose_folder, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setListener(this);
        adapter.setData(viewModel.getData(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_folder;
    }

    @Override
    public void onItemFolderClicked(ImageFolder item) {
        Intent intent = new Intent(ChooseFolderActivity.this, ChooseFileActivity.class);
        intent.putExtra("folderpath", item.getPath());
        intent.putExtra("foldername", item.getFolderName());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
