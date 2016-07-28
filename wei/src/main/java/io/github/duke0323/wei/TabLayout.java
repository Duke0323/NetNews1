package io.github.duke0323.wei;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by ${Duke} on 2016/6/25.
 */
public class TabLayout extends LinearLayout implements View.OnClickListener {
    private ArrayList<TabItem> tabs;
    private OnTabClickListener listener;

    private void initView() {
        setOrientation(HORIZONTAL);
    }


    public TabLayout(Context context) {
        super(context);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initData(ArrayList<TabItem> tabs, OnTabClickListener listener) {
        this.tabs = tabs;
        this.listener = listener;
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.height = 1;
        if (tabs != null && tabs.size() > 0) {
            TabView mTabView = null;
            for (int i = 0; i < tabs.size(); i++) {
                mTabView = new TabView(getContext());
                mTabView.setTag(tabs.get(i));
                mTabView.initData(tabs.get(i));
                mTabView.setOnClickListener(this);
                addView(mTabView, params);
            }

        } else {
            throw new IllegalArgumentException("no can no bibi");

        }
    }

    @Override
    public void onClick(View v) {

    }
}
