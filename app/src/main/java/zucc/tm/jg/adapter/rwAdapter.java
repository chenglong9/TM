package zucc.tm.jg.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.RWlisttb;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.View.UIdesignActivity;
import zucc.tm.jg.bean.RWBean;

/**
 * Created by 王泽豪 on 2017/5/24.
 */

public class rwAdapter extends BaseAdapter {
    private Context context;
    private int n;
    private Handler handler;

    public rwAdapter(Context context, int i, Handler handler) {
        this.context = context;
        this.n = i;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return RWlisttb.RWlist.size();
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
            view = mInflater.inflate(R.layout.item_rw, null);
        }
        CheckBox cbx = (CheckBox) view.findViewById(R.id.check);
        final TextView title = (TextView) view.findViewById(R.id.title);
        TextView time = (TextView) view.findViewById(R.id.time);


        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UIdesignActivity.class);
                //  intent.putExtra("id",(int) context.getIntent().getSerializableExtra("id"));
                intent.putExtra("id", i);
                intent.putExtra("n", n);
                context.startActivity(intent);
            }
        });
        if (my.my.getPhone().equals(Projectlistb.projectlistb.get(n).getPhone())) {
            title.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    alertdialog.showSimpleDialog(context, "", "是否删除该任务?", "取消", "删除", null, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            del(i);
                        }
                    }, true);
                    return true;
                }
            });
        }
        time.setText(RWlisttb.RWlist.get(i).getTx_time());
        title.setText(RWlisttb.RWlist.get(i).getProject_name());
        title.setTextSize(16);
        title.setTextColor(Color.parseColor("#212121"));

        if (RWlisttb.RWlist.get(i).getPerson_in_charge().equals("1")){
            cbx.setChecked(true);
            title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            title.setTextColor(Color.parseColor("#757575"));
        } else {
            cbx.setChecked(false);
            title.setTextColor(Color.parseColor("#212121"));
            title.getPaint().setFlags(0);
        }


        cbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    update(i, "f", "1");
                    title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    title.setTextColor(Color.parseColor("#757575"));
                } else {
                    update(i, "f", "0");
                    title.setTextColor(Color.parseColor("#212121"));
                    title.getPaint().setFlags(0);
                }
            }
        });
        return view;
    }




    public void del(int x) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                connect();


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(context, "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/DeleteDetailsServlet?id=" + RWlisttb.RWlist.get(x).getStage_id());
        task.execute();
    }

    public void connect() {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {

                    RWlisttb.RWlist.clear();
                    JSONArray rwlist = new JSONArray((String) result.get(0));
                    for (int i = 0; i < rwlist.length(); i++) {
                        JSONObject rw = rwlist.getJSONObject(i);
                        RWBean rwBean = new RWBean();
                        rwBean.setStage_id(rw.getString("stage_id"));
                        rwBean.setProject_id(rw.getString("project_id"));
                        rwBean.setProject_name(rw.getString("project_name"));
                        rwBean.setDescribes(rw.getString("describes"));
                        rwBean.setStart_time(rw.getString("start_time"));
                        rwBean.setEnd_time(rw.getString("end_time"));
                        rwBean.setTypes(rw.getString("types"));
                        rwBean.setPerson_in_charge(rw.getString("person_in_charge"));
                        rwBean.setTx_time(rw.getString("tx_time"));
                        rwBean.setTx_method(rw.getString("tx_method"));

                        JSONArray friends = rw.getJSONArray("friend");

                        ArrayList<HashMap> friendlist = new ArrayList<>();
                        for (int j = 0; j < friends.length(); j++) {
                            JSONObject friend = friends.getJSONObject(j);
                            HashMap friendb = new HashMap();
                            friendb.put("mphone", friend.getString("mphone"));
                            friendb.put("mname", friend.getString("mname"));
                            friendlist.add(friendb);
                        }
                        rwBean.setFriends(friendlist);
                        RWlisttb.RWlist.add(rwBean);
                    }
                    handler.sendMessage(new Message());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(context, "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/GetWorkDetailsServlet?id=" + Projectlistb.projectlistb.get(n).getProjectid());
        task.execute();
    }

    public void update(int i, String what, String value) {

        HttpTask task = new HttpTask(new HttpCallBack() {
            @Override
            public void success(List result) {

            }

            @Override
            public void error(Exception e) {
                Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/ModifyRW?id=" + RWlisttb.RWlist.get(i).getStage_id() + "&what=" + what + "&value=" + value);
        task.execute();
    }
}
