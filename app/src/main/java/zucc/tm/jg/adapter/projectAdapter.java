package zucc.tm.jg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import zucc.tm.jg.R;

/**
 * Created by 45773 on 2017-05-20.
 */

public class projectAdapter extends BaseAdapter {
    private Context context;
    public projectAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 4;
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
            view = mInflater.inflate(R.layout.item_peoject, null);
        }

        return view;
    }
}
