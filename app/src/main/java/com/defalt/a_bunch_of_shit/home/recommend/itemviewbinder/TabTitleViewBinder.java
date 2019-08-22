package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;
/*
 *  author: Derrick
 *  Time: 2019/8/19 22:20
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.Subjects;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.EmptyValue;

import me.drakeet.multitype.ItemViewBinder;

/*
* 首页中正在上映和即将上映是同一块view, 所以这里用TheaterMovieViewBinder统一管理
*
* */
public class TabTitleViewBinder extends ItemViewBinder<EmptyValue, TabTitleViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_home_tab_title, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull EmptyValue item) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
