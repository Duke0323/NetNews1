package io.github.duke0323.eventbus3;

/**
 * Created by ${Duke} on 2016/6/29.
 */
public class SecondEvent {

    public String msg;

    public SecondEvent(String msg) {
        this.msg = "MainEvent" + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
