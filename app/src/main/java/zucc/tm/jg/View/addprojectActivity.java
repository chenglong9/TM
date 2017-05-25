package zucc.tm.jg.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.addAdapter;
import zucc.tm.jg.bean.friendbean;

public class addprojectActivity extends AppCompatActivity {

    private ListView list;
    private ArrayList<friendbean> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject);

        list = (ListView) findViewById(R.id.list);
        getfriend get=new getfriend(this);
        arraylist=get.get2();
        friendbean friend = new friendbean();
        friend.setContact_name("");
        arraylist.add(0,friend);
        arraylist.add(2,friend);
        arraylist.add(friend);
        addAdapter adapter = new addAdapter(this,arraylist);
        list.setAdapter(adapter);

    }
    public void fan(View v){
        finish();
    }
}
