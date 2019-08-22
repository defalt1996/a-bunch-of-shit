package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.recommend_subjects.RankSubjects;

import me.drakeet.multitype.ItemViewBinder;

/*
 *  author: Derrick
 *  Time: 2019/8/22 23:42
 */

public class RankMovieViewBinder extends ItemViewBinder<RankSubjects, RankMovieViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_movie_home_rank, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull RankSubjects item) {
        holder.title.setText(item.getSubject().getTitle());
        Glide.with(holder.img.getContext()).load(item.getSubject().getImages().getSmall()).into(holder.img);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.news_summary_photo_iv);
            this.title = itemView.findViewById(R.id.news_summary_title_tv);
        }
    }
}
