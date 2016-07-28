package io.github.duke0323.netnews.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;

/**
 * Created by ${Duke} on 2016/6/24.
 */
public class ImageUtil {
    public boolean checkIfImageExisted(String imageName) {
        Bitmap bitmap = null;
        File file = getImage("/" + imageName + ".jpg");
        String path = file.getAbsolutePath();
        if (path != null) {
            bitmap = BitmapFactory.decodeFile(path);
        }
        if (bitmap == null) {
            return false;
        }
        return true;
    }

    public File getImage(String s) {
        File imagefile = null;

        try {
            //sd卡路径
            String root = Environment.getExternalStorageDirectory().toString();
            //SD卡
            File file = new File(root);
            //sd开有没有
            if (!file.exists()) {
                return null;
            }
            //拿文件
            imagefile = new File(file.getPath() + "/" + Contance.FOLDERNAME + s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagefile;

    }

    public Bitmap getBitmap(String path) {
        //生成bitmap
        //option生成参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }
}
