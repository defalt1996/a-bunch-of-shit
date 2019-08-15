package com.defalt.a_bunch_of_shit.bean.douban.film;
/*
 *  author: Derrick
 *  Time: 2019/8/15 16:58
 */
public class Rating {

    private int max;
    private double average;
    private String stars;
    private int min;
    public void setMax(int max) {
        this.max = max;
    }
    public int getMax() {
        return max;
    }

    public void setAverage(double average) {
        this.average = average;
    }
    public double getAverage() {
        return average;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
    public String getStars() {
        return stars;
    }

    public void setMin(int min) {
        this.min = min;
    }
    public int getMin() {
        return min;
    }

}
