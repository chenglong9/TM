package zucc.tm.jg.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import zucc.tm.jg.R;
import zucc.tm.jg.adapter.projectAdapter;

/**
 * Created by 45773 on 2017-05-19.
 */

public class projectFragment extends Fragment{


    private ListView list;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project,container, false);
        list = (ListView) view.findViewById(R.id.list);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        projectAdapter adapter = new projectAdapter(getActivity());
        list.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }


}
