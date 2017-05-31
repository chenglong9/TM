package zucc.tm.jg.View;

import android.content.Intent;
import android.content.SharedPreferences;
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
import zucc.tm.jg.Util.my;
import zucc.tm.jg.adapter.fragmentAdapter;

public class LoginActivity extends AppCompatActivity {

    public static final String[] sTitle = new String[]{"登录", "注册"};
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);


        if (!sharedPre.getString("name", "").equals("")) {
            my.my.setName(sharedPre.getString("name", ""));
            my.my.setPhone(sharedPre.getString("phone", ""));
            my.my.setPwd(sharedPre.getString("pwd", ""));
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        initView();
    }

    private void initView() {

        //Tab、Fragment
        mViewPager = (ViewPager) findViewById(R.id.view_pager_project);
        mTabLayout = (TabLayout) findViewById(R.id.tab_login);
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[1]));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(LoginFragment.newInstance());
        fragments.add(RegisterFragment.newInstance());

        fragmentAdapter adapter = new fragmentAdapter(getSupportFragmentManager(), fragments, Arrays.asList(sTitle));
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
