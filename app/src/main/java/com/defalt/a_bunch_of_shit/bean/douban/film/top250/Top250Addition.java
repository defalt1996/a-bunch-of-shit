package com.defalt.a_bunch_of_shit.bean.douban.film.top250;
/*
 *  author: Derrick
 *  Time: 2019/9/11 14:22
 */

import java.util.List;

public class Top250Addition {
    private List<String> contentPhoto;
    private String comment;
    private String commentAuthor;

    public List<String> getContentPhoto() {
        return contentPhoto;
    }

    public void setContentPhoto(List<String> contentPhoto) {
        this.contentPhoto = contentPhoto;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }
}
