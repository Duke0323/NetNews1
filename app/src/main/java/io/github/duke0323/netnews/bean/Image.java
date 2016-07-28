package io.github.duke0323.netnews.bean;

/**
 * Created by ${Duke} on 2016/6/27.
 */
public class Image {
    String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Image{" +
                "src='" + src + '\'' +
                '}';
    }
}
