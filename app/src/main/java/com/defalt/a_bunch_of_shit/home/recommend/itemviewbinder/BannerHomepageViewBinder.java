package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;
/*
 *  author: Derrick
 *  Time: 2019/8/19 22:20
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.bean.douban.film.BannerHomePage;
import com.defalt.a_bunch_of_shit.bean.douban.multitype.BannerHomePageAll;
import com.defalt.a_bunch_of_shit.util.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/*
* Banner in homepage
*
* */
public class BannerHomepageViewBinder extends ItemViewBinder<BannerHomePageAll, BannerHomepageViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_home_recommend_topbanner, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BannerHomePageAll item) {
        holder.banner.setImageLoader(new GlideImageLoader());
        List<String> imgList = new ArrayList<>();

        for (BannerHomePage banner : item.getBannerHomePageList()){
            imgList.add(banner.getImgUrl());
        }
        holder.banner.setImages(imgList);
        holder.banner.start();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private Banner banner;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.banner = itemView.findViewById(R.id.banner_top);
        }
    }
}
