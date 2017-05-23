package zucc.tm.jg.View;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import zucc.tm.jg.R;
import zucc.tm.jg.Util.getfriend;
import zucc.tm.jg.adapter.friendAdapter;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 45773 on 2017-05-19.
 */

public class friendtFragment extends Fragment{

    private ArrayList<friendbean> arraylist;
    private ListView list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        list = (ListView) view.findViewById(R.id.list);
        getfriend get=new getfriend(getActivity());
        arraylist=get.get();
        friendAdapter adapter = new friendAdapter(getActivity(), arraylist);
        list.setAdapter(adapter);

        return view;
    }


}

/*
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
*/