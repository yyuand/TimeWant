package com.example.yyuand.timewant.utils;

import android.content.Context;
import android.graphics.Color;


import com.example.yyuand.timewant.R;
import com.example.yyuand.timewant.bean.TimeWantBean;
import com.example.yyuand.timewant.view.TagView.Tag;
import com.example.yyuand.timewant.view.TagView.TagView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/1.
 */
public class TagViewUtils {

    public static void initTagView(Context context, TagView tagView, String[] tags) {

        for (int i = 0; i < tags.length; i++) {

            Tag tag = new Tag(tags[i]);

            tag.radius = 80f;
            tag.layoutColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
            tag.tagTextColor = context.getResources().getColor(R.color.white);
            tag.tagTextSize = 12;
            tagView.addTag(tag);
        }
    }

    public static void initTagView(Context context, TagView tagView, List<TimeWantBean.SignatureListBean> tags) {

        for (int i = 0; i < tags.size(); i++) {

            Tag tag = new Tag(tags.get(i).getDescription());

            tag.radius = 80f;
            tag.layoutColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
            tag.tagTextColor = context.getResources().getColor(R.color.white);
            tag.tagTextSize = 12;
            tagView.addTag(tag);
        }
    }
}
