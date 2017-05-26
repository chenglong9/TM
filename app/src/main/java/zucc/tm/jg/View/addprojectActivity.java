package zucc.tm.jg.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.addAdapter;
import zucc.tm.jg.bean.friendbean;

import static zucc.tm.jg.Util.my.my;

public class addprojectActivity extends AppCompatActivity {

    private NoScrollListview list;
    private ArrayList<friendbean> arraylist;
    private Toolbar toolbar;
    private String get;
    private EditText name;
    private EditText con;
    private TextView times;
    private TextView timee;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject);
        setWindowStatusBarColor(this,R.color.colorAccent);

        list = (NoScrollListview) findViewById(R.id.list);
        name = (EditText) findViewById(R.id.name_t);
        con = (EditText) findViewById(R.id.con_t);
        times = (TextView) findViewById(R.id.time_t);
        timee = (TextView) findViewById(R.id.time_t2);

        arraylistinit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("添加项目");//设置Toolbar标题
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

        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        addAdapter adapter = new addAdapter(this, arraylist);
        list.setAdapter(adapter);

        Calendar c = Calendar.getInstance();//
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

    }

    private void arraylistinit() {
        arraylist = new ArrayList<friendbean>();
        getfriend get = new getfriend(this);
        friendbean friend = new friendbean();
        friend.setContact_name(my.getName());
        friend.setContact_phone(my.getPhone());

        arraylist.add(friend);
        arraylist.add(friend);
        arraylist.add(friend);
    }

    public void fan(View v) {
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusuc, menu);
        return true;
    }


    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuitem) {
            switch (menuitem.getItemId()) {
                case R.id.suc:
                    int x=pan();
                    if (x==1) {
                        Toast.makeText(addprojectActivity.this, "项目数据不能为空", Toast.LENGTH_LONG).show();
                        break;
                    }else
                    if (x==2) {
                        Toast.makeText(addprojectActivity.this, "结束时间要大于开始时间", Toast.LENGTH_LONG).show();
                        break;
                    }else {
                        get = "people_in_charge="+my.getPhone()+"&project_name="+name.getText()+"&project_describe="+con.getText();
                        get=get+"&start_time="+times.getText()+"&end_time="+timee.getText();
                        connect();
                    }
                    break;
            }
            return true;
        }
    };
    public int pan() {
        if (name.getText().equals("")||con.getText().equals("")||times.getText().equals("")||timee.getText().equals(""))
        {
            return 1;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateStart = dateFormat.parse(times.getText().toString());
            Date dateEnd = dateFormat.parse(timee.getText().toString());
            if (dateEnd.getTime()<dateStart.getTime())
                return 2;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public void connect() {

        HttpTask task = new HttpTask(new HttpCallBack() {
            @Override
            public void success(List result) {
                //网络请求成功后将会调用
               /* try {
                    JSONObject Data = new JSONObject((String) result.get(0));


                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                finish();
                Toast.makeText(addprojectActivity.this, "添加成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(addprojectActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/AddProjectServlet?" + get);
        task.execute();
    }

    public void time1(View v) {
        new DatePickerDialog(addprojectActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                times.setText(String.format("%d-%d-%d", i, i1 + 1, i2));
            }
        }, mYear, mMonth, mDay).show();
    }

    public void time2(View v) {
        new DatePickerDialog(addprojectActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                timee.setText(String.format("%d-%d-%d", i, i1 + 1, i2));
            }
        }, mYear, mMonth, mDay).show();
    }
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*  type=getIntent().getStringExtra("type");
            try {
                type = URLEncoder.encode(type, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/