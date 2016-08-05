package com.example.yyuand.timewant.activity;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yyuand.timewant.R;
import com.example.yyuand.timewant.adaper.SquarePageAdapter;
import com.example.yyuand.timewant.adaper.TimeWantedAdaper;
import com.example.yyuand.timewant.application.Path;
import com.example.yyuand.timewant.bean.ImageBean;
import com.example.yyuand.timewant.bean.TimeWantBean;
import com.example.yyuand.timewant.utils.BaseTranslucentActivity;
import com.example.yyuand.timewant.utils.TextUtilsForFont;
import com.example.yyuand.timewant.utils.ToastUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseTranslucentActivity {

    private Toolbar toolBar;
    private PullToRefreshListView pfl;
    private ListView listView;
    private ArrayList<TimeWantBean> list;
    private TimeWantedAdaper adapter;
    private ViewPager viewpager;
    private int startIndex = 0;
    private SearchView.SearchAutoComplete et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTitle();

        initView();

        initData();
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu__time_wanted,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        //设置一出来就直接呈现搜索框---SearchView
        //searchView.setIconified(false);

        ImageView  icon = (ImageView) searchView.findViewById(R.id.search_go_btn);
        icon.setImageResource(R.mipmap.application_search_icon);
        icon.setVisibility(View.VISIBLE);

        et = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        et.setHintTextColor(Color.WHITE);
        et.setHint("搜索时间求租");

        searchView.setSubmitButtonEnabled(true);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ToastUtils.showToast(MainActivity.this, "搜索时间求租", Toast.LENGTH_SHORT);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_time_want){
            ToastUtils.showToast(MainActivity.this, "选择城市", Toast.LENGTH_SHORT);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        getData();
    }

    private void getData() {

        RequestParams params = new RequestParams();
        JSONObject object = new JSONObject();
        try {
            object.put("startIndex", 0);
            object.put("pageCount", 1);
            object.put("cityID", "63");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        params.addBodyParameter("data",object.toString());

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.POST, Path.get_time_rent_list_page_info, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                Log.e("responseInfo",responseInfo.result);

                try {
                    JSONObject result = new JSONObject(responseInfo.result);
                    if("SUCCESS".equals(result.getString("status"))) {
                        JSONObject data = result.getJSONObject("data");

                        JSONArray merchandiseList = data.getJSONArray("merchandiseList");
                        JSONArray carouselList = data.getJSONArray("carouselList");

                        initMerchandiseList(merchandiseList);
                        initViewPager(carouselList);

                    }
                } catch (JSONException e) {

                    Log.e("JSONException",e.toString());
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {

                Log.e("HttpException",s);
            }
        });
    }

    private void initTitle() {

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        ((TextView) findViewById(R.id.tv_title)).setTextSize(TypedValue.COMPLEX_UNIT_PX,
                TextUtilsForFont.getFontSize(MainActivity.this, 35));

        setOrChangeTranslucentColor(toolBar, null, getResources().getColor(R.color.color_main_dark));
    }

    private void initView() {

        pfl = (PullToRefreshListView) findViewById(R.id.pfl);
        pfl.setMode(PullToRefreshBase.Mode.BOTH);
        listView = pfl.getRefreshableView();
    }

    private void initViewPager(JSONArray carouselList) {

        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.time_want,null);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);

        ArrayList<ImageBean>imgs = new ArrayList<ImageBean>();
        for(int i = 0;i<carouselList.length();i++){

            try {
                JSONObject item = carouselList.getJSONObject(i);
                String url = item.getString("url");
                String path = item.getString("path");
                String imgPathID = item.getString("imgPathID");

                ImageBean bean = new ImageBean(url,path,imgPathID);

                imgs.add(bean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        viewpager.setAdapter(new SquarePageAdapter(MainActivity.this,imgs));

        listView.addHeaderView(view);
    }

    private void initMerchandiseList(JSONArray merchandiseList) {
        list = new ArrayList<TimeWantBean>();
        Gson gson = new Gson();
        for(int i =  0 ;i <merchandiseList.length();i++ ){
            try {
                list.add(gson.fromJson(merchandiseList.getJSONObject(i).toString(),TimeWantBean.class));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.e("adapter", list.size()+"");

        adapter = new TimeWantedAdaper(MainActivity.this,list);

        listView.setAdapter(adapter);

        pfl.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                startIndex = 0;
                reFresh(startIndex,"63","0",1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                startIndex +=1;
                reFresh(startIndex,"63","0",2);
            }
        });


    }

    private void reFresh(int startIndex,String cityID,String collegeID,final int type){

        RequestParams params = new RequestParams();
        JSONObject object = new JSONObject();

        try {
            object.put("startIndex", startIndex);
            object.put("pageCount", 1);
            object.put("cityID", cityID);
            object.put("collegeID", collegeID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        params.addBodyParameter("data", object.toString());

        HttpUtils utils = new HttpUtils();

        utils.send(HttpRequest.HttpMethod.POST, Path.get_time_rent_list, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject result = new JSONObject(responseInfo.result);
                    if("SUCCESS".equals(result.getString("status"))){
                        JSONArray data = result.getJSONArray("data");
                        Gson gson = new Gson();
                        switch (type){
                            //下拉
                            case 1:
                                list.clear();

                                for(int i = 0;i < data.length();i++){

                                    list.add(gson.fromJson(data.getJSONObject(i).toString(),TimeWantBean.class));
                                }
                                adapter.notifyDataSetChanged();
                                pfl.onRefreshComplete();

                                break;
                            case 2:
                                for(int i = 0;i < data.length();i++){

                                    list.add(gson.fromJson(data.getJSONObject(i).toString(),TimeWantBean.class));
                                }
                                adapter.notifyDataSetChanged();
                                pfl.onRefreshComplete();

                                break;
                        }

                    }else {

                        pfl.onRefreshComplete();
                        Toast.makeText(MainActivity.this,"没有更多了",Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }


        });

    }
}
