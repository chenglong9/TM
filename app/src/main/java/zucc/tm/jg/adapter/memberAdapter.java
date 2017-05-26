package zucc.tm.jg.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import zucc.tm.jg.R;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 45773 on 2017-05-20.
 */

public class memberAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap> arraylist;


    public memberAdapter(Context context, ArrayList<HashMap> arraylist) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater mInflater = LayoutInflater.from(context);
        if (view == null) {
            view = mInflater.inflate(R.layout.item_addpro, null);
        }
        RelativeLayout title = (RelativeLayout) view.findViewById(R.id.title);
        RelativeLayout add = (RelativeLayout) view.findViewById(R.id.add);
        RelativeLayout card = (RelativeLayout) view.findViewById(R.id.card);

        CardView cardx = (CardView) view.findViewById(R.id.cardx);
        TextView title_t = (TextView) view.findViewById(R.id.title_t);
        TextView add_t = (TextView) view.findViewById(R.id.add_t);

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView phone = (TextView) view.findViewById(R.id.phone);
        ImageButton jia = (ImageButton) view.findViewById(R.id.jia);

        if (i == arraylist.size() - 1) {
            title.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
            add_t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {
            cardx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + arraylist.get(i).get("mphone")));
                    context.startActivity(intent);
                }
            });

            jia.setVisibility(View.VISIBLE);

            title.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            card.setVisibility(View.VISIBLE);

            name.setText((String) arraylist.get(i).get("mname"));
            phone.setText((String) arraylist.get(i).get("mphone"));

        }
        return view;
    }

}
