package com.defalt.a_bunch_of_shit.home.recommend;
/*
 *  author: Derrick
 *  Time: 2019/7/17 11:15
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.defalt.a_bunch_of_shit.R;
import com.defalt.a_bunch_of_shit.base.baseview.ButtonImageText;
import com.defalt.a_bunch_of_shit.home.bean.AppBean;
import com.defalt.a_bunch_of_shit.util.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class RecommendRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<AppBean> appBeanList;

    private static final int TYPE_BANNER_TOP = 0;
    private static final int TYPE_CATEGORY = 1;
    private static final int TYPE_APP = 2;
    private static final int TYPE_BANNER = 3;

    public RecommendRVAdapter(Context context, List<AppBean> appBeanList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.appBeanList = appBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_BANNER_TOP;
        }else if (position == 1){
            return TYPE_CATEGORY;
        }else if (position % 10 == 0){
//            return TYPE_BANNER;
            return TYPE_APP;
        }else {
            return TYPE_APP;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_BANNER_TOP:
                return new TopBannerViewHolder(layoutInflater.inflate(R.layout.item_home_recommend_topbanner, parent, false));
            case TYPE_CATEGORY:
                return new CategoryViewHolder(layoutInflater.inflate(R.layout.item_home_recommend_category, parent, false));
            case TYPE_APP:
                return new SingleAppViewHolder(layoutInflater.inflate(R.layout.item_home_recommend_single_app ,parent , false));
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SingleAppViewHolder){
            SingleAppViewHolder singleHolder = (SingleAppViewHolder) holder;
            singleHolder.mTitle.setText(appBeanList.get(position).getName());
            singleHolder.mContent.setText(appBeanList.get(position).getShortInfo());
            singleHolder.mTime.setText(appBeanList.get(position).getGrade());
        }else {

        }
    }

    @Override
    public int getItemCount() {
        return appBeanList.size();
    }


    public class TopBannerViewHolder extends RecyclerView.ViewHolder {
        private Banner topBanner;
        public TopBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            topBanner = itemView.findViewById(R.id.banner_top);
            topBanner.setImageLoader(new GlideImageLoader());
            List<String> bannerList = new ArrayList<>();
            bannerList.add("https://s2.ax1x.com/2019/07/23/eivIOS.png");
            bannerList.add("https://s2.ax1x.com/2019/07/23/eivTeg.png");
            topBanner.setImages(bannerList);
            topBanner.start();
        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private ButtonImageText button_1;
        private ButtonImageText button_2;
        private ButtonImageText button_3;
        private ButtonImageText button_4;
        private ButtonImageText button_5;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            button_1 = itemView.findViewById(R.id.btn_category_1);
            button_2 = itemView.findViewById(R.id.btn_category_2);
            button_3 = itemView.findViewById(R.id.btn_category_3);
            button_4 = itemView.findViewById(R.id.btn_category_4);
            button_5 = itemView.findViewById(R.id.btn_category_5);
        }
    }

    public class SingleAppViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView mTitle;
        private TextView mContent;
        private TextView mTime;
        private RelativeLayout mLayout;

        public SingleAppViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.news_summary_photo_iv);
            mTitle = (TextView) itemView.findViewById(R.id.news_summary_title_tv);
            mContent = (TextView) itemView.findViewById(R.id.news_summary_digest_tv);
            mTime = (TextView) itemView.findViewById(R.id.news_summary_ptime_tv);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.rl_root);
        }
    }

}
