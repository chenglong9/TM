package zucc.tm.jg.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.RWlisttb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 45773 on 2017-05-20.
 */

public class memberAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap> arraylist;
    private Handler handler;
    int z;
    int m;
    public memberAdapter(Context context, ArrayList<HashMap> arraylist ,Handler handler,int z,int m) {
        this.context = context;
        this.arraylist = arraylist;
        this.handler=handler;
        this.z=z;
        this.m=m;
    }
    public memberAdapter(Context context, ArrayList<HashMap> arraylist ) {
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

        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            del((String) arraylist.get(i).get("mphone"));
            }
        });

            cardx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + arraylist.get(i).get("mphone")));
                    context.startActivity(intent);
                }
            });

            jia.setVisibility(View.VISIBLE);
        if (!my.my.getPhone().equals(Projectlistb.projectlistb.get(m).getPhone()))
            jia.setVisibility(View.GONE);

            title.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            card.setVisibility(View.VISIBLE);

            name.setText((String) arraylist.get(i).get("mname"));
            phone.setText((String) arraylist.get(i).get("mphone"));

        return view;
    }

    public  void del(String phone) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                handler.sendMessage(new Message());

            }

            @Override
            public void error(Exception e) {
                Toast.makeText(context, "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/DeleteWorkMember?id=" + RWlisttb.RWlist.get(z).getStage_id()+"&phone="+phone);
        task.execute();
    }
}
