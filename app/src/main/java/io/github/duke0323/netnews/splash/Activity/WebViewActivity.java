package io.github.duke0323.netnews.splash.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.duke0323.netnews.R;
import io.github.duke0323.netnews.splash.bean.Action;

/**
 * Created by ${Duke} on 2016/6/24.
 */
public class WebViewActivity extends AppCompatActivity {
    private ImageView webback;
    private TextView webtv;
    private ImageView webmore;
    private RelativeLayout webrl;
    private WebView webwv;
    public static final String ACTION_NAME = "action_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Action action = (Action) getIntent().getSerializableExtra(ACTION_NAME);
        String link_url = action.getLink_url();
        setContentView(R.layout.activity_webview);
        this.webwv = (WebView) findViewById(R.id.web_wv);
        this.webrl = (RelativeLayout) findViewById(R.id.web_rl);
        this.webmore = (ImageView) findViewById(R.id.web_more);
        this.webtv = (TextView) findViewById(R.id.web_tv);
        this.webback = (ImageView) findViewById(R.id.web_back);

        webwv.getSettings().setJavaScriptEnabled(true);
        webwv.loadUrl(link_url);
        webwv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                webtv.setText(title);
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
        webwv.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
    }
}
