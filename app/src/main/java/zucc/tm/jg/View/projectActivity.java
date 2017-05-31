package zucc.tm.jg.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.adapter.fragmentAdapter;

public class projectActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final String []sTitle = new String[]{"简介","任务","分析"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        id= (int) getIntent().getSerializableExtra("id");
        initView();

    }
//    获取menu样式
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_project, menu);
        return true;
    }

    private void initView() {

        //TooBar
        toolbar = (Toolbar) findViewById(R.id.pa_toolbar);
        toolbar.setTitle(Projectlistb.projectlistb.get(id).getProjectname());//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //message，menuItem监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.messageItem:
                        Intent intent = new Intent(projectActivity.this,qunActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        //Tab、Fragment
        mViewPager = (ViewPager) findViewById(R.id.view_pager_project);
        mTabLayout = (TabLayout) findViewById(R.id.tab_project);
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[2]));

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
        fragments.add(projectInfoFragment.newInstance());
        fragments.add(stageListFragment.newInstance());
        fragments.add(analysisFragment.newInstance());

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
