package com.example.easymusic.ui.screen.protect.hidden.image.choosefile;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityChooseFileBinding;

import com.example.easymusic.models.Image;

import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.base.BaseBindingAdapter;

import java.io.File;
import java.util.ArrayList;

public class ChooseFileActivity extends BaseActivity<ActivityChooseFileBinding, ChooseFileViewModel> implements FileListener<Image> {

    private BaseBindingAdapter<Image> adapter;
    private String path;
    private ArrayList<File> files;

    @Override
    protected Class<ChooseFileViewModel> getViewModeClass() {
        return ChooseFileViewModel.class;
    }

    @Override
    protected void init() {
        files = new ArrayList<>();
        path = getIntent().getStringExtra("folderpath");
        adapter = new BaseBindingAdapter<>(R.layout.item_choose_file, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setListener(this);
        binding.setAdd(this);
        adapter.setData(viewModel.getData(path, this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_file;
    }

    @Override
    public void onFileClicked(Image item) {
        files.add(new File(item.getImagePath()));
        item.setSelected(true);
        Log.e("onFileClicked: ", item.getImagePath() + " " + String.valueOf(item.getSelected()));

        binding.btnAdd.setBackgroundColor(Color.parseColor("#00BCD4"));

    }

    @Override
    public void onAddClicked() {
        viewModel.moveFile(files, this);
        viewModel.deleteFile(files, this);
        adapter.notifyDataSetChanged();
    }

}
