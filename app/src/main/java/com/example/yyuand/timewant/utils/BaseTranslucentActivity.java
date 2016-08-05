package com.example.yyuand.timewant.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class BaseTranslucentActivity extends AppCompatActivity {

	private static int getSystemComponentDimen(Context context, String dimenName) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			String heightStr = clazz.getField(dimenName).get(object).toString();
			int height = Integer.parseInt(heightStr);

			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	private static boolean hasNavigationBarShow(WindowManager wm) {
		Display display = wm.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();

		display.getRealMetrics(outMetrics);
		int heightPixels = outMetrics.heightPixels;
		int widthPixels = outMetrics.widthPixels;

		outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		int heightPixels2 = outMetrics.heightPixels;
		int widthPixels2 = outMetrics.widthPixels;
		int w = widthPixels - widthPixels2;
		int h = heightPixels - heightPixels2;
		return w > 0 || h > 0;
	}
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT
				&& Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP){
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	@SuppressLint("NewApi")
	public void setOrChangeTranslucentColor(Toolbar toolbar, View bottomNavigationBar, int translucentPrimaryColor){

		if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT
				&& Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP){
			if(toolbar!=null){

				LayoutParams params = toolbar.getLayoutParams();
				int statusBarHeight = getStatusBarHeight(this);
				params.height += statusBarHeight ;
				toolbar.setLayoutParams(params );

				toolbar.setPadding(
						toolbar.getPaddingLeft(),
						toolbar.getPaddingTop() + getStatusBarHeight(this),
						toolbar.getPaddingRight(),
						toolbar.getPaddingBottom());

				toolbar.setBackgroundColor(translucentPrimaryColor);

			}
			//加颜色，防止华为手机出现虚拟键
			/*if(bottomNavigationBar!=null){

				if(hasNavigationBarShow(getWindowManager())){
					LayoutParams p = bottomNavigationBar.getLayoutParams();
					p.height += getNavigationBarHeight(this);
					bottomNavigationBar.setLayoutParams(p);

					bottomNavigationBar.setBackgroundColor(translucentPrimaryColor);
				}
			}*/
		}else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
			//getWindow().setNavigationBarColor(translucentPrimaryColor);设置为透明
			getWindow().setStatusBarColor(translucentPrimaryColor);
		}else{

		}
	}
	
	private int getNavigationBarHeight(Context context) {
		return getSystemComponentDimen(this, "navigation_bar_height");
	}

	private int getStatusBarHeight(Context context) {

		return getSystemComponentDimen(this, "status_bar_height");
	}
	
}
