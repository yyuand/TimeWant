package com.example.yyuand.timewant.adaper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yyuand.timewant.R;
import com.example.yyuand.timewant.bean.TimeWantBean;
import com.example.yyuand.timewant.utils.BitmapHelper;
import com.example.yyuand.timewant.utils.TagViewUtils;
import com.example.yyuand.timewant.view.CircleImageView;
import com.example.yyuand.timewant.view.MyGirdViewNoClick;
import com.example.yyuand.timewant.view.TagView.TagView;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyuand on 2016/8/4.
 */
public class TimeWantedAdaper extends BaseAdapter {

    private Context context;
    private ArrayList<TimeWantBean> list;
    private BitmapUtils utils;

    public TimeWantedAdaper(Context context, ArrayList<TimeWantBean> list) {
        this.context = context;
        this.list = list;
        utils = BitmapHelper.getBitmapUtils(context);
    }



    @Override
    public int getCount() {

        Log.e("list.size()", list.size()+"");
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.time_wanted_item, null);
            holder = new ViewHolder();
            holder.cviHeader = (CircleImageView) convertView.findViewById(R.id.civ_header);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.glPics = (MyGirdViewNoClick) convertView.findViewById(R.id.glPics);
            holder.ivImage = (ImageView) convertView.findViewById(R.id.iv_image);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_desc);
            holder.tvCollege = (TextView) convertView.findViewById(R.id.tv_college);
            holder.tagView = (TagView) convertView.findViewById(R.id.tag_view);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        TimeWantBean bean = list.get(i);

        utils.display(holder.cviHeader,bean.getPortraitPath());
        holder.tvName.setText(bean.getUserName());
        holder.tvDesc.setText(bean.getDescription());
        holder.tvCollege.setText(bean.getCollegeName());

        holder.tagView.removeAllTags();
        TagViewUtils.initTagView(context,holder.tagView,bean.getSignatureList());
        holder.tvPrice.setText((bean.getPrice()== -1f) == true ?"价格面议" : "￥" + bean.getPrice() + "");
        List<TimeWantBean.ImgListBean> imgs = bean.getImgList();

        if(imgs.size() == 1){
            holder.ivImage.setVisibility(View.VISIBLE);
            holder.glPics.setVisibility(View.GONE);
            utils.display(holder.ivImage, imgs.get(i).getPath());
        }else {
            ArrayList<String> picurls = new ArrayList<>();
            for (int j = 0; j < imgs.size(); j++) {

                String url = imgs.get(j).getPath();
                picurls.add(url);
            }
            holder.ivImage.setVisibility(View.GONE);
            holder.glPics.setVisibility(View.VISIBLE);
            PicsGirdAdapter adapter = new PicsGirdAdapter(context, picurls, 3, "1111");//最大显示三个图片
            holder.glPics.setAdapter(adapter);
        }

        return convertView;
    }

    class ViewHolder {

        public CircleImageView cviHeader;
        public TextView tvName;
        public MyGirdViewNoClick glPics;
        public ImageView ivImage;
        public TextView tvPrice;
        public TextView tvDesc;
        public TextView tvCollege;
        public TagView tagView;

    }
}
