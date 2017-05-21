package zucc.tm.jg.View;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import zucc.tm.jg.R;
import zucc.tm.jg.adapter.friendAdapter;

/**
 * Created by 45773 on 2017-05-19.
 */

public class friendtFragment extends Fragment{


    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend,container, false);
        list = (ListView) view.findViewById(R.id.list);

        friendAdapter adapter = new friendAdapter(getActivity());
        list.setAdapter(adapter);
        return view;
    }


}
