package io.github.duke0323.netnews.bean;

import java.util.ArrayList;

/**
 * Created by ${Duke} on 2016/6/28.
 */
public class Comments {
    ArrayList<Comment> mComments;
    boolean isTitle = false;
    String titleName;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public ArrayList<Comment> getComments() {
        return mComments;
    }

    public void setComments(ArrayList<Comment> comments) {
        mComments = comments;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public Comments() {
        this.mComments = new ArrayList<>();
    }

    public void add(Comment comment) {
        mComments.add(comment);
    }
}
