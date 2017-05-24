package zucc.tm.jg.Util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunFly on 2016/12/4.
 */

public class HttpTask extends AsyncTask<Object,Object,List> {
    private String DATAURL="";//数据源

    private List contentDatas;//数据
    private HttpCallBack callBack;//回调函数


    public HttpTask(HttpCallBack callBack, String url){
        this.DATAURL=url;
        this.contentDatas = new ArrayList<>();
        this.callBack = callBack;
    }
    //耗时长的在此运行，如：网络请求
    @Override
    protected List doInBackground(Object[] params) {
        URL connection = null;
        try {
            connection = new URL(DATAURL);

            HttpURLConnection conn = (HttpURLConnection) connection.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
           // conn.setRequestProperty("Charset", "utf-8");

            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream in = new BufferedInputStream(conn.getInputStream());
                String response = dealWithJson(in);
                contentDatas.add(response);
            }else {
                // 请求失败
                Log.e("error","error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.callBack.error(e);
        }
        return contentDatas;
    }


    private String dealWithJson(InputStream is) throws JSONException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //返回结果，onbackground运行完将会调用此方法，此方法在主线程中运行
    @Override
    protected void onPostExecute(List contentDatas) {
        super.onPostExecute(contentDatas);
        this.callBack.success(contentDatas);
    }
}
