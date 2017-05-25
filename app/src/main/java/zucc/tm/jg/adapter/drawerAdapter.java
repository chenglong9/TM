package zucc.tm.jg.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 王泽豪 on 2017/5/23.
 */

public class drawerAdapter extends BaseAdapter {
    private Context context;
    private List<String> lists;

    public drawerAdapter(List<String> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
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
        if (view == null) {
            view = mInflater.inflate(R.layout.item_drawer, null);
        }
        ImageView imageView= (ImageView) view.findViewById(R.id.icon_drawer);
        TextView textView = (TextView) view.findViewById(R.id.tv_drawer);

         if(i==0){
            imageView.setImageResource(R.drawable.setting);
            textView.setText(lists.get(i));
        }
        else  if(i==1){
        imageView.setImageResource(R.drawable.setting);
            textView.setText(lists.get(i));
        }
        else if(i==2){
            imageView.setImageResource(R.drawable.update);
            textView.setText(lists.get(i));
        }
        else if(i==3){
            imageView.setImageResource(R.drawable.about);
            textView.setText(lists.get(i));
        }
        else if(i==4){
            imageView.setImageResource(R.drawable.exit);
            textView.setText(lists.get(i));
            textView.setTextColor(Color.parseColor("#E61E28"));
        }

        return view;
    }
}
