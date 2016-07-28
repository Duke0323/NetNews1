package io.github.duke0323.netnews.bean;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.util.Contance;
import io.github.duke0323.netnews.util.HttpResponse;
import io.github.duke0323.netnews.util.HttpUtil;
import io.github.duke0323.netnews.util.JsonUtil;

/**
 * Created by ${Duke} on 2016/6/27.
 */

public class ArticleWebActivity extends Activity {
    public static final String DOC_ID = "docID";
    String docId;
    private android.widget.ImageView webback;
    private android.widget.ImageView webmenu;
    private android.widget.RelativeLayout webnavi;
    private WebView web;
    private android.widget.EditText webbottomedit;
    private android.widget.TextView webbottomsend;
    private android.widget.TextView webbottomreply;
    private android.widget.ImageView share;
    private android.widget.LinearLayout webbottomreplyshare;
    private android.widget.RelativeLayout webbottom;
    private static final int INIT_SUCCESS = 1;
    private InnerHandler mHandler;
    boolean isFocus = true;
    private RelativeLayout rl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mHandler = new InnerHandler(this);
        rl = (RelativeLayout) findViewById(R.id.rl);
        //设置图片显示区域
        final Drawable drawable = getResources().getDrawable(R.drawable.biz_tie_comment_reply_write);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());


        this.webbottom = (RelativeLayout) findViewById(R.id.web_bottom);
        this.webbottomreplyshare = (LinearLayout) findViewById(R.id.web_bottom_reply_share);
        this.share = (ImageView) findViewById(R.id.share);
        this.webbottomreply = (TextView) findViewById(R.id.web_bottom_reply);
        webbottomreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ArticleWebActivity.this, CommentActivity.class);
                intent.putExtra(DOC_ID, docId);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_in, R.anim.enter_out);
            }
        });
        this.webbottomsend = (TextView) findViewById(R.id.web_bottom_send);
        this.webbottomedit = (EditText) findViewById(R.id.web_bottom_edit);
        this.web = (WebView) findViewById(R.id.web);
        this.webnavi = (RelativeLayout) findViewById(R.id.web_navi);
        this.webmenu = (ImageView) findViewById(R.id.web_menu);
        this.webback = (ImageView) findViewById(R.id.web_back);
        webback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        webbottomedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isFocus = hasFocus;
                if (hasFocus) {
                    webbottomreplyshare.setVisibility(View.GONE);
                    webbottomsend.setVisibility(View.VISIBLE);
                    webbottomedit.setHint("");
                    webbottomedit.setCompoundDrawables(null, null, null, null);

                } else {
                    webbottomreplyshare.setVisibility(View.VISIBLE);
                    webbottomsend.setVisibility(View.GONE);
                    webbottomedit.setHint("写跟帖");
                    webbottomedit.setCompoundDrawables(drawable, null, null, null);

                }
            }
        });

        docId = getIntent().getStringExtra(DOC_ID);
        getData(docId);


    }

    @Override
    public void onBackPressed() {

        if (isFocus) {
            web.requestFocus();
            return;
        }
        finish();
    }

    public void getData(final String id) {
        String newscontentUrl = Contance.getNewscontentUrl(id);
        Log.d("520it", "item网址" + newscontentUrl);
        HttpUtil util = HttpUtil.getInstance();
        util.doGet(newscontentUrl, new HttpResponse<String>(String.class) {
            @Override
            public void onSuccess(String s) {
                try {
                    Log.d("520it", "onSuccess");

                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject content = jsonObject.optJSONObject(id);
                    Log.d("520it", "jsonObject" + jsonObject.toString());
                    Log.d("520it", "content" + content.toString());
                    NewsContent newsContent = JsonUtil.parse(content.toString(), NewsContent.class);
                    if (newsContent != null) {
                        String body = newsContent.getBody();
                        List<Image> images = newsContent.getImg();
                        for (int i = 0; i < images.size(); i++) {
                            String imgHtml = "<IMG src='" + images.get(i).getSrc() + "'/>";
                            body = body.replaceFirst("<!--IMG#" + i + "-->", imgHtml);
                        }

                        String title = "<p><span style='font-size:22px;'><strong>" + newsContent.getTitle() + "</strong></span></p>" + body;
                        String source = "<p><span style='color:#666666;'>" + newsContent.getSource() + "&nbsp&nbsp" + newsContent.getPtime() + "</span></p>";
                        body = source + title + body;
                        body = "<Html><head><style>img{width:100%}</style></head>" + body + "</Html>";
                        Message message = mHandler.obtainMessage(INIT_SUCCESS);
                        message.obj = body;
                        message.arg1 = newsContent.getReplyCount();
                        mHandler.sendMessage(message);

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

    public void showContent(String htmlurl) {
        web.loadDataWithBaseURL(null, htmlurl, "text/html", "utf-8", null);
    }

    private void showReply(int reply) {
        webbottomreply.setText(String.valueOf(reply));
    }

    static class InnerHandler extends Handler {
        //预防内存泄漏
        WeakReference<ArticleWebActivity> mActivity;

        public InnerHandler(ArticleWebActivity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArticleWebActivity activity = mActivity.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case INIT_SUCCESS:
                    String body = (String) msg.obj;
                    int replycount = msg.arg1;
                    activity.showReply(replycount);
                    activity.showContent(body);
                    break;
            }
        }
    }

}
