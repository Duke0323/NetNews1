package io.github.duke0323.netnews.util;

/**
 * Created by ${Duke} on 2016/6/23.
 */
public class Contance {
    public static final String FOLDERNAME = "duke0323";
    public static final String SPLASH_URL = "http://g1.163.com/madr?app=7A16FBB6&platform=android&category=STARTUP&location=1&timestamp=1462779408364&uid=OaBKRDb%2B9FBz%2FXnwAuMBWF38KIbARZdnRLDJ6Kkt9ZMAI3VEJ0RIR9SBSPvaUWjrFtfw1N%2BgxquT0B2pjMN5zsxz13RwOIZQqXxgjCY8cfS8XlZuu2bJj%2FoHqOuDmccGyNEtV%2FX%2FnBofofdcXyudJDmBnAUeMBtnIzHPha2wl%2FQnUPI4%2FNuAdXkYqX028puyLDhnigFtrX1oiC2F7UUuWhDLo0%2BE0gUyeyslVNqLqJCLQ0VeayQa%2BgbsGetk8JHQ";

    public static final String HOT_URL = "http://c.m.163.com/nc/article/headline/T1348647909107/%START-%PAGESIZE.html?from=toutiao&size=20&prog=LTitleA&fn=1&passport=&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D&lat=&lon=&version=10.0&net=wifi&ts=1466904304&sign=o4a2fL1i84wRs4OJ7l%2FoE2Cph13eUOBYhBFVzkDCKqp48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=ufA8ZtUu0%2BnqYtIc%2FJ%2BsJMniKsPhqwUPmAMQf86XLBg%3D";
    public static String NEWSCONTENT_URL = "http://c.3g.163.com/nc/article/%SSSA/full.html";
    public static String COMMENT_HOT_URL = "http://comment.api.163.com/api/json/post/list/new/hot/news_shehui7_bbs/%SSS/0/10/10/2/2";
    public static String COMMENT_NORMAL_URL = "http://comment.api.163.com/api/json/post/list/new/normal/news_shehui7_bbs/%SSS/0/10/10/2/2";

    public static String getNewscontentUrl(String docId) {
        String url = NEWSCONTENT_URL.replace("%SSSA", docId);
        return url;
    }

    public static String getComment_Url(String docId) {
        String url = COMMENT_HOT_URL.replace("%SSS", docId);
        return url;
    }

    public static String getnormalComment_Url(String docId) {
        String url = COMMENT_NORMAL_URL.replace("%SSS", docId);
        return url;
    }

    public static String getHotUrl(int index, int size) {
        String now = String.valueOf(index * size);
        String url = new String(HOT_URL);
        //使用替换数据
        url = url.replace("%START", now);
        url = url.replace("%PAGESIZE", String.valueOf(size));
        return url;
    }
}
