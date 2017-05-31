package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.PhoneFormatCheckUtils;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.rwAdapter;

import static zucc.tm.jg.Util.my.my;

public class LoginFragment extends Fragment {
    private EditText et_call;
    private EditText et_passwd;
    private Button btn_login;

    private String get;
    private String call;
    private String passwd;
    public static Fragment newInstance(){
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,null);
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
        et_call = (EditText) view.findViewById(R.id.et_call);
        et_passwd = (EditText) view.findViewById(R.id.et_passwd);
        btn_login = (Button) view.findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                call = et_call.getText().toString();
                passwd = et_passwd.getText().toString();
                if(call.equals("")){
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
                else {
                    get = "phone="+call+"&pwd="+passwd;
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
                        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_LONG).show();
                        my.setName(msg.getString("name"));
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
                    }
                    else if(msg.getString("result").equals("no")){
                        alertdialog.showSimpleDialog(getActivity(), "", "该手机号未注册", "", "确认", null, null, true);
                    }
                    else if(msg.getString("result").equals("no2")){
                        alertdialog.showSimpleDialog(getActivity(), "", "密码不正确", "", "确认", null, null, true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_LONG).show();
            }
        }, "http://" + curUrl.url + "/LoginServlet?" + get);
        task.execute();
    }
}
