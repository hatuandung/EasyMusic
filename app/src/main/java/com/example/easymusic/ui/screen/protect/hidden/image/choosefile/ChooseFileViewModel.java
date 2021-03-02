package com.example.easymusic.ui.screen.protect.hidden.image.choosefile;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.easymusic.models.Image;
import com.example.easymusic.ui.base.BaseViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ChooseFileViewModel extends BaseViewModel {

    public ArrayList<Image> getData(String path, Context context ){
        ArrayList<Image> images = new ArrayList<>();
        Uri allVideosuri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE};
        Cursor cursor = context.getContentResolver().query( allVideosuri, projection, MediaStore.Images.Media.DATA + " like ? ", new String[] {"%"+path+"%"}, null);
        try {
            cursor.moveToFirst();
            do{
                Image pic = new Image();

                pic.setImageName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));

                pic.setImagePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));

                pic.setImageSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));

                images.add(pic);
            }while(cursor.moveToNext());
            cursor.close();
            ArrayList<Image> reSelection = new ArrayList<>();
            for(int i = images.size()-1;i > -1;i--){
                reSelection.add(images.get(i));
            }
            images = reSelection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    public void moveFile(ArrayList<File> files, Context context){
        for (int i = 0; i < files.size(); i++) {
            try {
                String fileName = files.get(i).getName();
                InputStream in = new FileInputStream(files.get(i));
                Log.e("onAddClicked: ", files.get(i).getPath() + files.get(i).getAbsolutePath());
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
    }

    public void deleteFile(ArrayList<File> files, Context context){
        for (int i = 0; i < files.size(); i++) {
            files.get(i).delete();
            Uri contentUri = Uri.fromFile(files.get(i));
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
            context.sendBroadcast(mediaScanIntent);
        }
    }




}
