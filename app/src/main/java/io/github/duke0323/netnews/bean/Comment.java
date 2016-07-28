package io.github.duke0323.netnews.bean;

/**
 * Created by ${Duke} on 2016/6/28.
 */
public class Comment {
    //名字
    String n;
    //where
    String f;
    //body
    String b;
    //点赞
    String v;
    //头像
    String timg;
    //vip
    String vip;
    //key
    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "n='" + n + '\'' +
                ", f='" + f + '\'' +
                ", b='" + b + '\'' +
                ", v='" + v + '\'' +
                ", timg='" + timg + '\'' +
                ", vip='" + vip + '\'' +
                ", index=" + index +
                '}';
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getTimg() {
        return timg;
    }

    public void setTimg(String timg) {
        this.timg = timg;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}
