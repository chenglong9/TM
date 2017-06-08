package zucc.tm.jg.View;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;


import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import zucc.tm.jg.R;
import zucc.tm.jg.Util.Joblisttb;
import zucc.tm.jg.Util.Projectlistb;
import zucc.tm.jg.Util.RWlisttb;
import zucc.tm.jg.Util.alertdialog;
import zucc.tm.jg.adapter.drawerAdapter;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import zucc.tm.jg.R;
import zucc.tm.jg.Util.my;

import zucc.tm.jg.bean.mybean;

import static zucc.tm.jg.View.addprojectActivity.setWindowStatusBarColor;


/**
 * Created by iiro on 7.6.2016.
 */
public class MainActivity extends AppCompatActivity implements
        EasyPermissions.PermissionCallbacks {

    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    private static final int RC_SETTINGS_SCREEN = 125;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private List<String> lvs = new ArrayList<String>();
    private drawerAdapter arrayAdapter;
    private BottomBar bottomBar;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    public Fragment fragment;
    private TextView name;
    private TextView phone;
    private Intent intent;
    private ImageView guodu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guodu = (ImageView) findViewById(R.id.guodu);
        AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);//创建一个AlphaAnimation 对象，渐变从1->0
        aa.setDuration(2000);//设置持续时间
        aa.setFillAfter(false);//设置最后的动画效果，这里是显示状态（最后能够看到这个View)
        guodu.startAnimation(aa);//启动动画
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                guodu.setVisibility(View.GONE);
            }
        });

        intent = new Intent(this, MsgIntentService.class);
        startService(intent);

        locationAndContactsTask();
        setWindowStatusBarColor(MainActivity.this, R.color.colorPrimary);


        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);

        toolbar.setTitle("查看项目");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //设置菜单列表
        lvs.add("通知");
        lvs.add("设置");
        lvs.add("检查更新");
        lvs.add("关于");
        lvs.add("退出登录");

        arrayAdapter = new drawerAdapter(lvs, this);
        lvLeftMenu.setAdapter(arrayAdapter);
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == 0) {
                    Intent intent = new Intent(MainActivity.this, TongzhiActivity.class);
                    MainActivity.this.startActivity(intent);
                } else if (arg2 == 4) {
                    my.my = new mybean();
                    Projectlistb.projectlistb.clear();
                    RWlisttb.RWlist.clear();
                    Joblisttb.jobl.clear();
                    SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPre.edit();
                    editor.clear();
                    editor.commit();
                    MsgIntentService.onclose();
                    stopService(intent);

                    finish();
                    Intent intents = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(intents);

                } else if (arg2 == 2) {
                    Uri uri = Uri.parse("http://www.zcl1995.xin:8080/TeamWork/download");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                } else if (arg2 == 3)
                    alertdialog.showSimpleDialog(MainActivity.this, "", "TM--团队管理app1.0", "", "确认", null, null, true);

            }

        });

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if (tabId == R.id.tab_peoject) {
                    toolbar.setTitle("查看项目");//设置Toolbar标题
                    fragment = new projectFragment();

                } else if (tabId == R.id.tab_job) {
                    toolbar.setTitle("今日任务");//设置Toolbar标题
                    fragment = new jobFragment();

                } else if (tabId == R.id.tab_friends) {
                    toolbar.setTitle("人员管理");//设置Toolbar标题
                    fragment = new friendtFragment();

                }
                bottomBar.getTabWithId(tabId).setBackgroundResource(R.drawable.xx);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            }
        });
        bottomBar.setDefaultTab(R.id.tab_job);

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

            }
        });

        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        name.setText(my.my.getName());
        phone.setText(my.my.getPhone());
    }


    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void locationAndContactsTask() {
        String[] perms = {android.Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permissions, do the thing!

        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, "我们需要访问您的通讯录信息",
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