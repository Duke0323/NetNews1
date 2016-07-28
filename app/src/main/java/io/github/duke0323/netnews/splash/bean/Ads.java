package io.github.duke0323.netnews.splash.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${Duke} on 2016/6/23.
 */
public class Ads implements Serializable {
    private int result;
    private List<Ad> ads;
    private int next_req;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public int getNext_req() {
        return next_req;
    }

    public void setNext_req(int next_req) {
        this.next_req = next_req;
    }

    @Override
    public String toString() {
        return "Ads{" +
                "result=" + result +
                ", ads=" + ads +
                ", next_req=" + next_req +
                '}';
    }
}
