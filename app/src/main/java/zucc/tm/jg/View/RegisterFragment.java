package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.PhoneFormatCheckUtils;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.bean.mybean;

import static zucc.tm.jg.Util.my.my;

public class RegisterFragment extends Fragment {
    private EditText et_name;
    private EditText et_call;
    private EditText et_passwd;
    private EditText et_repasswd;
    private Button btn_register;

    private String get;
    private String name;
    private String call;
    private String passwd;
    private String repasswd;


    public static Fragment newInstance(){
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,null);
        initView(view);
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

    public void initView(View view){
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_call = (EditText) view.findViewById(R.id.et_call);
        et_passwd = (EditText) view.findViewById(R.id.et_passwd);
        et_repasswd = (EditText) view.findViewById(R.id.et_repasswd);
        btn_register = (Button) view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                name = et_name.getText().toString();
                call = et_call.getText().toString();
                passwd = et_passwd.getText().toString();
                repasswd = et_repasswd.getText().toString();
                if(name.equals("")){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "姓名不能为空", "确定", null, null,null, true);
                }
                else if(call.equals("")){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "手机号不能为空", "确定", null, null,null, true);
                }
                else if(new PhoneFormatCheckUtils().isChinaPhoneLegal(call)==false){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "手机号格式不正确", "确定", null, null,null, true);
                }
                else if(passwd.equals("")){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "密码不能为空", "确定", null, null,null, true);
                }
                else if(passwd.length()<6){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "密码不能小于6位", "确定", null, null,null, true);
                }
                else if(!passwd.equals(repasswd)){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "两次密码输入不一致", "确定", null, null,null, true);
                }
                else {
                    get="phone="+call+"&pwd="+passwd+"&name="+name;
                    connect();
                }
            }
        });
    }

    public void connect() {

        HttpTask task = new HttpTask(new HttpCallBack() {
            @Override
            public void success(List result) {
                try {
                    JSONObject msg = new JSONObject((String) result.get(0));
                    if (msg.getString("result").equals("ok")) {
                        Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                        my.setName(name);
                        my.setPwd(passwd);
                        my.setPhone(call);

                        SharedPreferences sharedPre=getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE);
                        //获取Editor对象
                        SharedPreferences.Editor editor=sharedPre.edit();
                        //设置参数
                        editor.putString("name", msg.getString("name"));
                        editor.putString("pwd", passwd);
                        editor.putString("phone", call);
                        //提交
                        editor.commit();

                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else{
                        alertdialog.showSimpleDialog(getActivity(), "", "该手机号已被注册", "", "确认", null, null, true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/registerServlet?" + get);
        task.execute();
    }

//    public void connectx() {
//
//        HttpTask task = new HttpTask(new HttpCallBack() {
//
//
//            @Override
//            public void success(List result) {
//                //网络请求成功后将会调用
//                try {
//
//                    Projectlistb.projectlistb.clear();
//                    JSONArray projectlist = new JSONArray((String) result.get(0));
//                    for (int i = 0; i < projectlist.length(); i++) {
//                        JSONObject project = projectlist.getJSONObject(i);
//                        projectbean projectb = new projectbean();
//                        projectb.setPhone(project.getString("people_in_charge"));
//                        projectb.setProjectid(project.getString("project_id"));
//                        projectb.setProjectname(project.getString("project_name"));
//                        projectb.setProjectcon(project.getString("project_describe"));
//                        projectb.setTimes(project.getString("start_time"));
//                        projectb.setTimee(project.getString("end_time"));
//                        JSONArray friends = project.getJSONArray("friend");
//
//                        ArrayList<HashMap> friendlist = new ArrayList<>();
//                        for (int j = 0; j < friends.length(); j++) {
//                            JSONObject friend = friends.getJSONObject(j);
//                            if (friend.getString("mphone").equals(project.getString("people_in_charge")))
//                                continue;
//                            HashMap friendb = new HashMap();
//                            friendb.put("mphone", friend.getString("mphone"));
//                            friendb.put("mname", friend.getString("mname"));
//                            friendlist.add(friendb);
//                        }
//                        HashMap friendb = new HashMap();
//                        friendlist.add(friendb);
//                        projectb.setFriends(friendlist);
//                        Projectlistb.projectlistb.add(projectb);
//                        Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_LONG).show();
//                        finish();
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            @Override
//            public void error(Exception e) {
//            }
//        }, "http://" + curUrl.url + "/registerServlet?phone=1&pwd=1&name=1");
//        task.execute();
//    }
}
