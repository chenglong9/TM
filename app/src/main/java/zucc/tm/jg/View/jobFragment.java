package zucc.tm.jg.View;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zucc.tm.jg.R;

/**
 * Created by 45773 on 2017-05-19.
 */

public class jobFragment extends Fragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_job,container, false);

        return view;
    }


}
