package com.example.easymusic.ui.screen.protect.hidden.video.choosevideo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.easymusic.models.Video;
import com.example.easymusic.ui.base.BaseViewModel;
import com.example.easymusic.utils.SystemData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ChooseVideoViewModel extends BaseViewModel {
    private ArrayList<Video> videos;
    private Context context;

    public ArrayList<Video> getVideos(Context context) {
        if (videos == null) {
            SystemData data = new SystemData(context);
            videos = data.getData(Video.class);
        }
        return videos;
    }

    public void moveVideo(String path, Context context) {
        File file = new File(path);
        try {
            String fileName = file.getName();
            InputStream in = new FileInputStream(file);
            Log.e("onAddClicked: ", file.getPath() + file.getAbsolutePath());
            FileOutputStream outputStream = null;
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            outputStream.close();
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class AsyncVideo extends AsyncTask<String, Void, Void> {
        Context context;
        ProgressDialog dialog;
        String file;

        public AsyncVideo(Context context,  String file) {
            this.context = context;
            this.file = file;
        }

        @Override
        protected Void doInBackground(String... strings) {
            file = strings[0];
            moveVideo(file, context);
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(context, file, "Loading...");
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
        }
    }
}


