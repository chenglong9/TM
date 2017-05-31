package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.logging.LogRecord;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.RWlisttb;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.projectAdapter;
import zucc.tm.jg.adapter.rwAdapter;
import zucc.tm.jg.bean.RWBean;
import zucc.tm.jg.bean.projectbean;

import static zucc.tm.jg.R.mipmap.project;


public class stageListFragment extends Fragment {
    private NoScrollListview rw_list;
    private int id;
    private FloatingActionButton fab;
    public rwAdapter adapter;
    private ArrayList<String> lvs = new ArrayList<String>();
    private SwipeRefreshLayout mRefreshLayout;

    public static Fragment newInstance() {
        stageListFragment fragment = new stageListFragment();
        return fragment;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage_list, null);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        id = (int) getActivity().getIntent().getSerializableExtra("id");
        if (!my.my.getPhone().equals(Projectlistb.projectlistb.get(id).getPhone()))
            fab.setVisibility(View.GONE);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srlayout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                connect();
            }
        });
        rw_list = (NoScrollListview) view.findViewById(R.id.list_rw);
        mRefreshLayout.setRefreshing(true);
        connect();
        adapter = new rwAdapter(getActivity(), (int) getActivity().getIntent().getSerializableExtra("id"), handler);
        rw_list.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addrwActivity.class);
                intent.putExtra("id", (int) getActivity().getIntent().getSerializableExtra("id"));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "fab").toBundle());

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

                     for (HashMap f : friendlist) {
                            if (f.get("mphone").equals(my.my.getPhone())) {
                                RWlisttb.RWlist.add(rwBean);
                                break;
                            } else if (Projectlistb.projectlistb.get(id).getPhone().equals(my.my.getPhone())) {
                                RWlisttb.RWlist.add(rwBean);
                                break;
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/GetWorkDetailsServlet?id=" + Projectlistb.projectlistb.get((int) getActivity().getIntent().getSerializableExtra("id")).getProjectid());
        task.execute();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        connect();
    }
}
