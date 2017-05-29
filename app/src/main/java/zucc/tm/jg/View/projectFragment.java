package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.projectAdapter;
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
        }
        else {
            adapter = new projectAdapter(getActivity());
            list.setAdapter(adapter);

        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(getActivity(),projectActivity.class);
                intent.putExtra("id",arg2);
                getActivity().startActivity(intent);
            }

        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addprojectActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,"fab").toBundle());

            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           final int position, long id) {

                alertdialog.showSimpleDialog(getActivity(), "", "是否删除该项目?", "取消", "删除", null, new DialogInterface.OnClickListener (){
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

    public  void connect() {

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

    public  void del(int x) {

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
}

