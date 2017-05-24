package zucc.tm.jg.Util;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import zucc.tm.jg.bean.friendbean;

/**
 * Created by 45773 on 2017-05-23.
 */

public class getfriend {

    private ArrayList<friendbean> arraylist;
    private Cursor c;
    private Activity activity;
    public getfriend(Activity activity) {
       this.activity=activity;
        arraylist = getContact(activity);
    }


    public ArrayList<friendbean> get(){
        for (int i = 0; i < arraylist.size(); i++) {
            if (i + 1 < arraylist.size() & !arraylist.get(i).getContact_name().equals(""))
                if (!(arraylist.get(i).getSortKey().equals(arraylist.get(i + 1).getSortKey()))) {
                    friendbean friend = new friendbean();
                    friend.setContact_name("");
                    friend.setSortKey(arraylist.get(i + 1).getSortKey());
                    arraylist.add(i + 1, friend);
                }
        }
        return arraylist;
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
}
