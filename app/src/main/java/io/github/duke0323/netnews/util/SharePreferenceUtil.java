package io.github.duke0323.netnews.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ${Duke} on 2016/6/24.
 */
public class SharePreferenceUtil {
    public static final String FILENAME="netSave";

    public static void putString(Context context, String title, String content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(title,content);
        edit.apply();
    }

    public static String getString(Context context,String title){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(title,"");
    }


    public static void putInt(Context context,String title,int content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(title, content);
        edit.apply();
    }

    public static int getInt(Context context,String title){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(title, 0);
    }



    public static void putLong(Context context,String title,long content){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(title, content);
        edit.apply();
    }

    public static long getLong(Context context,String title){
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getLong(title, 0);
    }
}
