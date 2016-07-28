package io.github.duke0323.netnews.content.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.bean.BannerData;
import io.github.duke0323.netnews.bean.Hot;
import io.github.duke0323.netnews.bean.NewDetails;
import io.github.duke0323.netnews.bean.ArticleWebActivity;
import io.github.duke0323.netnews.content.news.adapter.BannerAdapter;
import io.github.duke0323.netnews.content.news.adapter.NewsAdapter;
import io.github.duke0323.netnews.util.Contance;
import io.github.duke0323.netnews.util.HttpResponse;
import io.github.duke0323.netnews.util.HttpUtil;


/**
 * Created by ${Duke} on 2016/6/25.
 */
public class TopFragment extends Fragment {
    private ListView mListView;
    private NewsAdapter mAdapter;
    private ArrayList<NewDetails> mNewDetailses;
    private ArrayList<BannerData> ads;
    private InnerHandler mHandler;
    //第一次请求成功
    static final int INIT_LIST_SUCCESS = 1;
    //下拉
    static final int GET_LIST_MORE = 2;
    int index = 0;
    int size = 20;
    boolean isEnd = false;
    TextView banner_title;
    private ArrayList<ImageView> mDots_array;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHandler = new InnerHandler(this);
        View view = inflater.inflate(R.layout.fragment_top, null);
        mListView = (ListView) view.findViewById(R.id.lv_top);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //mAdapter = new NewsAdapter(new ArrayList<NewDetails>(), getActivity());
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        //滑动结束
                        Log.d("520it", "idle" + isEnd);
                        if (isEnd) {
                            getData(false);
                        }
                        break;
                    case SCROLL_STATE_FLING:

                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:

                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //XLog.d(view.getLastVisiblePosition());
                //                if(mAdapter!=null) {
                //                    Log.d("520it", "lastvis" + view.getLastVisiblePosition() + "last" + mAdapter.getLast());
                //                }
                if (mAdapter != null && view.getLastVisiblePosition() == mAdapter.getLast()) {
                    isEnd = true;
                } else {
                    isEnd = false;
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewDetails item = (NewDetails) mAdapter.getItem(position-1);
                Intent intent = new Intent();
                intent.setClass(getContext(), ArticleWebActivity.class);
                intent.putExtra(ArticleWebActivity.DOC_ID,item.getDocid());
                startActivity(intent);
            }
        });
        //第一次获取删除
        getData(true);

    }

    //首先取数据
    public void getData(final boolean isFirst) {
        String hotUrl = Contance.getHotUrl(index, size);
        HttpUtil util = HttpUtil.getInstance();
        util.doGet(hotUrl, new HttpResponse<Hot>(Hot.class) {
            Message message;

            @Override
            public void onSuccess(Hot hot) {
                if (hot == null && hot.getT1348647909107() == null) {
                    return;
                }
                ArrayList<NewDetails> newDetailses;
                if (isFirst) {
                    //在子线程中进行数据的获取
                    newDetailses = hot.getT1348647909107();
                    ads = newDetailses.get(0).getAds();
                    //取图集
                    newDetailses.remove(0);
                    message = mHandler.obtainMessage(INIT_LIST_SUCCESS);
                } else {
                    newDetailses = hot.getT1348647909107();
                    message = mHandler.obtainMessage(GET_LIST_MORE);
                }
                //传递到UI
                message.obj = newDetailses;
                mHandler.sendMessage(message);
                index++;
            }

            @Override
            public void onError(String msg) {
                Log.d("520it", "onError");

            }
        });
    }

    private void setBannerDatas() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_head, null);

        LinearLayout dots = (LinearLayout) headView.findViewById(R.id.ll_bottom_dots);

        ViewPager pager = (ViewPager) headView.findViewById(R.id.head_vp);
        banner_title = (TextView) headView.findViewById(R.id.head_tv_title);
        mDots_array = new ArrayList<>();


        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < ads.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews.add(imageView);
            ImageView dotsImg = new ImageView(getActivity());
            dotsImg.setImageResource(R.drawable.head_bottom_dot_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(9, 0, 0, 0);
            mDots_array.add(dotsImg);
            dots.addView(dotsImg, params);
        }

        banner_title.setText(ads.get(0).getTitle());
        mDots_array.get(0).setImageResource(R.drawable.head_bottom_dot_white);
        pager.setCurrentItem((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % ads.size());

        BannerAdapter bannerAdapter = new BannerAdapter(imageViews, ads);
        pager.setAdapter(bannerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int nowPosition = position % ads.size();
                banner_title.setText(ads.get(nowPosition).getTitle());
                changeDots(nowPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mListView.addHeaderView(headView);
    }

    private void changeDots(int position) {
        for (int i = 0; i < mDots_array.size(); i++) {
            ImageView imageView = mDots_array.get(i);
            if (i == position) {
                imageView.setImageResource(R.drawable.head_bottom_dot_white);
            } else {
                imageView.setImageResource(R.drawable.head_bottom_dot_gray);
            }
        }
    }

    private void initList(ArrayList<NewDetails> tmp) {
        mAdapter = new NewsAdapter(tmp, getActivity());
        mListView.setAdapter(mAdapter);
    }

    private void updateList(ArrayList<NewDetails> tmp) {
        mAdapter.add(tmp);
    }

    static class InnerHandler extends Handler {
        WeakReference<TopFragment> fra;

        public InnerHandler(TopFragment fra) {
            this.fra = new WeakReference<TopFragment>(fra);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            TopFragment topFragment = fra.get();

            if (topFragment == null) {
                return;
            }

            ArrayList<NewDetails> data;
            switch (msg.what) {
                case INIT_LIST_SUCCESS:
                    data = (ArrayList<NewDetails>) msg.obj;
                    topFragment.setBannerDatas();
                    topFragment.initList(data);
                    break;
                case GET_LIST_MORE:
                    data = (ArrayList<NewDetails>) msg.obj;
                    topFragment.updateList(data);
                    break;
            }
        }
    }
}
