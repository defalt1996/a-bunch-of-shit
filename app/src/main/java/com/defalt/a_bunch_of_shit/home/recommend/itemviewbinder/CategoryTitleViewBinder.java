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

import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.BannerHomePageAll;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.CategoryTitleAll;
import com.defalt.a_bunch_of_shit.util.GlideImageLoader;
import com.youth.banner.Banner;

import me.drakeet.multitype.ItemViewBinder;

/*
* Banner in homepage
*
* */
public class CategoryTitleViewBinder extends ItemViewBinder<CategoryTitleAll, CategoryTitleViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_movie_homepage_category, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CategoryTitleAll item) {

        holder.tvSearchMovie.setText("搜电影");
        holder.tvRankMovie.setText("电影排行");
        holder.tvGuessMovie.setText("豆瓣猜");
        holder.tvTop250Movie.setText("Top250");

        holder.ivSearchMovie.setImageResource(R.drawable.title_movie_1);
        holder.ivRankMovie.setImageResource(R.drawable.title_movie_2);
        holder.ivGuessMovie.setImageResource(R.drawable.title_movie_3);
        holder.ivTop250Movie.setImageResource(R.drawable.title_movie_4);


    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSearchMovie;
        TextView tvRankMovie;
        TextView tvGuessMovie;
        TextView tvTop250Movie;

        ImageView ivSearchMovie;
        ImageView ivRankMovie;
        ImageView ivGuessMovie;
        ImageView ivTop250Movie;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvSearchMovie = itemView.findViewById(R.id.tv_search_movie);
            this.tvRankMovie = itemView.findViewById(R.id.tv_rank_movie);
            this.tvGuessMovie = itemView.findViewById(R.id.tv_guess_movie);
            this.tvTop250Movie = itemView.findViewById(R.id.tv_top250_movie);

            this.ivSearchMovie = itemView.findViewById(R.id.iv_search_movie);
            this.ivRankMovie = itemView.findViewById(R.id.iv_rank_movie);
            this.ivGuessMovie = itemView.findViewById(R.id.iv_guess_movie);
            this.ivTop250Movie = itemView.findViewById(R.id.iv_top250_movie);
        }
    }
}
