package zucc.tm.jg.View;

import android.app.Activity;

import android.content.DialogInterface;
import android.os.Bundle;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.alertdialog;

public class newActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        String name= (String) getIntent().getSerializableExtra("flag");
        alertdialog.showSimpleDialog(newActivity.this, "温馨提醒", "可以进行"+name+"任务了！", null, "知道了", null, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newActivity.this.finish();
            }
        }, true);

    }

}
