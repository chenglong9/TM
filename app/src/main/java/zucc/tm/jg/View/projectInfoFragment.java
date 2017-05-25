package zucc.tm.jg.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.adapter.memberAdapter;


public class projectInfoFragment extends Fragment {
    NoScrollListview list;
    public static Fragment newInstance(){

        projectInfoFragment fragment = new projectInfoFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_info,null);
        list= (NoScrollListview) view.findViewById(R.id.member_list);
        int id= (int) getActivity().getIntent().getSerializableExtra("id");
        memberAdapter adapter=new memberAdapter(getActivity(), Projectlistb.projectlistb.get(id).getFriends());
        list.setAdapter(adapter);
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
