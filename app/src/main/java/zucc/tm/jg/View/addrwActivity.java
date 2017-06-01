package zucc.tm.jg.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.NoScrollListview;

import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;

import zucc.tm.jg.adapter.memberAdapter;

public class addrwActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String get;
    private EditText name;
    private EditText con;
    private TextView times;
    private TextView timee;
    private TextView timetx;
    private TextView method;
    private int mYear;
    private int mMonth;
    private int mDay;

    public int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrw);
        addprojectActivity.setWindowStatusBarColor(this,R.color.colorAccent);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("添加任务");//设置Toolbar标题
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


    /*    HashMap fri=new HashMap();
        arraylist.add(fri);*/


        toolbar.setOnMenuItemClickListener(onMenuItemClick);


        Calendar c = Calendar.getInstance();//
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        name= (EditText) findViewById(R.id.name_t);
        con= (EditText) findViewById(R.id.con_t);
        times= (TextView) findViewById(R.id.time_t);
        timee= (TextView) findViewById(R.id.time_t2);
        timetx= (TextView) findViewById(R.id.tv_time);
        method= (TextView) findViewById(R.id.method);
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
                    int x = pan();
                    if (x == 1) {
                        Toast.makeText(addrwActivity.this, "任务数据不能为空", Toast.LENGTH_LONG).show();
                        break;
                    } else if (x == 2) {
                        Toast.makeText(addrwActivity.this, "结束时间要大于开始时间", Toast.LENGTH_LONG).show();
                        break;
                    } else {
                        id= (int) getIntent().getSerializableExtra("id");
                        get = "project_id=" + Projectlistb.projectlistb.get(id).getProjectid()+ "&project_name=" + name.getText() + "&describes=" + con.getText();
                        get = get + "&start_time=" + times.getText() + "&end_time=" + timee.getText();
                        int index;
                        for (index = 0;index<methods.length;index++){
                            if (methods[index].equals(method.getText()))
                                break;
                        }
                        get = get + "&tx_time="+timetx.getText().toString().replace(" ","/")+"&tx_method="+index;

                        connect();
                    }

                    break;
            }

            return true;
        }
    };

    public int pan() {
        if (name.getText().equals("") || con.getText().equals("") || times.getText().equals("") || timee.getText().equals("")) {
            return 1;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateStart = dateFormat.parse(times.getText().toString());
            Date dateEnd = dateFormat.parse(timee.getText().toString());
            if (dateEnd.getTime() < dateStart.getTime())
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
                finish();
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(addrwActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/AddDetailsServlet?" + get);
        task.execute();
    }



    public void time1(View v) {
        new DatePickerDialog(addrwActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                times.setText(String.format("%d-%d-%d", i, i1+1 , i2));
            }
        }, mYear, mMonth, mDay).show();
    }

    public void time2(View v) {
        new DatePickerDialog(addrwActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                timee.setText(String.format("%d-%d-%d", i, i1+1 , i2));
            }
        }, mYear, mMonth, mDay).show();
    }
    public void time3(View v) {

        new DatePickerDialog(addrwActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                timetx.setText(String.format("%d-%d-%d ", i, i1 + 1, i2));
                TimePickerDialog timeDialog = new TimePickerDialog(addrwActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            //从这个方法中取得获得的时间
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                timetx.setText(timetx.getText() +String.format("%d:%d",hourOfDay,minute));
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
         }
            });

        builder.show();
    }
}