package io.github.duke0323.netnews.bean;

import java.util.List;

/**
 * Created by ${Duke} on 2016/6/27.
 */
public class NewsContent {
    //主体
    String body;
    //图片
    List<Image> img;
    //文章标题
    String title;
    //时间
    String ptime;
    //来源
    String source;
    //回复
    int replyCount;

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "NewsContent{" +
                "body='" + body + '\'' +
                ", img=" + img +
                ", title='" + title + '\'' +
                ", ptime='" + ptime + '\'' +
                ", source='" + source + '\'' +
                ", replyCount=" + replyCount +
                '}';
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Image> getImg() {
        return img;
    }

    public void setImg(List<Image> img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
