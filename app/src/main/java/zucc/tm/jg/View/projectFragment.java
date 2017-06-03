package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.Joblisttb;
import zucc.tm.jg.Util.Msglist;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.projectAdapter;
import zucc.tm.jg.bean.RWBean;
import zucc.tm.jg.bean.msgbean;
import zucc.tm.jg.bean.projectbean;

/**
 * Created by 45773 on 2017-05-19.
 */

public class projectFragment extends Fragment {

    private ListView list;
    private FloatingActionButton fab;
    public projectAdapter adapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        list = (ListView) view.findViewById(R.id.list);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srlayout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                connect();
            }
        });

        if (Projectlistb.projectlistb.size() == 0) {
            mRefreshLayout.setRefreshing(true);
            connect();
        } else {
            adapter = new projectAdapter(getActivity());
            list.setAdapter(adapter);

        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(getActivity(), projectActivity.class);
                intent.putExtra("id", arg2);

                getActivity().startActivity(intent);
            }

        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addprojectActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "fab").toBundle());

            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           final int position, long id) {
                if (Projectlistb.projectlistb.get(position).getPhone().equals(my.my.getPhone()))
                    alertdialog.showSimpleDialog(getActivity(), "", "是否删除该项目?", "取消", "删除", null, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            del(position);
                        }
                    }, true);
                return true;
            }
        });


        return view;
    }

    public void connect() {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {
                    mRefreshLayout.setRefreshing(false);
                    Projectlistb.projectlistb.clear();
                    JSONArray projectlist = new JSONArray((String) result.get(0));
                    for (int i = 0; i < projectlist.length(); i++) {
                        JSONObject project = projectlist.getJSONObject(i);
                        projectbean projectb = new projectbean();
                        projectb.setPhone(project.getString("people_in_charge"));
                        projectb.setProjectid(project.getString("project_id"));
                        projectb.setProjectname(project.getString("project_name"));
                        projectb.setProjectcon(project.getString("project_describe"));
                        projectb.setTimes(project.getString("start_time"));
                        projectb.setTimee(project.getString("end_time"));

                        JSONArray friends = project.getJSONArray("friend");
                        ArrayList<HashMap> friendlist = new ArrayList<>();
                        for (int j = 0; j < friends.length(); j++) {
                            JSONObject friend = friends.getJSONObject(j);
                            if (friend.getString("mphone").equals(project.getString("people_in_charge"))) {
                                projectb.setPhonename(friend.getString("mname"));
                                continue;
                            }
                            HashMap friendb = new HashMap();
                            friendb.put("mphone", friend.getString("mphone"));
                            friendb.put("mname", friend.getString("mname"));
                            friendlist.add(friendb);
                        }
                        projectb.setFriends(friendlist);
                        Projectlistb.projectlistb.add(projectb);
                    }
                    adapter = new projectAdapter(getActivity());
                    list.setAdapter(adapter);
                    String get = proid();
                    connectjob(get);
                    for (int m = 0; m < Projectlistb.projectlistb.size(); m++) {
                        ArrayList<msgbean> msgl = new ArrayList<msgbean>();
                        Msglist.map.put(Projectlistb.projectlistb.get(m).getProjectid(), msgl);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/GetProjectServlet?id=" + my.my.getPhone());
        task.execute();
    }
    public void setAlarm(String time,String name) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm");

        try {
            Date dateStart = dateFormat.parse(time);
            Date date = new Date();
            if (dateStart.getTime()<date.getTime())
                return;
            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getActivity(), newActivity.class);
            intent.putExtra("flag",name);//显示任务提醒
            int requestCode = 0;
            PendingIntent pendingIntent = PendingIntent.getActivity(getActivity().getApplicationContext(),
                    requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long triggerAtTime = dateStart.getTime();//设置时间

            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void del(int x) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                connect();
                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/DeleteProjectServlet?id=" + Projectlistb.projectlistb.get(x).getProjectid());
        task.execute();
    }

    public void connectjob(String get) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {

                    JSONArray rwlist = new JSONArray((String) result.get(0));
                    Joblisttb.jobl.clear();
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
                        if (getActivity()!=null)
                        setAlarm(rw.getString("tx_time"),rw.getString("project_name"));

                        JSONArray friendx = rw.getJSONArray("friend");
                        JSONArray friends = friendx.getJSONArray(0);
                        ArrayList<HashMap> friendlist = new ArrayList<>();
                        for (int j = 0; j < friends.length(); j++) {
                            JSONObject friend = friends.getJSONObject(j);
                            HashMap friendb = new HashMap();
                            friendb.put("mphone", friend.getString("mphone"));
                            friendb.put("mname", friend.getString("mname"));
                            friendlist.add(friendb);
                        }
                        rwBean.setFriends(friendlist);
                        for (HashMap f : friendlist) {
                            if (f.get("mphone").equals(my.my.getPhone())) {
                                break;
                            }
                        }
                        Joblisttb.jobl.add(rwBean);
                    }
                    getx();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/GetJobs?id=" + get);
        task.execute();
    }

    public void getx() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            for (int i = 0; i < Joblisttb.jobl.size(); ) {
                Date dateStart = dateFormat.parse(Joblisttb.jobl.get(i).getStart_time());
                Date dateEnd = dateFormat.parse(Joblisttb.jobl.get(i).getEnd_time());
                Date now = new Date();
                if (dateStart.getTime() <= now.getTime() && dateEnd.getTime() >= now.getTime()) {
                    i++;
                } else  if (dateStart.getTime() <= now.getTime() && dateEnd.getTime()+24*60*60*1000 >= now.getTime()) {
                    i++;
                } else Joblisttb.jobl.remove(i);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (Joblisttb.jobl.size() > 0) {
            RWBean rwBean = new RWBean();
            String x = huan(Joblisttb.jobl.get(0).getProject_id());
            rwBean.setProject_name(x);
            rwBean.setProject_id("");
            Joblisttb.jobl.add(0, rwBean);
        }

        for (int i = 1; i < Joblisttb.jobl.size(); i++) {
            if (i + 1 < Joblisttb.jobl.size() & !Joblisttb.jobl.get(i).getProject_id().equals(""))
                if (!(Joblisttb.jobl.get(i).getProject_id().equals(Joblisttb.jobl.get(i + 1).getProject_id()))) {
                    RWBean rwBean = new RWBean();
                    String x = huan(Joblisttb.jobl.get(i + 1).getProject_id());
                    rwBean.setProject_name(x);
                    rwBean.setProject_id("");
                    Joblisttb.jobl.add(i + 1, rwBean);
                }
        }
    }

    private static String proid() {
        JSONArray json = new JSONArray();
        for (int i = 0; i < Projectlistb.projectlistb.size(); i++)
            json.put(Projectlistb.projectlistb.get(i).getProjectid());

        return json.toString();
    }

    public String huan(String x) {
        for (int i = 0; i < Projectlistb.projectlistb.size(); i++)
            if (Projectlistb.projectlistb.get(i).getProjectid().equals(x)) {
                x = Projectlistb.projectlistb.get(i).getProjectname();
                break;
            }
        return x;
    }

    @Override
    public void onResume() {
        super.onResume();
        connect();
    }
}

