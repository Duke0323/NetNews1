package io.github.duke0323.netnews.util;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${Duke} on 2016/6/23.
 */
public class HttpUtil {
    private volatile static HttpUtil mHttpUtil;
    private OkHttpClient mHttpClient;


    private HttpUtil() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();
    }

    //singleton
    public  static HttpUtil getInstance() {
        if (mHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (mHttpUtil == null) {
                    mHttpUtil = new HttpUtil();
                }
            }
        }
        return mHttpUtil;
    }

    public void doGet(String url, final HttpResponse res) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("520it", "失败");
                res.onError("失败" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.d("520it", "失败");
                    res.onError("失败");
                }
                String content = response.body().string();

                res.parse(content);
                //Log.d("520it", "content" + content);
                //XLog.json(content);


                //Log.d("520it","httpUtil"+ content);

            }
        });
    }
}
