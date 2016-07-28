package io.github.duke0323.netnews.util;

import android.text.TextUtils;

/**
 * Created by ${Duke} on 2016/6/23.
 */
public abstract class HttpResponse<T> {
    Class<T> t;

    public HttpResponse(Class<T> t) {
        this.t = t;
    }

    //chenggong
    public abstract void onSuccess(T t);

    //shibai
    public abstract void onError(String msg);

    public void parse(String json) {
        if (TextUtils.isEmpty(json)) {
            onError("失败");
        }
        if (t==String.class) {
            onSuccess((T) json);
            return;
        }
        T t = JsonUtil.parse(json, this.t);
        if (t == null) {
            onError("失败");
        } else {
            onSuccess(t);
        }

    }

}
