package io.github.duke0323.netnews.splash.bean;

import java.io.Serializable;

/**
 * Created by ${Duke} on 2016/6/23.
 */
public class Action implements Serializable{
    String link_url;

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    @Override
    public String toString() {
        return "Action{" +
                "link_url='" + link_url + '\'' +
                '}';
    }
}
