package zucc.tm.jg.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.adapter.messageAdapter;
import zucc.tm.jg.bean.MessageBean;

public class qunActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private messageAdapter msgAdapter;
    private List<MessageBean> msgList = new ArrayList<MessageBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qun);

        msgAdapter = new messageAdapter(qunActivity.this, R.layout.item_message, msgList);
        inputText = (EditText) findViewById(R.id.text_msg);
        msgListView = (ListView) findViewById(R.id.list_kefu);
        msgListView.setAdapter(msgAdapter);

        inputText.clearFocus();


    }



    public void fan(View v) {
        this.finish();
    }

    public void send(View v) {
        String content = inputText.getText().toString();
        if (!"".equals(content)) {
            MessageBean msg = new MessageBean(content, MessageBean.SEND, new Date());
            msgList.add(msg);
            msgAdapter.notifyDataSetChanged();
            msgListView.setSelection(msgList.size());
            inputText.setText("");
        }

    }








}
/*  type=getIntent().getStringExtra("type");
            try {
                type = URLEncoder.encode(type, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/
/*  public void init() {

        HttpTask task = new HttpTask(new HttpCallBack() {
            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {
                    JSONObject Data = new JSONObject((String) result.get(0));

                    request = Data.getString("workerId");
                    if (request.equals("")) {
                        Toast.makeText(KeFu.this, "没有客服在线", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    connect();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(KeFu.this, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/convenience/loadlineworker.action?type=" + type + "&userid=" + curUser.cur_user.getUserId());
        task.execute();
    }*/