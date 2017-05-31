package zucc.tm.jg.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.View.MainActivity;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 45773 on 2017-05-20.
 */

public class friendAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<friendbean> arraylist;


    public friendAdapter(Context context, ArrayList<friendbean> arraylist) {
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
            view = mInflater.inflate(R.layout.item_friend, null);
        }
        RelativeLayout card = (RelativeLayout) view.findViewById(R.id.card);
        CardView cardx = (CardView) view.findViewById(R.id.cardx);
        TextView text = (TextView) view.findViewById(R.id.title);
        TextView name = (TextView) view.findViewById(R.id.name);
        final TextView phone = (TextView) view.findViewById(R.id.phone);
        ImageView jia = (ImageView) view.findViewById(R.id.jia);


        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(phone.getText().toString(), my.my.getName()+"邀请您使用TM");
            }
        });
        cardx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + arraylist.get(i).getContact_phone()));
                context.startActivity(intent);
            }
        });


        if (arraylist.get(i).getContact_name().equals("") || i == 0) {
            text.setText(arraylist.get(i).getSortKey());
            card.setVisibility(View.GONE);
            text.setVisibility(View.VISIBLE);
        } else {
            name.setText(arraylist.get(i).getContact_name());
            phone.setText(arraylist.get(i).getContact_phone());
            card.setVisibility(View.VISIBLE);
            text.setVisibility(View.GONE);
        }
        return view;
    }
    private void sendSMS(String number, String message){
        Uri uri = Uri.parse("smsto:" + number.trim());
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        sendIntent.putExtra("sms_body", message);
        context.startActivity(sendIntent);
    }
}
