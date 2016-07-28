package io.github.duke0323.netnews.content.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.opensrc.smarttab.SmartTabLayout;
import io.github.duke0323.netnews.opensrc.smarttab.adapter.DemoAdapter;

/**
 * Created by ${Duke} on 2016/6/25.
 */
public class FragmentOne extends Fragment {
    private android.widget.FrameLayout tab;
    private ViewPager viewpager;
    private ImageView show_change;
    boolean showMenu = false;
    private TextView textView;
    private RelativeLayout rl_pull;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab = (FrameLayout) getActivity().findViewById(R.id.tab);
        rl_pull = (RelativeLayout) getActivity().findViewById(R.id.rl_pull);
        viewpager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        textView = (TextView) getActivity().findViewById(R.id.textView);
        show_change = (ImageView) getActivity().findViewById(R.id.show_change);
        tab.addView(LayoutInflater.from(getActivity()).inflate(R.layout.demo_custom_tab_text, tab, false));
        show_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showMenu) {

                    rl_pull.setBackgroundColor(Color.TRANSPARENT);
                    textView.setVisibility(View.GONE);

                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.news_rotate_back);
                    animation.setFillAfter(true);
                    show_change.startAnimation(animation);
                } else {
                    rl_pull.setBackgroundResource(R.color.main_tiqhuan);
                    textView.setVisibility(View.VISIBLE);

                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.news_rotate_go);
                    animation.setFillAfter(true);
                    show_change.startAnimation(animation);

                }
                showMenu = !showMenu;
            }
        });
        SmartTabLayout viewPagerTab = (SmartTabLayout) getActivity().findViewById(R.id.viewpagertab);
        String[] titles = {
                "头条", "娱乐", "热点", "体育", "本地", "财经", "网易号"
        };
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            if (i == 0) {
                TopFragment topFragment = new TopFragment();
                fragments.add(topFragment);
            } else {
                AmuseFragment amuseFragment = new AmuseFragment();
                fragments.add(amuseFragment);
            }
        }
        DemoAdapter adapter = new DemoAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewpager);
    }
}
