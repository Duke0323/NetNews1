package io.github.duke0323.netnews.bean;

import java.util.ArrayList;

/**
 * Created by ${Duke} on 2016/6/26.
 */
public class NewDetails {
    //广告
    ArrayList<BannerData> ads;
    //图片源
    String imgsrc;
    //标题
    String title;
    //新闻来源
    String source;
    //详细ID
    String docid;
    //专题ID
    String specialID;
    //回复数
    int replyCount;

    @Override
    public String toString() {
        return "NewDetails{" +
                "ads=" + ads +
                ", imgsrc='" + imgsrc + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", docid='" + docid + '\'' +
                ", specialID='" + specialID + '\'' +
                ", replyCount=" + replyCount +
                '}';
    }

    public ArrayList<BannerData> getAds() {
        return ads;
    }

    public void setAds(ArrayList<BannerData> ads) {
        this.ads = ads;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
}
