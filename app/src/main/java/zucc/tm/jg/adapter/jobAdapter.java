package zucc.tm.jg.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.Joblisttb;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.View.UIdesignActivity;

/**
 * Created by 45773 on 2017-05-20.
 */

public class jobAdapter extends BaseAdapter {
    private Context context;

    public jobAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Joblisttb.jobl.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (view == null) {
            view = mInflater.inflate(R.layout.item_job, null);
        }
        CheckBox cbx = (CheckBox) view.findViewById(R.id.check);
        final TextView title = (TextView) view.findViewById(R.id.title);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView dv = (TextView) view.findViewById(R.id.dv);





        if (i == 0 || Joblisttb.jobl.get(i).getProject_id().equals("")) {
            if (i != 0)
                dv.setVisibility(View.VISIBLE);
            title.setText(Joblisttb.jobl.get(i).getProject_name());
            title.setTextSize(14);
            title.setTextColor(Color.parseColor("#03A9F4"));
            cbx.setVisibility(View.INVISIBLE);
            time.setVisibility(View.GONE);
        } else {
            title.setText(Joblisttb.jobl.get(i).getProject_name());
            dv.setVisibility(View.GONE);
            time.setText(Joblisttb.jobl.get(i).getTx_time());
            title.setTextSize(14);
            title.setTextColor(Color.parseColor("#212121"));
            cbx.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);

            if (Joblisttb.jobl.get(i).getPerson_in_charge().equals("1")) {
                cbx.setChecked(true);
                title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                title.setTextColor(Color.parseColor("#757575"));
            } else {
                cbx.setChecked(false);
                title.setTextColor(Color.parseColor("#212121"));
                title.getPaint().setFlags(0);
            }
        }
        cbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    update(i, "f", "1");
                    title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    title.setTextColor(Color.parseColor("#757575"));
                } else {
                    update(i, "f", "0");
                    title.setTextColor(Color.parseColor("#212121"));
                    title.getPaint().setFlags(0);
                }
            }
        });
        return view;
    }

    public void update(int i, String what, String value) {

        HttpTask task = new HttpTask(new HttpCallBack() {
            @Override
            public void success(List result) {

            }

            @Override
            public void error(Exception e) {
                Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/ModifyRW?id=" + Joblisttb.jobl.get(i).getStage_id() + "&what=" + what + "&value=" + value);
        task.execute();
    }

}
