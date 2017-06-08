package zucc.tm.jg.View;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.PhoneFormatCheckUtils;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.gg;
import zucc.tm.jg.adapter.gonggaoAdapter;
import zucc.tm.jg.bean.ggbean;

import static zucc.tm.jg.Util.my.my;

public class GonggaoActivity extends AppCompatActivity {
    private ListView list;
    Toolbar toolbar;
    int id;
    private gonggaoAdapter adapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonggao);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srlayout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getgong();
            }
        });

        id= (int) getIntent().getSerializableExtra("id");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("公告");//设置Toolbar标题
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

        list = (ListView) findViewById(R.id.list);
        adapter = new gonggaoAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           final int position, long id) {

                alertdialog.showSimpleDialog(GonggaoActivity.this, "", "是否删除该公告?", "取消", "删除", null, new DialogInterface.OnClickListener (){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        del(position);
                    }
                }, true);
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if(my.getPhone().equals(Projectlistb.projectlistb.get(id).getPhone()))
            getMenuInflater().inflate(R.menu.menuadd, menu);
        return true;
    }
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuitem) {
            switch (menuitem.getItemId()) {
                case R.id.add:
                    View views = LayoutInflater.from(GonggaoActivity.this).inflate(R.layout.editalert, null);
                    final EditText editText= (EditText) views.findViewById(R.id.edit);
                    alertdialog.showeditDialog(GonggaoActivity.this, "输入公告内容", "好的", views, "取消", "确认", null, new DialogInterface.OnClickListener (){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                if (!editText.getText().toString().equals("")) {
                                    String get="con="+editText.getText().toString()+"&id="+Projectlistb.projectlistb.get(id).getProjectid();
                                    addgong(get);
                                }
                            }
                        }, true);
                        break;
            }
            return true;
        }
    };
    public  void addgong(String get) {

        HttpTask task = new HttpTask(new HttpCallBack() {

            @Override
            public void success(List result) {
                Toast.makeText(GonggaoActivity.this, "发布成功", Toast.LENGTH_LONG).show();
                getgong();
            }

            @Override
            public void error(Exception e) {

            }
        }, "http://" + curUrl.url + "/addmsg2Servlet?"+get);
        task.execute();
    }
    public  void getgong() {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {
                    mRefreshLayout.setRefreshing(false);
                    gg.GGlist.clear();

                    JSONArray msg = new JSONArray((String) result.get(0));
                    for (int i = 0; i < msg.length(); i++) {
                        JSONObject msgx = msg.getJSONObject(i);
                        ggbean g=new ggbean(msgx.getString("con"),msgx.getString("time"),msgx.getString("id"));
                        gg.GGlist.add(g);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(Exception e) {

            }
        }, "http://" + curUrl.url + "/getmsg2Servlet?type=msg2&id="+Projectlistb.projectlistb.get(id).getProjectid());
        task.execute();
    }
    public  void del(int x) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                getgong();
                Toast.makeText(GonggaoActivity.this, "删除成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(GonggaoActivity.this, "删除失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/delmsg2Servlet?id=" + gg.GGlist.get(x).getId());
        task.execute();
    }
}
