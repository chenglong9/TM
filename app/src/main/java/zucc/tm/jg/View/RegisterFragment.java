package zucc.tm.jg.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.PhoneFormatCheckUtils;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.alertdialog;

import static zucc.tm.jg.Util.my.my;

public class RegisterFragment extends Fragment {
    private EditText et_call;
    private EditText et_passwd;
    private EditText et_repasswd;
    private Button btn_register;
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
        et_call = (EditText) view.findViewById(R.id.et_call);
        et_passwd = (EditText) view.findViewById(R.id.et_passwd);
        et_repasswd = (EditText) view.findViewById(R.id.et_repasswd);
        btn_register = (Button) view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String call = et_call.getText().toString();
                String passwd = et_passwd.getText().toString();
                String repasswd = et_repasswd.getText().toString();
                if(call.equals("")){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "手机号不能为空", "确定", null, null,null, true);
                }
                else if(passwd.equals("")){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "密码不能为空", "确定", null, null,null, true);
                }
                else if(!passwd.equals(repasswd)){
                    alertdialog.showSimpleDialog(getActivity(), "提醒", "两次密码输入不一致", "确定", null, null,null, true);
                }
                else {

                }
            }
        });
    }
}
