package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.adapter.rwAdapter;

public class LoginFragment extends Fragment {
    private EditText et_call;
    private EditText et_passwd;
    private Button btn_login;
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
                String call = et_call.getText().toString();
                String passwd = et_passwd.getText().toString();
                if(call.equals("")){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "手机号不能为空", "确定", null, null,null, true);
                }
                else if(passwd.equals("")){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "密码不能为空", "确定", null, null,null, true);
                }
                else {

                }
            }
        });
    }
}
