package io.github.duke0323.wei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnTabClickListener {

    private TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tablayout = (TabLayout) findViewById(R.id.tab_layout);
        initData();
    }

    private void initData() {
        ArrayList<TabItem> tabs = new ArrayList<>();
        tabs.add(new TabItem(R.drawable.qq, R.string.qq));
        tabs.add(new TabItem(R.drawable.sina, R.string.qq));
        tabs.add(new TabItem(R.drawable.zhifubao, R.string.qq));
        tabs.add(new TabItem(R.drawable.weixin, R.string.qq));
        tablayout.initData(tabs, this);
    }

    @Override
    public void onTabClick(TabItem tabItem) {

    }
}
