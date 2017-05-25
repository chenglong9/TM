package zucc.tm.jg.View;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.addAdapter;
import zucc.tm.jg.bean.friendbean;

public class UIdesignActivity extends AppCompatActivity {
    private NoScrollListview list;
    private ArrayList<friendbean> arraylist;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uidesign);

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
    }

}
