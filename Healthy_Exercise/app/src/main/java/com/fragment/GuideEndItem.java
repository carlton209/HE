package com.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import activity.com.healthy_exercise.MainActivity;
import activity.com.healthy_exercise.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class GuideEndItem extends Fragment {
    private static final String SHAREPREFERENCES_NAME="first_pref";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.guide_item4,container,false);
        Button button= (Button) view.findViewById(R.id.to_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGuided();
                MainActivity.actionStart(getActivity());
            }
        });
        return view;
    }
    private void setGuided(){
        SharedPreferences preferences=getActivity().getSharedPreferences(SHAREPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("isFirstIn",true);
        editor.commit();
    }
}
