package com.example.yyuand.timewant.adaper;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yyuand.timewant.R;
import com.example.yyuand.timewant.activity.ShiJiuActivity;
import com.example.yyuand.timewant.bean.ImageBean;
import com.example.yyuand.timewant.utils.BitmapHelper;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by yyuand on 2016/8/4.
 */
public class SquarePageAdapter extends PagerAdapter {
    //PagerAdapter只继承两个方法，另外初始化与销毁要自己添加
    private Context context;
    private ArrayList<ImageBean> list;
    private BitmapUtils utils;

    public SquarePageAdapter(Context context, ArrayList<ImageBean> list) {
        this.context = context;
        this.list = list;
        utils = BitmapHelper.getBitmapUtils(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        View view = View.inflate(context, R.layout.viewpager_carousel,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_carousel);
        utils.display(imageView,list.get(position).getPath());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShiJiuActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
