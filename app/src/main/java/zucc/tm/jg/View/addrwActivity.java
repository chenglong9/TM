package zucc.tm.jg.View;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.addAdapter;
import zucc.tm.jg.adapter.memberAdapter;
import zucc.tm.jg.bean.friendbean;

public class addrwActivity extends AppCompatActivity {
    private NoScrollListview list;
    private ArrayList<friendbean> arraylist;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrw);
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

        list= (NoScrollListview) findViewById(R.id.list);
        ArrayList<HashMap> arraylist=new ArrayList<>();
        HashMap fri=new HashMap();
        arraylist.add(fri);
        memberAdapter adapterx=new memberAdapter(this, arraylist);
        list.setAdapter(adapterx);

    }
    public void fan(View v) {
        this.finish();
    }
}
