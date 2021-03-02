package com.example.easymusic.ui.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.easymusic.utils.MediaController;
import com.example.easymusic.databinding.UiControlBinding;
import com.example.easymusic.service.MP3Service;


public class ControllerView extends FrameLayout {

    private UiControlBinding binding;
    private MediaController mediaController;

    public ControllerView(@NonNull Context context) {
        super(context);
        init();
    }

    public ControllerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ControllerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ControllerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        binding = UiControlBinding.inflate(
                LayoutInflater.from(getContext()),
                this,
                true
        );
        setVisibility(GONE);
        //binding.name.setSelected(true);
//        binding.sbTime.setOnSeekBarChangeListener(this);
    }

    public void setService(MP3Service service) {
        service.getLiveController().observe((LifecycleOwner) getContext(), new Observer<MediaController>() {
            @Override
            public void onChanged(MediaController mediaController) {
                ControllerView.this.mediaController = mediaController;
                if (mediaController == null) {
                    setVisibility(GONE);
                } else {
                    setVisibility(VISIBLE);
                }
                binding.setController(mediaController);
            }
        });
    }

}
