package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;
/*
 *  author: Derrick
 *  Time: 2019/8/19 22:20
 */

import android.graphics.Color;
import android.net.Uri;
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
import com.defalt.a_bunch_of_shit.widget.StarMarkView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import me.drakeet.multitype.ItemViewBinder;

/*
* 首页中正在上映和即将上映是同一块view, 所以这里用TheaterMovieViewBinder统一管理
*
* */
public class TheaterMovieViewBinder extends ItemViewBinder<Subjects, TheaterMovieViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_moive_home_theater, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Subjects item) {
        holder.title.setText(item.getTitle());
//        Glide.with(holder.img.getContext()).load(item.getImages().getSmall()).into(holder.img);
        //换用Fresco
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(item.getImages().getSmall()))
                .setAutoPlayAnimations(true)
                .build();
        holder.img.setController(controller);
            holder.marksStar.setParams(item.getRating().getAverage(), R.drawable.start_full, R.drawable.start_half, R.drawable.star_none);
        if (item.getRating().getAverage() != 0){
            holder.marksRating.setText(item.getRating().getAverage()+ "");
        }else {
            holder.marksRating.setText("暂无评分");
            holder.marksRating.setTextColor(Color.parseColor("#5f5f5f"));
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView img;
        private TextView title;
        private StarMarkView marksStar;
        private TextView marksRating;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.theater_image);
            this.title = itemView.findViewById(R.id.theater_title);
            this.marksStar = itemView.findViewById(R.id.movie_marks_star);
            this.marksRating = itemView.findViewById(R.id.movie_marks_rating);
        }
    }
}
