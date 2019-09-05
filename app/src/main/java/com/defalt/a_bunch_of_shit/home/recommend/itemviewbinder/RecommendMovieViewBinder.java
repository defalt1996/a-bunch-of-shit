package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.Top250;

import me.drakeet.multitype.ItemViewBinder;

/*
 *  author: Derrick
 *  Time: 2019/9/5 22:39
 */

public class RecommendMovieViewBinder extends ItemViewBinder<Top250, RecommendMovieViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_top250_movie, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Top250 item) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
