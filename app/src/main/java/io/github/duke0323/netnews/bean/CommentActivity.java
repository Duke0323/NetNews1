package io.github.duke0323.netnews.bean;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.content.news.adapter.CommentAdapter;
import io.github.duke0323.netnews.util.Contance;
import io.github.duke0323.netnews.util.HttpResponse;
import io.github.duke0323.netnews.util.HttpUtil;
import io.github.duke0323.netnews.util.JsonUtil;

public class CommentActivity extends AppCompatActivity {
    private ListView listView;
    String docId;
    ArrayList<Comments> mComments;
    private InnerHandler mHandler;
    public static final int INIT_SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mHandler = new InnerHandler(this);
        mComments = new ArrayList<>();
        listView = (ListView) findViewById(R.id.comment);
        docId = getIntent().getStringExtra(ArticleWebActivity.DOC_ID);
        getData(docId);

    }

    public void getData(String docId) {
        String comment_url = Contance.getComment_Url(docId);
        HttpUtil.getInstance().doGet(comment_url, new HttpResponse<String>(String.class) {
            @Override
            public void onSuccess(String s) {
                Log.d("520it", "String" + s);
                JSONObject jsonObject = null;
                try {
                    //拿到json后
                    jsonObject = new JSONObject(s);
                    //得到hot
                    JSONArray hotPosts = jsonObject.getJSONArray("hotPosts");
                    Comments title = new Comments();
                    title.setTitle(true);
                    title.setTitleName("最新跟帖");
                    mComments.add(title);
                    //遍历hot
                    for (int i = 0; i < hotPosts.length(); i++) {
                        Comments comments = new Comments();
                        //继续取对象
                        JSONObject object = hotPosts.optJSONObject(i);
                        Iterator<String> keys = object.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            JSONObject comment = object.optJSONObject(next);
                            Comment parse = JsonUtil.parse(comment.toString(), Comment.class);
                            //可能为空
                            if (parse != null) {
                                parse.setIndex(Integer.parseInt(next));
                            }
                            comments.add(parse);
                        }
                        //这是内容
                        title.setTitle(false);
                        mComments.add(comments);
                        mHandler.sendEmptyMessage(INIT_SUCCESS);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(String msg) {
                Log.d("520it", "onError");
            }
        });
    }

    private void initListView() {
        CommentAdapter commentAdapter = new CommentAdapter(mComments, this);
        listView.setAdapter(commentAdapter);
    }

    static class InnerHandler extends Handler {
        WeakReference<CommentActivity> mActivity;

        public InnerHandler(CommentActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CommentActivity commentActivity = mActivity.get();
            if (commentActivity == null) {
                return;
            }
            switch (msg.what) {
                case INIT_SUCCESS:
                    commentActivity.initListView();
                    break;
            }

        }
    }
}
