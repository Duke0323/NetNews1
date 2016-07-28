package io.github.duke0323.wei;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ${Duke} on 2016/6/25.
 */
public class TabView extends LinearLayout implements View.OnClickListener {
    private ImageView mTabImage;
    private TextView mTabLable;

    private void assignViews(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.item_bottom, this, true);
        mTabImage = (ImageView) findViewById(R.id.tab_image);
        mTabLable = (TextView) findViewById(R.id.tab_lable);
    }

    public void initData(TabItem tabItem) {
        mTabImage.setImageResource(tabItem.imageResId);
        mTabLable.setText(tabItem.labelResId);
    }

    public TabView(Context context) {
        super(context);
        assignViews(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        assignViews(context);

    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        assignViews(context);

    }

    @Override
    public void onClick(View v) {

    }
}
