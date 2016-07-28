package io.github.duke0323.netnews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

import io.github.duke0323.netnews.content.ContentActivity;
import io.github.duke0323.netnews.splash.Activity.WebViewActivity;
import io.github.duke0323.netnews.splash.bean.Action;
import io.github.duke0323.netnews.splash.bean.Ad;
import io.github.duke0323.netnews.splash.bean.Ads;
import io.github.duke0323.netnews.splash.service.DownLoadService;
import io.github.duke0323.netnews.splash.widget.IOnClickRingListener;
import io.github.duke0323.netnews.splash.widget.RingTextView;
import io.github.duke0323.netnews.util.Contance;
import io.github.duke0323.netnews.util.HttpResponse;
import io.github.duke0323.netnews.util.HttpUtil;
import io.github.duke0323.netnews.util.ImageUtil;
import io.github.duke0323.netnews.util.JsonUtil;
import io.github.duke0323.netnews.util.Md5Helper;
import io.github.duke0323.netnews.util.SharePreferenceUtil;

public class MainActivity extends AppCompatActivity {
    public static final String ADS_NAME = "ads";
    public static final String JSON_SAVE = "jsonsave";
    public static final String SPLASH_COUNT = "splash_count";
    public static final String LAST_TIME = "last_time";
    private static final int SKIP = 1;
    private static final int RES_RING = 2;

    public static final String TIME_OUT = "time_out";
    private android.widget.ImageView bottom;
    private android.widget.ImageView adsimage;
    private Handler mHandler;
    private RingTextView ring;
    private int now = 0;
    private int all = 20;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("520it", "onDestroy");
    }

    public void setRing(int progerss, int all) {
        ring.setProgress(progerss, all);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏 set上面
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mHandler = new MyHandler(this);
        this.adsimage = (ImageView) findViewById(R.id.ads_image);
        this.bottom = (ImageView) findViewById(R.id.bottom);
        ring = (RingTextView) findViewById(R.id.ring);
        ring.setListener(new IOnClickRingListener() {
            @Override
            public void onClick() {
                goToContent();

            }
        });
        String json = SharePreferenceUtil.getString(MainActivity.this, JSON_SAVE);
        getDate(json);
        getImage(json);


        Message msg = mHandler.obtainMessage(RES_RING, now, 0);
        mHandler.sendMessage(msg);

    }

    private void getDate(String json) {
        if (TextUtils.isEmpty(json)) {
            getAds();
        } else {
            long last = SharePreferenceUtil.getLong(MainActivity.this, LAST_TIME);
            long time_out = SharePreferenceUtil.getLong(MainActivity.this, TIME_OUT);
            long now = System.currentTimeMillis();
            //600分钟
            if ((now - last) > time_out * 60 * 1000) {
                getAds();
            }
        }
    }

    private void getImage(String json) {
        if (TextUtils.isEmpty(json)) {
            mHandler.sendEmptyMessageAtTime(SKIP, 2000);
            //如果为空跳过
            return;
        }

        int index = SharePreferenceUtil.getInt(MainActivity.this, SPLASH_COUNT);
        ImageUtil imageUtil = new ImageUtil();

        Ads ads = JsonUtil.parse(json, Ads.class);
        List<Ad> datas = ads.getAds();

        final Ad ad = datas.get(index % datas.size());

        SharePreferenceUtil.putInt(MainActivity.this, SPLASH_COUNT, ++index);
        String url = ad.getRes_url().get(0);
        String imageName = Md5Helper.toMD5(url);
        if (imageUtil.checkIfImageExisted(imageName)) {
            File file = imageUtil.getImage("/" + imageName + ".jpg");
            if (file != null) {
                Bitmap bitmap = imageUtil.getBitmap(file.getPath());
                adsimage.setImageBitmap(bitmap);
                adsimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Action action = (Action) adsimage.getTag();
                        if (action != null && !TextUtils.isEmpty(action.getLink_url())) {
                            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                            intent.putExtra(WebViewActivity.ACTION_NAME, action);
                            startActivity(intent);
                            //跳转动画
                            overridePendingTransition(R.anim.enter_in, R.anim.enter_out);

                        }
                    }
                });
                adsimage.setTag(ad.getAction_params());

            }
        }


    }

    private void getAds() {
        HttpUtil util = HttpUtil.getInstance();
        util.doGet(Contance.SPLASH_URL, new HttpResponse<String>(String.class) {

            @Override
            public void onSuccess(String s) {
                long lasttime = System.currentTimeMillis();
                SharePreferenceUtil.putLong(MainActivity.this, LAST_TIME, lasttime);

                Ads tmp = JsonUtil.parse(s, Ads.class);
                SharePreferenceUtil.putLong(MainActivity.this, TIME_OUT, tmp.getNext_req());
                SharePreferenceUtil.putString(MainActivity.this, JSON_SAVE, s);

                Intent intent = new Intent();
                intent.putExtra(ADS_NAME, tmp);
                intent.setClass(MainActivity.this, DownLoadService.class);
                startService(intent);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    //静态内部类无法直接访问activity
    static class MyHandler extends Handler {
        //加弱引用 访问activity
        WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity mActivity) {
            this.mActivity = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = mActivity.get();
            if (mainActivity == null) {
                return;
            }
            switch (msg.what) {
                case SKIP:
                    mainActivity.goToContent();
                    break;
                case RES_RING:
                    int time = msg.arg1;
                    if (time <= mainActivity.all) {
                        Log.d("520it", "ring");
                        mainActivity.setRing(time, mainActivity.all);
                        mainActivity.now++;
                        Message tmp_msg = mainActivity.mHandler.obtainMessage(RES_RING, mainActivity.now, 0);
                        mainActivity.mHandler.sendMessageDelayed(tmp_msg, 250);
                    } else {
                        mainActivity.goToContent();
                    }
                    break;
            }
        }
    }

    public void goToContent() {
        mHandler.removeMessages(RES_RING);
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ContentActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_in, R.anim.enter_out);
        finish();
    }

}
