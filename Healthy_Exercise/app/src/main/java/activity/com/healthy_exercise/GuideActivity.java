package activity.com.healthy_exercise;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.adapter.GuideFragmentAdapter;
import com.adapter.GuideViewPagerAdapter;
import com.fragment.GuideEndItem;
import com.fragment.GuideFirstItem;
import com.fragment.GuideSecondItem;
import com.fragment.GuideThirdItem;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager guidePager;
    private List<View> listViews;
    private List<Fragment> fragmentList;
    private GuideViewPagerAdapter guideViewPagerAdapter;
    private ImageView[] dots;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        addActivity(this);
        initFragment();
        initDots();
    }
    private void initFragment(){
        fragmentList=new ArrayList<Fragment>();
        GuideFirstItem guideFirstItem=new GuideFirstItem();
        GuideSecondItem guideSecondItem=new GuideSecondItem();
        GuideThirdItem guideThirdItem=new GuideThirdItem();
        GuideEndItem guideEndItem=new GuideEndItem();
        fragmentList.add(guideFirstItem);
        fragmentList.add(guideSecondItem);
        fragmentList.add(guideThirdItem);
        fragmentList.add(guideEndItem);
        guidePager= (ViewPager) findViewById(R.id.guide);
        guidePager.setAdapter(new GuideFragmentAdapter(getSupportFragmentManager(),fragmentList));
        guidePager.addOnPageChangeListener(this);
    }
    private void initView(){
        LayoutInflater inflate=LayoutInflater.from(this);
        listViews=new ArrayList<View>();
        listViews.add(inflate.inflate(R.layout.guide_item1,null));
        listViews.add(inflate.inflate(R.layout.guide_item2,null));
        listViews.add(inflate.inflate(R.layout.guide_item3,null));
        listViews.add(inflate.inflate(R.layout.guide_item4,null));
        guideViewPagerAdapter=new GuideViewPagerAdapter(listViews,GuideActivity.this);
        guidePager= (ViewPager) findViewById(R.id.guide);
        guidePager.setAdapter(guideViewPagerAdapter);
        guidePager.addOnPageChangeListener(this);
    }
    private void initDots(){
        RelativeLayout parent= (RelativeLayout) findViewById(R.id.guide_dots);
        dots=new ImageView[fragmentList.size()];
        for(int i=0;i<dots.length;i++){
            dots[i]= (ImageView) parent.getChildAt(i);
            dots[i].setBackgroundColor(Color.parseColor("#cccccc"));
        }
        currentIndex=0;
        dots[currentIndex].setBackgroundColor(Color.parseColor("#ffffff"));
    }
    private void setCurrentDot(int position){
        if(position<0||position>dots.length-1||currentIndex==position){
            return;
        }
        dots[position].setBackgroundColor(Color.parseColor("#ffffff"));
        dots[currentIndex].setBackgroundColor(Color.parseColor("#cccccc"));
        currentIndex=position;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void actionStart(Context context){
        Intent intent=new Intent(context,GuideActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity(this);
    }
}
