package zucc.tm.jg.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import zucc.tm.jg.R;

/**
 * Created by 45773 on 2017-05-20.
 */

public class jobAdapter extends BaseAdapter {
    private Context context;
    public jobAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 10;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        if(view == null)
        {
            view = mInflater.inflate(R.layout.item_job, null);
        }
        CheckBox cbx = (CheckBox) view.findViewById(R.id.check);
        TextView title= (TextView) view.findViewById(R.id.title);
        TextView time= (TextView) view.findViewById(R.id.time);
        TextView dv= (TextView) view.findViewById(R.id.dv);
        if (i%4==0)
        {
            if (i!=0)
            dv.setVisibility(View.VISIBLE);

            title.setText("项目一");
            title.setTextSize(16);
            title.setTextColor(Color.parseColor("#757575"));
            cbx.setVisibility(View.INVISIBLE);
            time.setVisibility(View.GONE);
        }

        return view;
    }
}
