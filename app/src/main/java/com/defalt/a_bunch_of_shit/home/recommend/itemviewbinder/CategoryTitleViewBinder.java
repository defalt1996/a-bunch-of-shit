package com.defalt.a_bunch_of_shit.home.recommend.itemviewbinder;
/*
 *  author: Derrick
 *  Time: 2019/8/19 22:20
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.category1.setText(item.getmCategoryTitleList().get(0).getTitle());
        holder.category2.setText(item.getmCategoryTitleList().get(1).getTitle());
        holder.category3.setText(item.getmCategoryTitleList().get(2).getTitle());
        holder.category4.setText(item.getmCategoryTitleList().get(3).getTitle());
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView category1;
        TextView category2;
        TextView category3;
        TextView category4;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.category1 = itemView.findViewById(R.id.category_1);
            this.category2 = itemView.findViewById(R.id.category_2);
            this.category3 = itemView.findViewById(R.id.category_3);
            this.category4 = itemView.findViewById(R.id.category_4);
        }
    }
}
