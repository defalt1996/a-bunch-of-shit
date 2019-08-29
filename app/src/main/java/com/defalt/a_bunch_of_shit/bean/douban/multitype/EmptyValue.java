package com.defalt.a_bunch_of_shit.bean.douban.multitype;
/*
 *  author: Derrick
 *  Time: 2019/8/22 11:19
 */

public class EmptyValue {

    public static final int TAB_TITLE = 1;

    public static final int USBOX_TITLE = 2;

    public static final int LIST_TITLE = 3;

    public static final int RECOMMEND_TITLE = 4;

    public int type;
    public int amount;
    public String title;

    public EmptyValue(int type,String title, int amount) {
        this.type = type;
        this.amount = amount;
        this.title = title;
    }

}