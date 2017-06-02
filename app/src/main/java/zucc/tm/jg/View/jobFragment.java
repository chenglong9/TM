package zucc.tm.jg.View;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zucc.tm.jg.R;

import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.Joblisttb;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.RWlisttb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.adapter.jobAdapter;
import zucc.tm.jg.bean.RWBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static zucc.tm.jg.Util.my.my;

/**
 * Created by 45773 on 2017-05-19.
 */

public class jobFragment extends Fragment {

    private ListView list;
    private SwipeRefreshLayout mRefreshLayout;
    private jobAdapter adapter;
    private TextView jir;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(Joblisttb.jobl.size()==0){
                jir.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
                mRefreshLayout.setRefreshing(false);
            }else {
                adapter = new jobAdapter(getActivity());
                list.setAdapter(adapter);
                mRefreshLayout.setRefreshing(false);
            }
        }

    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_job, container, false);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srlayout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Joblisttb.jobl.size()!=0)
                mRefreshLayout.setRefreshing(false);
            }
        });
        list = (ListView) view.findViewById(R.id.list);
        jir = (TextView) view.findViewById(R.id.jir);
        adapter = new jobAdapter(getActivity());
        list.setAdapter(adapter);
        mRefreshLayout.setRefreshing(true);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (!Joblisttb.jobl.get(arg2).getProject_id().equals(""))
                    if (RWlisttb.RWlist.size()!=0&&RWlisttb.RWlist.get(0).getProject_id().equals(Joblisttb.jobl.get(arg2).getProject_id()))
                    {
                        Intent intent = new Intent(getActivity(), UIdesignActivity.class);
                        intent.putExtra("id", huan(Joblisttb.jobl.get(arg2).getProject_id()));
                        intent.putExtra("n", huan2(Joblisttb.jobl.get(arg2).getStage_id()));
                        getActivity().startActivity(intent);
                    }else
                     connectxx(huan(Joblisttb.jobl.get(arg2).getProject_id()),Joblisttb.jobl.get(arg2).getStage_id());
            }

        });


        new Thread() {
            @Override
            public void run() {
                while (Joblisttb.jobl.size()==0);
                handler.sendMessage(new Message());
            }
        }.start();

        TimerTask My= new TimerTask() {
            @Override
            public void run() {
                handler.sendMessage(new Message());
            }
        };

        Timer t=new Timer();
        t.schedule(My,3000);



        return view;
    }
    public int huan(String x)
    {
        int m=-1;
        for (int i = 0; i< Projectlistb.projectlistb.size(); i++)
            if (Projectlistb.projectlistb.get(i).getProjectid().equals(x)) {
                m =i;
                break;
            }
        return m;
    }
    public int huan2(String x)
    {
        int m=-1;
        for (int i = 0; i< RWlisttb.RWlist.size(); i++)
            if (RWlisttb.RWlist.get(i).getStage_id().equals(x)) {
                m =i;
                break;
            }
        return m;
    }
    public void connectxx(final int id,final String n) {

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
                        if (Projectlistb.projectlistb.get(id).getPhone().equals(my.getPhone())) {
                            RWlisttb.RWlist.add(rwBean);

                        } else
                            for (HashMap f : friendlist) {
                                if (f.get("mphone").equals(my.getPhone())) {
                                    RWlisttb.RWlist.add(rwBean);
                                    break;
                                }
                            }
                    }
                    Intent intent = new Intent(getActivity(), UIdesignActivity.class);
                    intent.putExtra("id",  huan2(n));
                    intent.putExtra("n",id);
                    getActivity().startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/GetWorkDetailsServlet?id=" + Projectlistb.projectlistb.get(id).getProjectid());
        task.execute();
    }

}
