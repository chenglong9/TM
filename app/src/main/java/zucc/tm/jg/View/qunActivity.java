package zucc.tm.jg.View;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.Msglist;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.messageAdapter;
import zucc.tm.jg.bean.msgbean;

public class qunActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private messageAdapter msgAdapter;
    private Toolbar toolbar;
    int id;
    public Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what==1)
            {
                msgAdapter.notifyDataSetChanged();
                msgListView.setSelection( Msglist.map.get(proid).size());
            }
        }

    };
    private ArrayList<HashMap> fri;
    private String proid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qun);
        MsgIntentService.sethand(handler);
        id= (int) getIntent().getSerializableExtra("id");
        proid=Projectlistb.projectlistb.get(id).getProjectid();

        fri=Projectlistb.projectlistb.get(id).getFriends();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("项目群聊");//设置Toolbar标题
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

        msgAdapter = new messageAdapter(qunActivity.this, R.layout.item_message,proid);
        inputText = (EditText) findViewById(R.id.text_msg);
        msgListView = (ListView) findViewById(R.id.list_kefu);
        msgListView.setAdapter(msgAdapter);

        getMsg(id);

        inputText.clearFocus();
    }

    public void send(View v) {
        String content = inputText.getText().toString();
        if (!"".equals(content)) {

            JSONObject msg=new JSONObject();
            try {
                msg.put("id",Projectlistb.projectlistb.get(id).getProjectid());
                msg.put("name",my.my.getName());
                msg.put("msg",content);
                msg.put("type","msg3");

                JSONArray fris=new JSONArray();
                for (int i=0;i<fri.size();i++)
                {
                    JSONObject msgfri=new JSONObject();
                    msgfri.put("name",fri.get(i).get("mname"));
                    msgfri.put("phone",fri.get(i).get("mphone"));
                    fris.put(msgfri);
                }
                JSONObject msgfri=new JSONObject();
                msgfri.put("name",Projectlistb.projectlistb.get(id).getPhonename());
                msgfri.put("phone",Projectlistb.projectlistb.get(id).getPhone());
                fris.put(msgfri);
                msg.put("phone",fris);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            MsgIntentService.sendMessage(msg.toString());

            inputText.setText("");
        }

    }
    public  void getMsg(int x) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {
                    Msglist.map.get(proid).clear();
                    JSONArray Msgl = new JSONArray((String) result.get(0));
                    for (int i = 0; i < Msgl.length(); i++) {
                        JSONObject msg = Msgl.getJSONObject(i);
                        msgbean msgb=new msgbean(msg.getString("id"),msg.getString("name"),msg.getString("msg"),msg.getString("time"));
                        Msglist.map.get(proid).add(msgb);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                msgAdapter.notifyDataSetChanged();
                msgListView.setSelection( Msglist.map.get(proid).size());
            }

            @Override
            public void error(Exception e) {
            }
        }, "http://" + curUrl.url + "/getmsg3Servlet?id=" +Projectlistb.projectlistb.get(x).getProjectid());
        task.execute();
    }

}

