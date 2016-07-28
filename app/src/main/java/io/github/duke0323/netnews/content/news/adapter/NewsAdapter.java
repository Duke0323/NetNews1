package io.github.duke0323.netnews.content.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.bean.NewDetails;

/**
 * Created by ${Duke} on 2016/6/26.
 */
public class NewsAdapter extends BaseAdapter {
    ArrayList<NewDetails> datas;
    LayoutInflater inflater;
    DisplayImageOptions mOptions;

    public NewsAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);

    }

    public NewsAdapter(ArrayList<NewDetails> datas, Context context) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//内存缓存
                .cacheOnDisk(true)//磁盘缓存
                .bitmapConfig(Bitmap.Config.RGB_565)//图片编码
                .showImageForEmptyUri(R.drawable.biz_pc_read_calendar_no_history)
                .displayer(new FadeInBitmapDisplayer(200))//
                .build();
    }

    public void add(ArrayList<NewDetails> tmp) {
        this.datas.addAll(tmp);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //viewholder的相关优化
        viewHolder holder = null;
        if (convertView != null) {
            holder = (viewHolder) convertView.getTag();
        } else {
            holder = new viewHolder();
            convertView = inflater.inflate(R.layout.item_news, null);
            holder.iv_hot_title = (ImageView) convertView.findViewById(R.id.iv_hot_title);
            holder.tv_hot_title = (TextView) convertView.findViewById(R.id.tv_hot_title);
            holder.tv_hot_src = (TextView) convertView.findViewById(R.id.tv_hot_src);
            holder.tv_hot_zhiding = (TextView) convertView.findViewById(R.id.tv_hot_zhiding);
            holder.tv_hot_reply = (TextView) convertView.findViewById(R.id.tv_hot_reply);
            convertView.setTag(holder);
        }
        NewDetails details = datas.get(position);
        initHolder(holder, details);

        return convertView;
    }

    private void initHolder(viewHolder holder, NewDetails details) {
        ImageLoader.getInstance().displayImage(details.getImgsrc(), holder.iv_hot_title, mOptions);
        holder.tv_hot_title.setText(details.getTitle());
        String source = details.getSource();
        source = source.replace("#", "");
        source = source.replace("$", "");
        holder.tv_hot_src.setText(source);
        if (!TextUtils.isEmpty(details.getSpecialID())) {
            holder.tv_hot_zhiding.setText("置顶");
        } else {
            holder.tv_hot_zhiding.setVisibility(View.GONE);
            holder.tv_hot_reply.setVisibility(View.VISIBLE);
            int replyCount = details.getReplyCount();
            if (replyCount >= 10000) {
                holder.tv_hot_reply.setText(replyCount / 10000 + "万跟帖");
            } else {
                holder.tv_hot_reply.setText(replyCount + "跟帖");
            }
        }


    }

    public int getLast() {
        return datas.size();
    }

    class viewHolder {
        ImageView iv_hot_title;
        TextView tv_hot_title;
        TextView tv_hot_src;
        TextView tv_hot_zhiding;
        TextView tv_hot_reply;
    }
}
