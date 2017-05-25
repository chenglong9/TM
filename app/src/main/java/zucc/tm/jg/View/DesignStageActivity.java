package zucc.tm.jg.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.addAdapter;
import zucc.tm.jg.adapter.jobAdapter;
import zucc.tm.jg.adapter.rwAdapter;
import zucc.tm.jg.bean.friendbean;

public class DesignStageActivity extends AppCompatActivity {
    private NoScrollListview list;
    private ListView rw_list;
    private ArrayList<friendbean> arraylist;
    private ArrayList<String> lvs =new ArrayList<String>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_stage);

        lvs.add("通知");
        lvs.add("设置");
        rw_list = (ListView) findViewById(R.id.list_rw);
        rwAdapter adapter = new rwAdapter(this,lvs);
        rw_list.setAdapter(adapter);

    }
    public void fan(View v) {
        this.finish();
    }
}
