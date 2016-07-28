package io.github.duke0323.eventbus3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class Main2Activity extends AppCompatActivity {
    private Button tofirst;
    private Button tosec;
    private Button tothr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tofirst = (Button) findViewById(R.id.tofirst);
        tofirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MyEvent("hi,MyEvent"));

            }
        });
        tosec = (Button) findViewById(R.id.tosec);
        tosec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SecondEvent("hi,SecondEvent"));
            }
        });
        tothr = (Button) findViewById(R.id.tothr);
        tothr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ThirdEvent("hi,ThirdEvent"));
            }
        });

    }

}
