package com.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import activity.com.healthy_exercise.MainActivity;
import activity.com.healthy_exercise.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class GuideViewPagerAdapter extends PagerAdapter{
    private List<View> views;
    private Activity activity;
    private static final String SHAREPREFERENCES_NAME="first_pref";
    public GuideViewPagerAdapter(List<View> views,Activity activity){
        this.views=views;
        this.activity=activity;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(views.get(position));
    }

    @Override
    public void finishUpdate(ViewGroup container) {
    }

    @Override
    public int getCount() {
        if(views!=null){
            return views.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(views.get(position),0);
        if(position==views.size()-1){
            Button button= (Button) views.get(position).findViewById(R.id.to_main);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setGuided();
                    MainActivity.actionStart(activity);
                }
            });
        }
        return views.get(position);
    }
    private void setGuided(){
        SharedPreferences preferences=activity.getSharedPreferences(SHAREPREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("isFirstIn",false);
        editor.commit();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

}
