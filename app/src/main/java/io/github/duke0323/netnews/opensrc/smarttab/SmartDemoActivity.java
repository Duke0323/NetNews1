package io.github.duke0323.netnews.opensrc.smarttab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.content.news.TopFragment;
import io.github.duke0323.netnews.opensrc.smarttab.adapter.DemoAdapter;

public class SmartDemoActivity extends FragmentActivity {

    private android.widget.FrameLayout tab;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tab = (FrameLayout) findViewById(R.id.tab);
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);

        tab.addView(LayoutInflater.from(this).inflate(R.layout.demo_custom_tab_text, tab, false));

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        String[] titles = {
                "头条", "娱乐", "热点", "体育", "本地", "财经", "网易号"
        };
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            TopFragment topFragment = new TopFragment();
            fragments.add(topFragment);
        }
        DemoAdapter adapter = new DemoAdapter(getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewpager);
    }
}
