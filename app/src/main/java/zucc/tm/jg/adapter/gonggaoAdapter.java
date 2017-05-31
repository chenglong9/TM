package zucc.tm.jg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.gg;

/**
 * Created by 王泽豪 on 2017/5/24.
 */

public class gonggaoAdapter extends BaseAdapter {
    private Context context;

    public gonggaoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return gg.GGlist.size();
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
            view = mInflater.inflate(R.layout.item_gonggao, null);
        }
        TextView neirong= (TextView) view.findViewById(R.id.neirou);
        TextView time= (TextView) view.findViewById(R.id.time);

        neirong.setText(gg.GGlist.get(i).getCon());
        time.setText(gg.GGlist.get(i).getTime());

        return view;
    }
}
