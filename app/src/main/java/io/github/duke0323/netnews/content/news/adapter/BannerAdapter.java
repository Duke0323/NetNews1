package io.github.duke0323.netnews.content.news.adapter;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import io.github.duke0323.netnews.bean.BannerData;

/**
 * Created by ${Duke} on 2016/6/27.
 */
public class BannerAdapter extends PagerAdapter {
    DisplayImageOptions mOptions;
    ArrayList<ImageView> imgs;
    ArrayList<BannerData> datas;

    public BannerAdapter(ArrayList<ImageView> imgs, ArrayList<BannerData> datas) {
        this.imgs = imgs;
        this.datas = datas;
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//内存缓存
                .cacheOnDisk(true)//磁盘缓存
                .bitmapConfig(Bitmap.Config.RGB_565)//图片编码
                .displayer(new FadeInBitmapDisplayer(200))//
                .build();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position % datas.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int nowPosition = position % datas.size();
        ImageView imageView = imgs.get(nowPosition);
        ImageLoader.getInstance().displayImage(datas.get(nowPosition).getImgsrc(),imageView,mOptions);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
