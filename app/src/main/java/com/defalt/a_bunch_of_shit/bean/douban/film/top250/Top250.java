package com.defalt.a_bunch_of_shit.bean.douban.film.top250;
/*
 *  author: Derrick
 *  Time: 2019/9/5 23:28
 */

import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.top250details.Top250Details;

public class Top250 {

    private Subjects item;
    private Top250Details details;
    private boolean Added;


    public Subjects getItem() {
        return item;
    }

    public void setItem(Subjects item) {
        this.item = item;
    }

    public void setDetails(Top250Details details) {
        this.details = details;
    }

    public Top250Details getDetails() {
        return details;
    }

    public boolean isAdded() {
        return Added;
    }

    public void setAdded(boolean added) {
        Added = added;
    }
}
