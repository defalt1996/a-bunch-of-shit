package com.defalt.a_bunch_of_shit.bean.douban.film.us_box;
/*
 *  author: Derrick
 *  Time: 2019/8/29 23:34
 */

import java.util.List;

public class RootUsBox {
    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    private List<Subjects> subjects;
}
