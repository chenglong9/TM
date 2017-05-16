package zucc.tm.jg.Util;

import android.app.Application;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 圆圈 on 2017-05-08.
 */
public class PushApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
        Set<String> set = new HashSet<>();
        set.add("chenglong");//名字任意，可多添加几个,能区别就好了
        JPushInterface.setTags(this, set, null);//设置标签
    }
}
