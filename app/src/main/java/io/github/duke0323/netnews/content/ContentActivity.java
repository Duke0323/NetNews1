package io.github.duke0323.netnews.content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.content.mine.FragmentMine;
import io.github.duke0323.netnews.content.news.FragmentOne;
import io.github.duke0323.netnews.content.read.FragmentRead;
import io.github.duke0323.netnews.content.topic.FragmentTopic;
import io.github.duke0323.netnews.content.video.FragmentVideo;

public class ContentActivity extends AppCompatActivity {

    private android.widget.FrameLayout content;
    private android.widget.FrameLayout tabcontent;
    private android.support.v4.app.FragmentTabHost tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        this.tab = (FragmentTabHost) findViewById(R.id.tab);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.content = (FrameLayout) findViewById(R.id.content);
        //
        tab.setup(this, getSupportFragmentManager(), R.id.content);

        int[] images = {R.drawable.news_seletor, R.drawable.reading_seletor
                , R.drawable.video_seletor, R.drawable.topic_seletor, R.drawable.mine_seletor};
        String[] bottom_title = {
                "新闻", "阅读", "视频", "话题", "我"
        };
        Class[] fragments = {FragmentOne.class, FragmentRead.class
                , FragmentVideo.class, FragmentTopic.class, FragmentMine.class};
        for (int i = 0; i < bottom_title.length; i++) {
            View view = getView(images[i], bottom_title[i]);
            tab.addTab(tab.newTabSpec(String.valueOf(i)).setIndicator(view), fragments[i], null);
        }
    }

    public void switchContent(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment)
                .commit();

    }

    public View getView(int imagesrc, String str) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.item_bottom_title, null);
        ImageView image = (ImageView) view.findViewById(R.id.bottom_icon);
        image.setImageResource(imagesrc);
        TextView tv = (TextView) view.findViewById(R.id.bottom_title);
        tv.setText(str);
        return view;
    }
}
