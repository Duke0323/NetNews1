package io.github.duke0323.eventbustest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    final String TAG = "520it";

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

    public void onEventMainThread(MyEvent event) {
        String msg = event.getMsg();
        tv.setText(msg);
        Log.d(TAG, "onEventMainThread: MyEvent" + msg);
    }

    public void onEventMainThread(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEventMainThread: SecondEvent  " + msg);
    }

    public void onEvent(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEvent: SecondEvent   " + msg);
    }

    public void onEventBackgroundThread(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEventBackgroundThread: SecondEvent  " + msg);
    }

    public void onEventAsync(SecondEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEventAsync: SecondEvent   " + msg);
    }

    public void onEventAsync(ThirdEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEventAsync: ThirdEvent   " + msg);
    }


    public void onEvent(ThirdEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEvent: ThirdEvent   " + msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
