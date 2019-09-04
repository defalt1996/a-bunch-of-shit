package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;
/*
 *  author: Derrick
 *  Time: 2019/8/19 22:20
 */

import android.annotation.SuppressLint;
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
import com.defalt.a_bunch_of_shit.home.recommend.RecommendContract;

import me.drakeet.multitype.ItemViewBinder;

/*
* 首页中正在上映和即将上映是同一块view, 所以这里用TheaterMovieViewBinder统一管理
*
* */
public class TabTitleViewBinder extends ItemViewBinder<EmptyValue, TabTitleViewBinder.ViewHolder> {

    OntabChangedListener ontabChangedListener;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_home_tab_title, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull EmptyValue item) {
        holder.tvInTheater.setText("影院热映");
        holder.tvComingSoon.setText("即将上映");
        holder.tvSeeAll.setText("全部" + item.amount);
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvInTheater;
        TextView tvComingSoon;
        TextView tvSeeAll;
        ImageView ivSeeAll;
        View indicatorInTheater;
        View indicatorComingSoon;
        boolean isInTheater;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInTheater = itemView.findViewById(R.id.tv_in_theater);
            tvComingSoon = itemView.findViewById(R.id.tv_coming_soon);
            tvSeeAll = itemView.findViewById(R.id.tv_movie_cinema_seeall);
            ivSeeAll = itemView.findViewById(R.id.iv_ic_seeall);

            indicatorInTheater = itemView.findViewById(R.id.indicator_in_theater);
            indicatorComingSoon = itemView.findViewById(R.id.indicator_coming_soon);

            isInTheater = true;
            final View.OnClickListener  listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view == tvInTheater){

                        indicatorInTheater.setVisibility(View.VISIBLE);
                        indicatorComingSoon.setVisibility(View.INVISIBLE);
                        isInTheater = true;
                        ontabChangedListener.onChanged(isInTheater);

                    }else if (view == tvComingSoon){
                        indicatorInTheater.setVisibility(View.INVISIBLE);
                        indicatorComingSoon.setVisibility(View.VISIBLE);
                        isInTheater = false;
                        ontabChangedListener.onChanged(isInTheater);
                    }else if (view == tvSeeAll || view == ivSeeAll){
                        if (isInTheater){
                            ontabChangedListener.onClickSeeAll(RecommendContract.SEE_ALL_IN_THEATER);
                        }else {
                            ontabChangedListener.onClickSeeAll(RecommendContract.SEE_ALL_COMING_SOON);
                        }
                    }
                }
            };
            tvInTheater.setOnClickListener(listener);
            tvComingSoon.setOnClickListener(listener);
        }
    }
    public interface OntabChangedListener{
        void onChanged(boolean isIntheater);
        void onClickSeeAll(int type);
    }

    public void setOntabChangedListener(OntabChangedListener ontabChangedListener) {
        this.ontabChangedListener = ontabChangedListener;
    }


}
