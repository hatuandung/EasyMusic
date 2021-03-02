package com.example.easymusic.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;


import androidx.lifecycle.MutableLiveData;

import com.example.easymusic.models.BaseModel;
import com.example.easymusic.models.FieldInfo;
import com.example.easymusic.ui.base.BaseViewModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SystemData {
    private ContentResolver resolver;

    public SystemData(Context context) {
        resolver = context.getContentResolver();
    }

    public <T extends BaseModel> ArrayList<T> getData(Class<T> clz) {
        ArrayList<T> data = new ArrayList<>();
        try {
            T t = clz.newInstance();
            Cursor cursor = resolver.query(
                    t.getContentUri(),
                    null, null, null, null, null
            );
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                T item = clz.newInstance();
                readInfo(cursor, item);
                data.add(item);
                cursor.moveToNext();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            return data;
        }
    }

    public <T extends BaseModel> MutableLiveData<List<T>> getMutableData(Class<T> clz) {
        List<T> data = new ArrayList<>();
        try {
            T t = clz.newInstance();
            Cursor cursor = resolver.query(
                    t.getContentUri(),
                    null, null, null, null, null
            );
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                T item = clz.newInstance();
                readInfo(cursor, item);
                data.add(item);
                cursor.moveToNext();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            return (MutableLiveData<List<T>>) data;
        }
    }


    private <T extends BaseModel> T readInfo(Cursor cursor, T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);//cho pheeps truy cap thuoc tih private
            FieldInfo info = f.getAnnotation(FieldInfo.class);
            if (info == null) continue;
            try {
                setValue(cursor, t, f, info);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private <T extends BaseModel> void setValue(Cursor cursor, T t, Field f, FieldInfo info) throws IllegalAccessException {
        int index = cursor.getColumnIndex(info.columnName());
        String value = cursor.getString(index);
        switch (f.getType().getSimpleName()) {
            case "int":
                f.setInt(t, Integer.parseInt(value));
                break;
            case "float":
                f.setFloat(t, Float.parseFloat(value));
                break;
            default:
                f.set(t, value);
                break;
        }
    }

//    private <T extends BaseModel> void Delete(Class<T> clz) {
//        try {
//            T t  = clz.newInstance();
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//
//    }
}
