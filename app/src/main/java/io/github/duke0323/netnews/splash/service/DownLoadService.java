package io.github.duke0323.netnews.splash.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import io.github.duke0323.netnews.MainActivity;
import io.github.duke0323.netnews.splash.bean.Ad;
import io.github.duke0323.netnews.splash.bean.Ads;
import io.github.duke0323.netnews.util.Contance;
import io.github.duke0323.netnews.util.ImageUtil;
import io.github.duke0323.netnews.util.Md5Helper;

/**
 * Created by ${Duke} on 2016/6/23.
 */
public class DownLoadService extends IntentService {
    //必须要有构造方法 调用super
    public DownLoadService() {
        super("DownLoadService");
    }

    //    private void downloadImage(String imageUrl, String imageName) {
    //
    //    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {

            ImageUtil imageUtil = new ImageUtil();


            Ads ads = (Ads) intent.getSerializableExtra(MainActivity.ADS_NAME);
            List<Ad> data = ads.getAds();
            //判断不为空 没有任何数据
            if (data != null && data.size() == 0) {
                return;
            }
            for (Ad tmp : data) {

                //图片url
                List<String> res_url = tmp.getRes_url();
                //图片 不为空
                if (res_url != null && !TextUtils.isEmpty(res_url.get(0))) {
                    String url = res_url.get(0);
                    String name = Md5Helper.toMD5(url);
                    if(!imageUtil.checkIfImageExisted(name)) {
                        downLoadImage(res_url.get(0), name);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void downLoadImage(String imageUrl, String imageName) {

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Null 失败
            Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            if (bitmap != null) {
                //缓冲到SD卡中
                save2Sd(bitmap, imageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //保存到SD中
    private String save2Sd(Bitmap bitmap, String filename) {
        String stored = null;
        //
        File sd = Environment.getExternalStorageDirectory();
        File folder = new File(sd.getAbsoluteFile(), Contance.FOLDERNAME);
        folder.mkdir();
        File file = new File(folder.getAbsoluteFile(), filename + ".jpg");
        if (file.exists()) {
            return stored;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            stored = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stored;
    }


}
