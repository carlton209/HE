package activity.com.healthy_exercise;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class WelcomeActivity extends BaseActivity {
    boolean isFirstIn=false;
    private static final int GO_HOME=1000;
    private static final int GO_GUIDE=1001;
    private static final long SPLASH_DELAY_MILLIS = 0;
    private Handler handler=new MyHandle();
    public static final String SHAREPREFERENCES_NAME="first_pref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        addActivity(this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }
    public void init(){
        SharedPreferences preferences=getSharedPreferences(SHAREPREFERENCES_NAME,MODE_PRIVATE);
        isFirstIn=preferences.getBoolean("isFirstIn",true);
        if(isFirstIn){
            handler.sendEmptyMessageDelayed(GO_GUIDE,SPLASH_DELAY_MILLIS);
        }else{
            handler.sendEmptyMessageDelayed(GO_HOME,SPLASH_DELAY_MILLIS);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity(this);
    }

    public void goHome(){
        MainActivity.actionStart(WelcomeActivity.this);
    }
    public void goGuide(){
        GuideActivity.actionStart(WelcomeActivity.this);
    }
    private class MyHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    }
}
