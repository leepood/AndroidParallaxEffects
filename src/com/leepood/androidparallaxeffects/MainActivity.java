package com.leepood.androidparallaxeffects;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity
{
	private ViewPager viewPager = null;
	
	int[] viewIds = new int[]{
			
			R.id.frameLayout,
			R.id.tv_title,
			R.id.tv_sub_title
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		viewPager.setAdapter(new MAdapter());
		viewPager.setPageTransformer(true, new PageTransformer()
		{
			@Override
			public void transformPage(View view, float position)
			{
				float coefficient =  view.getWidth();
				ViewGroup v = (ViewGroup) view;
				int childs = v.getChildCount();
				for(int i = 0;i < childs; i++){
					View mv = v.getChildAt(i);
					mv.setTranslationX(coefficient * position);
					coefficient *= 1.6;
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private class  MAdapter extends PagerAdapter {

		
		int[] images = new int[]{
				R.drawable.test1,
				R.drawable.test2,
				R.drawable.test3,
				R.drawable.test4,
		};
		
		public MAdapter(){
			
		}
		
		@Override
		public int getCount()
		{
			return 4;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == (View) arg1;
		}

		public Object instantiateItem(android.view.ViewGroup container, int position) {
			View v = getLayoutInflater().inflate(R.layout.pager_item_layout, null);
			container.addView(v,0);
			setupDatas(v,position);
			return v;
		};
		
		
		public void destroyItem(android.view.ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		};
		
		@SuppressLint("NewApi")
		private void setupDatas(View view,int position){
			FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.frameLayout);
			frameLayout.setBackground(getResources().getDrawable(images[position]));
			TextView txtTitle = (TextView)view.findViewById(R.id.tv_title);
			TextView txtSubTitle = (TextView)view.findViewById(R.id.tv_sub_title);
			txtTitle.setText("标题" + position);
			txtSubTitle.setText("这是一个介绍" + position);
		}
		
		
	};
}
