package zucc.tm.jg.View;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import zucc.tm.jg.R;
import zucc.tm.jg.adapter.jobAdapter;

/**
 * Created by 45773 on 2017-05-19.
 */

public class jobFragment extends Fragment {

    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_job,container, false);

        list = (ListView) view.findViewById(R.id.list);
        jobAdapter adapter = new jobAdapter(getActivity());
        list.setAdapter(adapter);


        return view;
    }


}
