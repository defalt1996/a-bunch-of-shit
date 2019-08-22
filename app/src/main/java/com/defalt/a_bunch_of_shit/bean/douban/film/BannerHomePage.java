package com.defalt.a_bunch_of_shit.bean.douban.film;
/*
 *  author: Derrick
 *  Time: 2019/8/22 0:58
 */

public class BannerHomePage {

    private String title;

    private String imgUrl;

    public BannerHomePage(String title, String url){
        this.title = title;
        this.imgUrl = url;
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
