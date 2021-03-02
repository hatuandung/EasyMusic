package com.example.easymusic.ui.screen.protect.hidden.image;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.easymusic.R;
import com.example.easymusic.databinding.FragmentHiddenImageBinding;
import com.example.easymusic.models.Image;
import com.example.easymusic.models.Song;
import com.example.easymusic.ui.base.BaseBindingAdapter;
import com.example.easymusic.ui.base.BaseFragment;
import com.example.easymusic.ui.screen.MediaListener;

import java.io.File;
import java.util.ArrayList;

public class ImageHiddenFragment extends BaseFragment<FragmentHiddenImageBinding, ImageHiddenViewModel> implements MediaListener<Image> {

    private BaseBindingAdapter<Image> adapter;

    @Override
    protected Class<ImageHiddenViewModel> getViewModelClass() {
        return ImageHiddenViewModel.class;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        adapter = new BaseBindingAdapter<>(
                R.layout.item_hidden_image, getLayoutInflater());
        binding.setAdapter(adapter);
        adapter.setListener(this);

        File file = new File(getContext().getFilesDir().getAbsolutePath());
        viewModel.getImages(getContext(), file);
        //adapter.setData(viewModel.getImages(getContext(), file));
        ArrayList<Image> images = new ArrayList<>();

        for (int i = 0; i < viewModel.getImages(getContext(), file).size(); i++) {
            Image image = new Image();
            image.setImagePath(viewModel.getImages(getContext(), file).get(i).getAbsolutePath());
            image.setImageName(viewModel.getImages(getContext(), file).get(i).getName());
            images.add(image);
        }
        adapter.setData(images);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_hidden_image;
    }

    @Override
    public void onItemMediaClicked(Image item) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(item.getImagePath()));
        intent.setDataAndType(uri,"image/*");
        getActivity().startActivity(intent);
    }

    @Override
    public void onMenuClicked(Image item, View view) {

    }

    @Override
    public void onFavoriteClicked(Image item, View view) {

    }

    @Override
    public void onHiddenImageClicked(Image item, View view) {
        adapter.createHiddenImageMenu(view.getContext(), view, item);
    }


}
