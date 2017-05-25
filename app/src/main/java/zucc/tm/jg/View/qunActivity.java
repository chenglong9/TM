package zucc.tm.jg.View;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qun);
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

