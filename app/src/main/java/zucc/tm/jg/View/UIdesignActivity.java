package zucc.tm.jg.View;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

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
import android.util.Log;
import android.view.View;

import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.NoScrollListview;

import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.RWlisttb;
import zucc.tm.jg.Util.curUrl;

import zucc.tm.jg.Util.alertdialog;

import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.memberAdapter;


public class UIdesignActivity extends AppCompatActivity {
    private NoScrollListview list;
    private TextView starttime_tv;
    private TextView endtime_tv;
    private TextView jianjie;
    private TextView tv_time;
    private TextView method;
    Toolbar toolbar;

    private String get;
    int i;
    memberAdapter adapterx;


    CardView cardView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            connect();
            Toast.makeText(UIdesignActivity.this, "删除成功", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uidesign);
        i = (int) getIntent().getSerializableExtra("id");

        starttime_tv = (TextView) findViewById(R.id.starttime_tv);
        endtime_tv = (TextView) findViewById(R.id.endtime_tv);
        jianjie = (TextView) findViewById(R.id.jianjie);
        tv_time = (TextView) findViewById(R.id.tv_time);
        method = (TextView) findViewById(R.id.method);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(RWlisttb.RWlist.get(i).getProject_name());//设置Toolbar标题
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

        list = (NoScrollListview) findViewById(R.id.list);
        starttime_tv.setText(RWlisttb.RWlist.get(i).getStart_time());
        endtime_tv.setText(RWlisttb.RWlist.get(i).getEnd_time());
        jianjie.setText(RWlisttb.RWlist.get(i).getDescribes());
        tv_time.setText(RWlisttb.RWlist.get(i).getTx_time());
        method.setText(RWlisttb.RWlist.get(i).getTx_method());



        adapterx = new memberAdapter(this, RWlisttb.RWlist.get(i).getFriends(),handler,i,(int) getIntent().getSerializableExtra("n"));
        list.setAdapter(adapterx);

        jianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertdialog.showSimpleDialog(UIdesignActivity.this, "", jianjie.getText().toString(), null, null, null, null, true);

            }
        });
    }

    public void add(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.d("n", "" + (int) getIntent().getSerializableExtra("n"));
        final ArrayList<String> names = new ArrayList<String>();
        for (int o = 0; o < Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().size(); o++) {
         //   Log.d("name", "" + (String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(o).get("mname"));
            names.add((String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(o).get("mname"));
        }
        names.add(my.my.getName());
        final String[] methods = names.toArray(new String[names.size()]);

        builder.setItems(methods, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==names.size()-1)
                    for (int l = 0; l < RWlisttb.RWlist.get(i).getFriends().size(); l++) {
                        if (RWlisttb.RWlist.get(i).getFriends().get(l).get("mphone").equals(my.my.getPhone())) {
                            Toast.makeText(UIdesignActivity.this, "成员已存在", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                 else
                for (int l = 0; l < RWlisttb.RWlist.get(i).getFriends().size(); l++) {
                    if (RWlisttb.RWlist.get(i).getFriends().get(l).get("mphone").equals(Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(which).get("mphone"))) {
                        Toast.makeText(UIdesignActivity.this, "成员已存在", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                get = "id=" + (int) getIntent().getSerializableExtra("n");

                if(which==names.size()-1)
                    get= get + "&phone=" +my.my.getPhone();
                    else
                get = get  + "&phone=" + (String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(which).get("mphone");

                get = get + "&workid=" + RWlisttb.RWlist.get(i).getStage_id();
                connectx();

            }
        });
        builder.show();

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

    public void setAlarm() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, UIdesignActivity.class);
        intent.putExtra("flag", true);//显示任务提醒
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerAtTime = SystemClock.elapsedRealtime() + 5 * 1000;//设置时间

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
    }

    public void connectx() {

        HttpTask task = new HttpTask(new HttpCallBack() {
            @Override
            public void success(List result) {
                connect();
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(UIdesignActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/addaddServlet?" + get);
        task.execute();
    }

    public void connect() {

        HttpTask task = new HttpTask(new HttpCallBack() {

            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {
                    //  mRefreshLayout.setRefreshing(false);
                    RWlisttb.RWlist.get(i).setFriends(new ArrayList<HashMap>());

                    JSONArray rwlist = new JSONArray((String) result.get(0));
                    for (int x = 0; x < rwlist.length(); x++) {
                        JSONObject rw = rwlist.getJSONObject(x);
                        JSONArray friends = rw.getJSONArray("friend");

                        for (int j = 0; j < friends.length(); j++) {
                            JSONObject friend = friends.getJSONObject(j);
                            HashMap friendb = new HashMap();
                            friendb.put("mphone", friend.getString("mphone"));
                            friendb.put("mname", friend.getString("mname"));

                            RWlisttb.RWlist.get(i).getFriends().add(friendb);
                        }

                    }

                    adapterx = new memberAdapter(UIdesignActivity.this, RWlisttb.RWlist.get(i).getFriends(),handler,i,(int) getIntent().getSerializableExtra("n"));
                    list.setAdapter(adapterx);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(UIdesignActivity.this, "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/GetMember?id=" + RWlisttb.RWlist.get(i).getStage_id());
        task.execute();
    }
    public void settime(View v){
         int mYear;
         int mMonth;
         int mDay;
        Calendar c = Calendar.getInstance();//
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        if (my.my.getPhone().equals(Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getPhone()))
        new DatePickerDialog(UIdesignActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                tv_time.setText(String.format("%d-%d-%d ", i, i1 + 1, i2));
                TimePickerDialog timeDialog = new TimePickerDialog(UIdesignActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            //从这个方法中取得获得的时间
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                tv_time.setText(tv_time.getText() +String.format("%d:%d",hourOfDay,minute));

                                update("tx_time",tv_time.getText().toString().replace(" ","/"));
                            }
                        }, 0, 0, true);
                timeDialog.show();
            }
        }, mYear, mMonth, mDay).show();
    }

    final String[] methods={"闹铃","无"};
    public void select(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(methods,new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                method.setText(methods[which]);
                int index;
                for (index = 0;index<methods.length;index++){
                    if (methods[index].equals(method.getText()))
                        break;
                }
                update("method",index+"");
            }
        });
        if (my.my.getPhone().equals(Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getPhone()))
        builder.show();
    }
    public void update(String what, String value) {

        HttpTask task = new HttpTask(new HttpCallBack() {
            @Override
            public void success(List result) {
                connect();
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(UIdesignActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/ModifyRW?id="+ RWlisttb.RWlist.get(i).getStage_id() +"&what="+what+"&value=" +value);
        task.execute();



    }
}
