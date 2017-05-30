package zucc.tm.jg.View;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.adapter.memberAdapter;
import zucc.tm.jg.bean.friendbean;

public class UIdesignActivity extends AppCompatActivity {
    private NoScrollListview list;
    private ArrayList<friendbean> arraylist;
    Toolbar toolbar;

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uidesign);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("通知");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list= (NoScrollListview) findViewById(R.id.list);
        ArrayList<HashMap> arraylist=new ArrayList<>();
        HashMap fri=new HashMap();
        arraylist.add(fri);

        memberAdapter adapterx=new memberAdapter(this,arraylist);
        list.setAdapter(adapterx);
        initView();
    }
    public void initView(){
        Intent intent = getIntent();
        if(intent.getBooleanExtra("flag",false)==true){
            alertdialog.showSimpleDialog(UIdesignActivity.this, "任务提醒", "任务。。。。", "", "确认", null, null, true);
        }
        //测试用的，点击完成度的卡片，进行定时，时间固定
        cardView = (CardView) findViewById(R.id.wc);
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(UIdesignActivity.this, "点击成功", Toast.LENGTH_LONG).show();
                setAlarm();
            }
        });
    }

    public void setAlarm(){

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, UIdesignActivity.class);
        intent.putExtra("flag",true);//显示任务提醒
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long triggerAtTime = SystemClock.elapsedRealtime() + 5 * 1000;//设置时间

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);

    }
}
