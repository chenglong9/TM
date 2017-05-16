package zucc.tm.jg;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import zucc.tm.jg.Util.curUrl;

public class MainActivity extends AppCompatActivity implements
        EasyPermissions.PermissionCallbacks{

    private TextView t;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    private static final int RC_SETTINGS_SCREEN = 125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t= (TextView) findViewById(R.id.text);

        t.setText(curUrl.url);

    }
    public void dian(View v)
    {
        locationAndContactsTask();
        t.setText(curUrl.url);
    }

    private void showListView(){
        ListView listView = new ListView(this);
        ArrayList<HashMap<String, String>> list = getPeopleInPhone2();
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2,
                new String[] {"peopleName", "phoneNum"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listView.setAdapter(adapter);
        setContentView(listView);
    }
    private ArrayList<HashMap<String, String>> getPeopleInPhone2(){
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);  // 获取手机联系人
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            int indexPeopleName = cursor.getColumnIndex(Phone.DISPLAY_NAME); // people name
            int indexPhoneNum = cursor.getColumnIndex(Phone.NUMBER);   // phone number
            String strPeopleName = cursor.getString(indexPeopleName);
            String strPhoneNum = cursor.getString(indexPhoneNum);
            map.put("peopleName", strPeopleName);
            map.put("phoneNum", strPhoneNum);
            list.add(map);
        }
        if(!cursor.isClosed()){
            cursor.close();
            cursor = null;
        }
        return list;
    }

    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void locationAndContactsTask() {
        String[] perms = { android.Manifest.permission.READ_CONTACTS };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permissions, do the thing!
            showListView();
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this,"我们需要访问您的通讯录信息",
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


