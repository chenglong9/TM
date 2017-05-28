package zucc.tm.jg.View;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.HttpCallBack;
import zucc.tm.jg.Util.HttpTask;
import zucc.tm.jg.Util.NoScrollListview;
import zucc.tm.jg.Util.PhoneFormatCheckUtils;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.adapter.memberAdapter;

import static zucc.tm.jg.Util.my.my;


public class projectInfoFragment extends Fragment {
    NoScrollListview list;
    private CardView cardView;
    private Button add;
    public int id;
    private TextView times;
    private TextView timee;
    private TextView con;
    private TextView name;
    private TextView phone;
    private View viewm;
    private TextView gonggao;

    public static Fragment newInstance(){
        projectInfoFragment fragment = new projectInfoFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewm = inflater.inflate(R.layout.fragment_project_info,null);
        init(viewm);

        id= (int) getActivity().getIntent().getSerializableExtra("id");
        memberAdapter adapter=new memberAdapter(getActivity(), Projectlistb.projectlistb.get(id).getFriends());
        list.setAdapter(adapter);

        cardView= (CardView) viewm.findViewById(R.id.tv_gonggao);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),GonggaoActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,"gg").toBundle());
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View views = LayoutInflater.from(getActivity()).inflate(R.layout.editalert, null);
                final EditText editText= (EditText) views.findViewById(R.id.edit);
                alertdialog.showeditDialog(getActivity(), "请输入成员的手机号", "好的", views, "取消", "确认", null, new DialogInterface.OnClickListener (){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (editText.getText().toString().equals(my.getPhone()))
                        {
                            alertdialog.showSimpleDialog(getActivity(), "", "不需要邀请自己", "", "确认", null, null, true);
                            return;

                        }
                        if (PhoneFormatCheckUtils.isPhoneLegal(editText.getText().toString()))
                        {
                            alertdialog.showSimpleDialog(getActivity(), "", "请输入正确的手机号", "", "确认", null, null, true);
                            return;
                        }
                        String get="con=邀请您加入项目"+"&name="+my.getPhone()+"&memberid="+editText.getText()+"&type=msg"+"&id="+ Projectlistb.projectlistb.get(id).getProjectid();
                        addmsg(get);
                    }
                }, true);
            }
        });
        return viewm;
    }
    public  void init(View view)
    {
        list= (NoScrollListview) viewm.findViewById(R.id.member_list);
        add= (Button) viewm.findViewById(R.id.add_x);
        times=(TextView)viewm.findViewById(R.id.time_t);
        timee=(TextView)viewm.findViewById(R.id.time_t2);
        con=(TextView)viewm.findViewById(R.id.con_t);
        name=(TextView)viewm.findViewById(R.id.name);
        phone=(TextView)viewm.findViewById(R.id.phone);
        gonggao=(TextView)viewm.findViewById(R.id.gonggao);

        times.setText(Projectlistb.projectlistb.get(id).getTimes());
        timee.setText(Projectlistb.projectlistb.get(id).getTimee());
        con.setText(Projectlistb.projectlistb.get(id).getProjectcon());
        name.setText(my.getName());
        phone.setText(my.getPhone());
        gonggao.setText("暂无公告");
    }
    public  void addmsg(String get) {

        HttpTask task = new HttpTask(new HttpCallBack() {


            @Override
            public void success(List result) {
                //网络请求成功后将会调用
                try {
                    JSONObject msg = new JSONObject((String) result.get(0));
                    if (msg.getString("result").equals("ok")) {
                        alertdialog.showSimpleDialog(getActivity(), "", "已向该用户发出邀请", "", "确认", null, null, true);
                    }else{
                        alertdialog.showSimpleDialog(getActivity(), "", "该用户尚未注册本应用", "", "确认", null, null, true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(Exception e) {

            }
        }, "http://" + curUrl.url + "/addmsgServlet?"+get);
        task.execute();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        init(viewm);
    }
}
