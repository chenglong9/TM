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

        list = (NoScrollListview) findViewById(R.id.list);
        getfriend get=new getfriend(this);
        arraylist=get.get2();
        friendbean friend = new friendbean();
        friend.setContact_name("");
        arraylist.add(0,friend);
        arraylist.add(2,friend);
        arraylist.add(friend);
        addAdapter adapter2 = new addAdapter(this,arraylist);
        list.setAdapter(adapter2);
    }
    public void fan(View v) {
        this.finish();
    }
}
