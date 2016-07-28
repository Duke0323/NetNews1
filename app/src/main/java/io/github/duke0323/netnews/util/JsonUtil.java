package io.github.duke0323.netnews.util;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by ${Duke} on 2016/6/23.
 */
public class JsonUtil<T> {
    Class<T> clz;
    static Gson gson;

    public static <T> T parse(String content, Class<T> clz) {
        try { if (TextUtils.isEmpty(content) || clz == null) {
            return null;
        }

            if (gson == null) {
                gson = new Gson();
            }
            return gson.fromJson(content, clz );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
