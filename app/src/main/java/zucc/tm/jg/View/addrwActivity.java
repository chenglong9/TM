package zucc.tm.jg.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.addAdapter;
import zucc.tm.jg.bean.friendbean;

public class addrwActivity extends AppCompatActivity {
    private NoScrollListview list;
    private ArrayList<friendbean> arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrw);


    }
    public void fan(View v) {
        this.finish();
    }
}
