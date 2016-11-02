package activity.com.healthy_exercise;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class BaseActivity extends AppCompatActivity {
    public static List<Activity> activityList=new ArrayList<Activity>();
    public void addActivity(Activity activity){
        activityList.add(activity);
    }
    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public void finishAll(){
        for (Activity activity:activityList
             ) {
            activity.finish();
        }
    }
}
