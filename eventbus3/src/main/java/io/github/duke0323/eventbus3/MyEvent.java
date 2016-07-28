package io.github.duke0323.eventbus3;

/**
 * Created by ${Duke} on 2016/6/29.
 */
public class MyEvent {

    public String msg;

    public MyEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
