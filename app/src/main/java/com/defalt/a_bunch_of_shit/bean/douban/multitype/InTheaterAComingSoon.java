package com.defalt.a_bunch_of_shit.bean.douban.multitype;
/*
 *  author: Derrick
 *  Time: 2019/8/27 23:54
 */

import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;

import java.util.List;

public class InTheaterAComingSoon {
    private List<Subjects> data;

    public void setData(List<Subjects> data) {
        this.data = data;
    }

    public List<Subjects> getData() {
        return data;
    }
}
