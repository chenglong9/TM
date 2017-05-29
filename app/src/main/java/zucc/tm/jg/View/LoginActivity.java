package zucc.tm.jg.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.adapter.fragmentAdapter;

public class LoginActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final String []sTitle = new String[]{"登录","注册"};
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView() {

        //TooBar
//        toolbar = (Toolbar) findViewById(R.id.pa_toolbar);
//        toolbar.setTitle(Projectlistb.projectlistb.get(id).getProjectname());//设置Toolbar标题
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        //Tab、Fragment
        mViewPager = (ViewPager) findViewById(R.id.view_pager_project);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(LoginFragment.newInstance());
        fragments.add(RegisterFragment.newInstance());

        fragmentAdapter adapter = new fragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(sTitle));
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
}
