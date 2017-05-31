package zucc.tm.jg.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import zucc.tm.jg.adapter.memberAdapter;
import zucc.tm.jg.adapter.rwAdapter;
import zucc.tm.jg.bean.RWBean;
import zucc.tm.jg.bean.friendbean;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uidesign);
        i = (int) getIntent().getSerializableExtra("id");
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
          /*   ArrayList<HashMap> arraylist=new ArrayList<>();
   HashMap fri=new HashMap();
        arraylist.add(fri);*/

        adapterx = new memberAdapter(this, RWlisttb.RWlist.get(i).getFriends());
        list.setAdapter(adapterx);

        starttime_tv = (TextView) findViewById(R.id.starttime_tv);
        endtime_tv = (TextView) findViewById(R.id.endtime_tv);
        jianjie = (TextView) findViewById(R.id.jianjie);
        tv_time = (TextView) findViewById(R.id.tv_time);
        method = (TextView) findViewById(R.id.method);

        starttime_tv.setText(RWlisttb.RWlist.get(i).getStart_time());
        endtime_tv.setText(RWlisttb.RWlist.get(i).getEnd_time());
        jianjie.setText(RWlisttb.RWlist.get(i).getDescribes());
        tv_time.setText(RWlisttb.RWlist.get(i).getTx_time());
        method.setText(RWlisttb.RWlist.get(i).getTx_method());
    }

    public void add(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.d("n", "" + (int) getIntent().getSerializableExtra("n"));
        ArrayList<String> names = new ArrayList<String>();
        for (int o = 0; o < Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().size(); o++) {
            Log.d("name", "" + (String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(o).get("mname"));
            names.add((String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(o).get("mname"));
        }
        final String[] methods = names.toArray(new String[names.size()]);
        builder.setItems(methods, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int l = 0; l < RWlisttb.RWlist.get(i).getFriends().size(); l++) {
                    if (RWlisttb.RWlist.get(i).getFriends().get(l).get("mname").equals(methods[which])) {
                        Toast.makeText(UIdesignActivity.this, "成员已存在", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                get = "id=" + (int) getIntent().getSerializableExtra("n") + "&phone=" +
                        (String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(which).get("mphone");
                get = get + "&workid=" + RWlisttb.RWlist.get(i).getStage_id();
                connect((String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(which).get("name"), (String) Projectlistb.projectlistb.get((int) getIntent().getSerializableExtra("n")).getFriends().get(which).get("phone"));

            }
        });
        builder.show();
    }

    public void connect(final String name, final String phone) {

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
                    RWlisttb.RWlist.get(i).getFriends().clear();
                    JSONArray rwlist = new JSONArray((String) result.get(0));
                    for (int i = 0; i < rwlist.length(); i++) {
                        JSONObject rw = rwlist.getJSONObject(i);
                        JSONArray friends = rw.getJSONArray("friend");
                        //ArrayList<HashMap> friendlist = new ArrayList<>();
                        for (int j = 0; j < friends.length(); j++) {
                            JSONObject friend = friends.getJSONObject(j);
                            HashMap friendb = new HashMap();
                            friendb.put("mphone", friend.getString("mphone"));
                            friendb.put("mname", friend.getString("mname"));
                            //friendlist.add(friendb);
                            RWlisttb.RWlist.get(i).getFriends().add(friendb);
                        }
                        //  RWlisttb.RWlist.get(i).setFriends(friendlist);
                    }
                    //   adapterx=new memberAdapter(UIdesignActivity.this,RWlisttb.RWlist.get(i).getFriends());
                    adapterx.notifyDataSetChanged();
                    //   list.setAdapter(adapterx);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(UIdesignActivity.this, "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "GetMember?id=" + RWlisttb.RWlist.get(i).getStage_id());
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
