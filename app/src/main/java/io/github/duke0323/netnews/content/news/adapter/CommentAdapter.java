package io.github.duke0323.netnews.content.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.bean.Comment;
import io.github.duke0323.netnews.bean.Comments;

/**
 * Created by ${Duke} on 2016/6/28.
 */
public class CommentAdapter extends BaseAdapter {
    final static int TITLE = 0;
    final static int CONTENT = 1;
    DisplayImageOptions mOptions;
    ArrayList<Comments> datas;
    LayoutInflater inflater;

    public CommentAdapter(ArrayList<Comments> datas, Context context) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//内存缓存
                .cacheOnDisk(true)//磁盘缓存
                .bitmapConfig(Bitmap.Config.RGB_565)//图片编码
                .showImageOnLoading(R.drawable.biz_screenshot_attitude_pure_13)
                .showImageOnFail(R.drawable.biz_screenshot_attitude_pure_3)
                .showImageForEmptyUri(R.drawable.biz_screenshot_attitude_naodong_3)
                .displayer(new FadeInBitmapDisplayer(200))//
                .build();
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
        int itemViewType = getItemViewType(position);
        viewHolder holder;
        Comments cs = datas.get(position);

        if (itemViewType == CONTENT) {
            if (convertView != null) {
                holder = (viewHolder) convertView.getTag();
            } else {
                holder = new viewHolder();
                convertView = inflater.inflate(R.layout.item_comment, null);
                holder.userImg = (CircleImageView) convertView.findViewById(R.id.userImg);
                holder.username = (TextView) convertView.findViewById(R.id.username);
                holder.fromwhere = (TextView) convertView.findViewById(R.id.fromwhere);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.body = (TextView) convertView.findViewById(R.id.body);
                holder.dianzan = (TextView) convertView.findViewById(R.id.dianzan);
                convertView.setTag(holder);
            }
            initHolder(holder, cs);
        } else {
            titleViewHolder mHolder;
            if (convertView != null) {
                mHolder = (titleViewHolder) convertView.getTag();
            } else {
                mHolder = new titleViewHolder();
                convertView = inflater.inflate(R.layout.item_com_title, null);
                mHolder.title = (TextView) convertView.findViewById(R.id.com_title);
                convertView.setTag(mHolder);
            }
            mHolder.title.setText(cs.getTitleName());
        }


        return convertView;
    }

    private void initHolder(viewHolder holder, Comments c) {
        ArrayList<Comment> comments = c.getComments();
        if (comments.size() > 0) {
            //最新的一条数据
            Comment comment = comments.get(comments.size() - 1);
            ImageLoader.getInstance().displayImage(comment.getTimg(), holder.userImg, mOptions);


            String name = comment.getN();
            String realFrom = null;
            if (TextUtils.isEmpty(name)) {
                holder.username.setText("匿名网友");
            }
            if (name.contains(":")) {
                name.replaceAll(":", "");
            }
            if (name.lastIndexOf("&nbsp") > 0) {
                String[] split = name.split("&nbsp");
                realFrom = split[0];
            }else{
                realFrom=name;
            }
            holder.username.setText(realFrom);

            holder.fromwhere.setText(comment.getF());

            holder.body.setText(comment.getB());
            holder.dianzan.setText(comment.getV());
        }
    }


    class viewHolder {
        CircleImageView userImg;
        TextView username;
        TextView fromwhere;
        TextView time;
        TextView body;
        TextView dianzan;
    }

    class titleViewHolder {

        TextView title;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        Comments comments = datas.get(position);
        int type = comments.isTitle() ? TITLE : CONTENT;

        return type;
    }
}
