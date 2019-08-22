package com.defalt.a_bunch_of_shit.bean.douban.film;
/*
 *  author: Derrick
 *  Time: 2019/8/22 0:59
 */

public class CategoryTitle {
    private String title;
    private String imgUrl;

    public CategoryTitle(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
