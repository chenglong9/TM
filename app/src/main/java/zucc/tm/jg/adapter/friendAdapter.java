package zucc.tm.jg.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zucc.tm.jg.R;

/**
 * Created by 45773 on 2017-05-20.
 */

public class friendAdapter extends BaseAdapter {
    private Context context;
    public friendAdapter(Context context){
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
            view = mInflater.inflate(R.layout.item_friend, null);
        }
        RelativeLayout card= (RelativeLayout) view.findViewById(R.id.card);
        TextView text= (TextView) view.findViewById(R.id.title);

        if (i%4==0)
        {
            card.setVisibility(View.GONE);
            text.setVisibility(View.VISIBLE);
        }else
        {
            card.setVisibility(View.VISIBLE);
            text.setVisibility(View.GONE);
        }
        return view;
    }
}
