package zucc.tm.jg.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import zucc.tm.jg.R;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 王泽豪 on 2017/5/24.
 */

public class rwAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<String> arraylist;

    public rwAdapter(Context context, ArrayList<String> arraylist) {
        this.context = context;
        this.arraylist = arraylist;
    }

    @Override
    public int getCount() {
        return arraylist.size();
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
            view = mInflater.inflate(R.layout.item_rw, null);
        }
        CheckBox cbx = (CheckBox) view.findViewById(R.id.check);
        TextView title= (TextView) view.findViewById(R.id.title);
        TextView line= (TextView) view.findViewById(R.id.line);
        TextView dv= (TextView) view.findViewById(R.id.dv);
        if (i==0)
            line.setVisibility(View.VISIBLE);
        title.setText(arraylist.get(i));
        title.setTextSize(16);
        title.setTextColor(Color.parseColor("#757575"));
        dv.setVisibility(View.VISIBLE);
        return view;
    }
}
