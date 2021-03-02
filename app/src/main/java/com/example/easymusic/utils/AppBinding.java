package com.example.easymusic.utils;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.easymusic.R;
import com.example.easymusic.ui.screen.video.VideoViewModel;

public class AppBinding {
    @BindingAdapter("duration")
    public static void parseTime(TextView textView, int duration) {
        String str = String.format(
                "%02d:%02d",
                (duration % 3600) / 60 / 1000,
                duration / 1000 % 60);
        textView.setText(str);
    }

    @BindingAdapter("size")
    public static void parseSize(TextView tv, int size) {
        String str = Formatter.formatFileSize(tv.getContext(), size);
        tv.setText(str);
    }

    @BindingAdapter("thumb")
    public static void thumb(ImageView img, int id) {
        Uri uri = Uri.parse("content://media/external/audio/albumart/" + id);
        Glide.with(img).load(uri).error(R.drawable.icon_app_mini).into(img);
    }

    @BindingAdapter("video")
    public static void video(ImageView img, String data){
        Glide.with(img).load(data).into(img);
    }

    @BindingAdapter("firstpic")
    public static void firstpic(ImageView img, String firstpic){
        Glide.with(img)
                .load(firstpic)
                .apply(new RequestOptions().centerCrop())
                .into(img);
    }
}
