package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.adapter.rwAdapter;


public class stageListFragment extends Fragment {
    private NoScrollListview rw_list;
    private FloatingActionButton fab;
    private ArrayList<String> lvs =new ArrayList<String>();

    public static Fragment newInstance(){
        stageListFragment fragment = new stageListFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stage_list,null);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        lvs.add("可行性分析");
        lvs.add("需求分析");
        lvs.add("UI设计");
        lvs.add("编码");
        lvs.add("测试");

        rw_list = (NoScrollListview) view.findViewById(R.id.list_rw);
        rwAdapter adapter = new rwAdapter(getActivity(),lvs);
        rw_list.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addrwActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,"fab").toBundle());

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
