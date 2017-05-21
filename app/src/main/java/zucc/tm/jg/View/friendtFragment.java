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
import zucc.tm.jg.adapter.friendAdapter;
import zucc.tm.jg.bean.friendbean;

/**
 * Created by 45773 on 2017-05-19.
 */

public class friendtFragment extends Fragment implements
        EasyPermissions.PermissionCallbacks {
    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    private static final int RC_SETTINGS_SCREEN = 125;
    Cursor c;

    private ListView list;
    private ArrayList<friendbean> arraylist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        list = (ListView) view.findViewById(R.id.list);
        locationAndContactsTask();

        return view;
    }


    public ArrayList<friendbean> getContact(Activity context) {
        ArrayList<friendbean> listMembers = new ArrayList<friendbean>();
        Cursor cursor = null;
        try {

            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            // 这里是获取联系人表的电话里的信息  包括：名字，名字拼音，联系人id,电话号码；
            // 然后在根据"sort-key"排序
            cursor = context.getContentResolver().query(
                    uri,
                    new String[]{"display_name", "sort_key", "contact_id",
                            "data1"}, null, null, "sort_key");

            if (cursor.moveToFirst()) {
                do {
                    friendbean contact = new friendbean();
                    String contact_phone = cursor
                            .getString(cursor
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String name = cursor.getString(0);
                    String sortKey = getSortKey(cursor.getString(1));
                    int contact_id = 1;
                    contact.contact_name = name;
                    contact.sortKey = sortKey;
                    contact.contact_phone = contact_phone;
                    contact.setContact_id(contact_id);
                    if (name != null)
                        listMembers.add(contact);
                } while (cursor.moveToNext());
                c = cursor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context = null;
        }
        return listMembers;
    }

    private static String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }

    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void locationAndContactsTask() {
        String[] perms = {android.Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
            // Have permissions, do the thing!
            arraylist = getContact(getActivity());
            for (int i = 0; i < arraylist.size(); i++) {
                if (i + 1 < arraylist.size() & !arraylist.get(i).getContact_name().equals(""))
                    if (!(arraylist.get(i).getSortKey().equals(arraylist.get(i + 1).getSortKey()))) {
                        friendbean friend = new friendbean();
                        friend.setContact_name("");
                        friend.setSortKey(arraylist.get(i + 1).getSortKey());
                        arraylist.add(i + 1, friend);
                    }
            }
            friendAdapter adapter = new friendAdapter(getActivity(), arraylist);
            list.setAdapter(adapter);

        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, "我们需要访问您的通讯录信息",
                    RC_LOCATION_CONTACTS_PERM, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, getString(R.string.rationale_ask_again))
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setPositiveButton(getString(R.string.setting))
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
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