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
import java.util.IllegalFormatCodePointException;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.View.addprojectActivity;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 45773 on 2017-05-20.
 */

public class addAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<friendbean> arraylist;


    public addAdapter(Context context, ArrayList<friendbean> arraylist) {
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
        ImageButton jia= (ImageButton) view.findViewById(R.id.jia);

        cardx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + arraylist.get(i).getContact_phone()));
                context.startActivity(intent);
            }
        });

        jia.setVisibility(View.VISIBLE);
        if (i==1)
            jia.setVisibility(View.GONE);
        if (i==0) {
            title.setVisibility(View.VISIBLE);
            add.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            title_t.setText("项目经理");
        } else  if (i==arraylist.size()-1){
            title.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
            card.setVisibility(View.GONE);
            add_t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(context).inflate(R.layout.editalert, null);
                    alertdialog.showeditDialog(context, "请输入成员的手机号", "好的", view, "取消", "确认", null, null, true);
                }
            });


        }else  if (i==2){
            title.setVisibility(View.VISIBLE);
            add.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            title_t.setText("成员");
        }else{
            title.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            card.setVisibility(View.VISIBLE);
            name.setText(arraylist.get(i).getContact_name());
            phone.setText(arraylist.get(i).getContact_phone());
        }

        return view;
    }

}
