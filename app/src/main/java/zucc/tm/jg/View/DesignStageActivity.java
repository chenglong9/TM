package zucc.tm.jg.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.addAdapter;
import zucc.tm.jg.adapter.jobAdapter;
import zucc.tm.jg.adapter.memberAdapter;
import zucc.tm.jg.adapter.rwAdapter;
import zucc.tm.jg.bean.friendbean;

public class DesignStageActivity extends AppCompatActivity {
    private NoScrollListview list;
    private ListView rw_list;
    private ArrayList<friendbean> arraylist;
    private ArrayList<String> lvs =new ArrayList<String>();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_stage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设计阶段");//设置Toolbar标题
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


        lvs.add("通知");
        lvs.add("设置");
        rw_list = (ListView) findViewById(R.id.list_rw);
        rwAdapter adapter = new rwAdapter(this,lvs);
        rw_list.setAdapter(adapter);
        list= (NoScrollListview) findViewById(R.id.list);
        //int id= (int) getIntent().getSerializableExtra("id");
        memberAdapter adapterx=new memberAdapter(this, Projectlistb.projectlistb.get(0).getFriends());
        list.setAdapter(adapterx);

    }
    public void add(View view){
        Intent intent = new Intent(this,addrwActivity.class);
        this.startActivity(intent);
    }
}
