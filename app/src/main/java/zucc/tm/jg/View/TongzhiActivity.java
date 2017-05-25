package zucc.tm.jg.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import zucc.tm.jg.R;
import zucc.tm.jg.adapter.gonggaoAdapter;
import zucc.tm.jg.adapter.tongzhiAdapter;

public class TongzhiActivity extends AppCompatActivity {
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongzhi);
        list = (ListView) findViewById(R.id.list);
        tongzhiAdapter adapter = new tongzhiAdapter(this);
        list.setAdapter(adapter);
    }
    public void fan(View v) {
        this.finish();
    }
}
