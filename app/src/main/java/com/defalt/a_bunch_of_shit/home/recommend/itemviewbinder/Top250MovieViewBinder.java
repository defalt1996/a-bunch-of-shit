package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.top250.Top250;
import com.defalt.a_bunch_of_shit.network.api.DoubanAPI;
import com.defalt.a_bunch_of_shit.util.GlideImageLoader;
import com.defalt.a_bunch_of_shit.widget.StarMarkView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/*
 *  author: Derrick
 *  Time: 2019/9/5 22:39
 */

public class Top250MovieViewBinder extends ItemViewBinder<Top250, Top250MovieViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_top250_movie, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Top250 item) {

        Glide.with(holder.movieAdsImg.getContext()).load(item.getItem().getImages().getMedium()).into(holder.movieAdsImg);
        holder.movieTitle.setText(item.getItem().getTitle());
        holder.movieYear.setText(item.getItem().getYear());

        holder.marksStar.setParams(item.getItem().getRating().getAverage(), R.drawable.start_full, R.drawable.start_half, R.drawable.star_none);
        if (item.getItem().getRating().getAverage() != 0){
            holder.marksRating.setText(item.getItem().getRating().getAverage()+ "");
        }else {
            holder.marksRating.setText("暂无评分");
            holder.marksRating.setTextColor(Color.parseColor("#5f5f5f"));
        }

        if (item.getDetails() != null){
            holder.hotComment.setText(item.getDetails().getPopular_comments().get(0).getContent()+ "  --" + item.getDetails().getPopular_comments().get(0).getAuthor().getName());
            holder.movieContentImg.setImageLoader(new GlideImageLoader());
            List<String> imgList = new ArrayList<>();
            for (int i = 0; i<3; i++){
                String imgUrl = item.getDetails().getPhotos().get(i).getImage() + "?apikey="+ DoubanAPI.apikey;
                imgList.add(imgUrl);
            }
            holder.movieContentImg.setImages(imgList);
            holder.movieContentImg.setDelayTime(5000);
            holder.movieContentImg.start();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView movieAdsImg;
        private Banner movieContentImg;
        private TextView movieTitle;
        private TextView movieYear;

        private StarMarkView marksStar;
        private TextView marksRating;

        private TextView hotComment;

        ViewHolder(View itemView) {
            super(itemView);
            movieAdsImg = itemView.findViewById(R.id.movie_ads_img);
            movieContentImg = itemView.findViewById(R.id.movie_content_img);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieYear = itemView.findViewById(R.id.movie_year);
            marksStar = itemView.findViewById(R.id.movie_marks_star);
            marksRating = itemView.findViewById(R.id.movie_marks_rating);
            hotComment = itemView.findViewById(R.id.hot_comment);
        }
    }
}
