package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.EmptyValue;

import me.drakeet.multitype.ItemViewBinder;

/*
 *  author: Derrick
 *  Time: 2019/8/29 22:28
 */

public class TitleViewBinder extends ItemViewBinder<EmptyValue, TitleViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.empty_title, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull EmptyValue item) {
        holder.title.setText(item.title);

        if (item.title.equals("Top250")){
            holder.tvSeeAll.setVisibility(View.INVISIBLE);
        }else {
            holder.tvSeeAll.setText("全部" + item.amount);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView tvSeeAll;
        ImageView ivSeeAll;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            tvSeeAll = itemView.findViewById(R.id.tv_movie_cinema_seeall);
            ivSeeAll = itemView.findViewById(R.id.iv_ic_seeall);
        }
    }
}
