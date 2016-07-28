package io.github.duke0323.eventbus3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    final String TAG = "520it";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        EventBus.getDefault().register(this);
    }

    public void toTwo(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(MyEvent event) {
        String msg = event.getMsg();
        tv.setText(msg);
        Log.d(TAG, "onEventMainThread: MyEvent" + msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void nihao(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, " ThreadMode.MAIN SecondEvent  " + msg);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void dfsdffsf(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "ThreadMode.POSTING SecondEvent   " + msg);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void ninini(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "ThreadMode.BACKGROUND:  " + msg);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void dfsdfsdff(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "ThreadMode.ASYNC:    " + msg);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void fghfghgfhfg(ThirdEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "ThreadMode.ASYNC: ThirdEvent   " + msg);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void ytryrtytry(ThirdEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, " ThreadMode.POSTING: ThirdEvent   " + msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
