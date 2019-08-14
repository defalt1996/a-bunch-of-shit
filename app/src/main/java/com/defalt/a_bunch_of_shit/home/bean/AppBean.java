package com.defalt.a_bunch_of_shit.home.bean;
/*
 *  author: Derrick
 *  Time: 2019/7/17 23:11
 */

import android.os.Parcel;
import android.os.Parcelable;

public class AppBean implements Parcelable {

    private String name;
    private String category;
    private String size;
    private String shortInfo;
    private String grade;
    private String imgUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.category);
        dest.writeString(this.size);
        dest.writeString(this.shortInfo);
        dest.writeString(this.grade);
        dest.writeString(this.imgUrl);
    }

    public AppBean() {
    }

    protected AppBean(Parcel in) {
        this.name = in.readString();
        this.category = in.readString();
        this.size = in.readString();
        this.shortInfo = in.readString();
        this.grade = in.readString();
        this.imgUrl = in.readString();
    }

    public static final Parcelable.Creator<AppBean> CREATOR = new Parcelable.Creator<AppBean>() {
        @Override
        public AppBean createFromParcel(Parcel source) {
            return new AppBean(source);
        }

        @Override
        public AppBean[] newArray(int size) {
            return new AppBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static Creator<AppBean> getCREATOR() {
        return CREATOR;
    }
}
