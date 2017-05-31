package zucc.tm.jg.View;

import android.graphics.Color;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.TZlisttb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.gonggaoAdapter;
import zucc.tm.jg.adapter.projectAdapter;
import zucc.tm.jg.adapter.tongzhiAdapter;
import zucc.tm.jg.bean.TZbean;
import zucc.tm.jg.bean.projectbean;

public class TongzhiActivity extends AppCompatActivity {
    private ListView list;
    Toolbar toolbar;
    tongzhiAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            connect();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongzhi);
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
        connect();
        list = (ListView) findViewById(R.id.list);
        adapter = new tongzhiAdapter(this,handler);
        list.setAdapter(adapter);
    }


    public  void connect() {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {
                    TZlisttb.TZ.clear();
                    JSONArray projectlist = new JSONArray((String) result.get(0));
                    for (int i = 0; i < projectlist.length(); i++) {
                        JSONObject project = projectlist.getJSONObject(i);
                        JSONArray tz = project.getJSONArray("tz");

                        for (int j = 0; j < tz.length(); j++) {
                            JSONObject t = tz.getJSONObject(j);
                            TZbean tZbean = new TZbean();
                            tZbean.setProject((String) t.get("name"));
                            tZbean.setProject_id(""+ t.get("project_id"));
                            tZbean.setNotice_id( Integer.parseInt(""+t.get("id") ) );
                            tZbean.setWords((String) t.get("con"));
                            tZbean.setNt_time((String) t.get("time"));
                            TZlisttb.TZ.add(tZbean);
                        }

                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(TongzhiActivity.this, "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/getmsgServlet?id=" +my.my.getPhone());
        task.execute();
    }

}
