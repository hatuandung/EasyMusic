package com.example.easymusic.ui.base;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easymusic.BR;
import com.example.easymusic.R;
import com.example.easymusic.dao.AppDatabase;
import com.example.easymusic.models.BaseModel;
import com.example.easymusic.models.Image;
import com.example.easymusic.models.Song;
import com.example.easymusic.ui.screen.main.MainActivity;
import com.google.gson.internal.$Gson$Preconditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class BaseBindingAdapter<T extends BaseModel> extends RecyclerView.Adapter<BaseBindingAdapter.BaseBindingHolder> implements Filterable {

    private ItemFilter itemFilter = new ItemFilter();

    private ArrayList<T> data;
    private ArrayList<Song> dataAll;
    private @LayoutRes
    int resId;
    private LayoutInflater inflater;
    private BaseBindingListener listener;


    public BaseBindingAdapter(int resId, LayoutInflater inflater) {
        this.resId = resId;
        this.inflater = inflater;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
        this.dataAll = (ArrayList<Song>) data;
        notifyDataSetChanged();
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setListener(BaseBindingListener listener) {
        this.listener = listener;
    }

    public void createMenu(Context context, View view, Song song) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.song_popup_menu, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.favorite:
                        try {
                            song.setFavorite(1);
                            AppDatabase.getInstance(context).getSongDao().insert(song);
                            Toast.makeText(context, "Đã thêm", Toast.LENGTH_SHORT).show();
                        } catch (Exception ex) {
                            Toast.makeText(context, "Đã tồn tại", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    case R.id.detail:

                        return true;
                    case R.id.share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = song.getTitle();
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        return true;
                    case R.id.delete_list:


                        return true;
                    case R.id.delete:
                        File file = new File(song.getData());
                        file.delete();
                        Uri contentUri = Uri.fromFile(file);
                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri);
                        context.sendBroadcast(mediaScanIntent);

                        //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                        data.remove(data.indexOf(song));
                        notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void createHiddenImageMenu(Context context, View view, Image image) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.hidden_image_menu, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.unhide:
                        data.remove(data.indexOf(image));
                        moveFile(new File(image.getImagePath()),context);
                        notifyDataSetChanged();
                        //Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();

                        return true;
                    case R.id.hidden_detail:
                        return true;
                    case R.id.delete:
                        new File(image.getImagePath()).delete();
                        data.remove(data.indexOf(image));
                        notifyDataSetChanged();

                        return true;
                    default:
                        return false;
                }
            }
        });
    }


    public void createFavoriteMenu(Context context, View view, Song song) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.favorite_popup_menu, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.favorite_detail:

                        return true;
                    case R.id.favorite_delete:
                        AppDatabase.getInstance(context).getSongDao().delete(song);
                        setData((ArrayList<T>) AppDatabase.getInstance(context).getSongDao().getAll());

                        return true;
                    case R.id.favorite_share:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = song.getTitle();
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        return true;

                    default:
                        return false;
                }
            }
        });
    }


    @NonNull
    @Override
    public BaseBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, resId, parent, false);
        return new BaseBindingHolder(binding);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingAdapter.BaseBindingHolder holder, int position) {
        T t = data.get(position);
        holder.binding.setVariable(BR.item, t);
        if (listener != null) {
            holder.binding.setVariable(BR.listener, listener);
        }

        holder.binding.executePendingBindings();
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    public class BaseBindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BaseBindingHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface BaseBindingListener {

    }

    public class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Song> result = new ArrayList<>();
            for (Song t : dataAll) {
                if (t.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                    result.add(t);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.count = result.size();
            filterResults.values = result;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data = (ArrayList<T>) filterResults.values;
            notifyDataSetChanged();
        }
    }

//
//    private class FileAsyncTask extends AsyncTask<String, Void, Void> {
//        Context context;
//        ProgressDialog dialog;
//
//        public FileAsyncTask( Context context) {
//            this.context = context;
//            dialog = ProgressDialog.show(context, "Your Title", "Loading...");
//        }
//
//        @Override
//        protected void onPreExecute() {
//            dialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(String... params) {
//            //copyFile(params[0], context);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            dialog.dismiss();
//        }
//    }

    public void moveFile(File file, Context context){
        try {
            String fileName = file.getName();
            InputStream inputStream = new FileInputStream(file);

            FileOutputStream outputStream = null;
            String rootPath = Environment.getExternalStorageDirectory().getPath();
            rootPath += "/DCIM/" + fileName;

            File image = new File(rootPath);

            outputStream = new FileOutputStream(image);

            byte[] buf = new byte[1024];
            int length;
            while ((length = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            outputStream.close();
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();

            file.delete();
            Uri contentUri = Uri.fromFile(image);
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
            context.sendBroadcast(mediaScanIntent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
