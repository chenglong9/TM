package zucc.tm.jg.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.TZlisttb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.View.TongzhiActivity;
import zucc.tm.jg.bean.TZbean;

/**
 * Created by 王泽豪 on 2017/5/24.
 */

public class tongzhiAdapter extends BaseAdapter {
    private Context context;
    private Handler handler;
    public tongzhiAdapter(Context context,Handler handler) {
        this.context = context;
        this.handler=handler;
    }

    @Override
    public int getCount() {
        return TZlisttb.TZ.size();
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
        if(view == null)
        {
            view = mInflater.inflate(R.layout.item_tongzhi, null);
        }
        TextView  neirou= (TextView) view.findViewById(R.id.neirou);
        TextView agree = (TextView) view.findViewById(R.id.agree);
        TextView disagree = (TextView) view.findViewById(R.id.disagree);
        TextView time = (TextView) view.findViewById(R.id.time);

        neirou.setText("邀请你加入"+TZlisttb.TZ.get(i).getProject());
        time.setText(TZlisttb.TZ.get(i).getNt_time().toString());

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect(i);
            }
        });

        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del(i);
            }
        });
        return view;
    }

    public  void connect(final int i) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                del( i);

            }

            @Override
            public void error(Exception e) {
                Toast.makeText(context, "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/addaddServlet?id=" + TZlisttb.TZ.get(i).getProject_id()+"&phone="+my.my.getPhone()+"&workid=0");
        task.execute();
    }
    public  void del(int i) {

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
        }, "http://" + curUrl.url + "/delmsg2Servlet?id=" + TZlisttb.TZ.get(i).getNotice_id());
        task.execute();
    }

}
