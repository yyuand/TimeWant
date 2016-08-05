package com.example.yyuand.timewant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.yyuand.timewant.R;

public class ShiJiuActivity extends AppCompatActivity {
    private WebView ShiJiuDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_jiu);

        initView();
    }

    private void initView() {
        ShiJiuDetail = (WebView) findViewById(R.id.ShiJiuDetail);
        ShiJiuDetail.loadUrl(getIntent().getStringExtra("url"));
    }
}
