package zucc.tm.jg.View;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import zucc.tm.jg.R;
import zucc.tm.jg.Util.Msglist;
import zucc.tm.jg.Util.curUrl;
import zucc.tm.jg.Util.my;
import zucc.tm.jg.bean.msgbean;

/**
 * Created by dy on 2016/8/29.
 */
public class MsgIntentService extends IntentService {
    private static final String wsurl = "ws://"+ curUrl.url+"/socket";
    private static WebSocketConnection mConnect = new WebSocketConnection();
    private static final String TAG = "MainActivity";
    private static Handler handler = null;
    private NotificationManager nm;

    public static void sethand(Handler handlers) {
        handler = handlers;
    }
    public MsgIntentService() {
        super("worker thread");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        connect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
    private void connect() {
        Log.i(TAG, "ws connect....");
        try {
            mConnect.connect(wsurl, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    Log.i(TAG, "Status:Connect to " + wsurl);

                    JSONObject sendMsg = new JSONObject();
                    try {
                        sendMsg.put("phone", my.my.getPhone());
                        sendMsg.put("type","connect");
                        String msg = sendMsg.toString();
                        sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onTextMessage(String payload) {
                    Log.i(TAG, payload);

                    try {
                        if (payload.equals("你好"))
                            return;
                        JSONObject receiveMsg =  new JSONObject(payload);
                        String type = receiveMsg.getString("type");

                        if (type.equals("msg")) {
                            String id = receiveMsg.getString("id");
                            String name = receiveMsg.getString("name");
                            String msg = receiveMsg.getString("msg");
                            String time = receiveMsg.getString("time");
                            msgbean msgb=new msgbean(id,name,msg,time);
                            Msglist.map.get(id).add(msgb);
                            Msglist.msg=true;

                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);

                        }else if (type.equals("gg"))
                        {
                            titlex("有新的公告");
                            Msglist.gg=true;

                        }else if (type.equals("tz"))
                        {
                            titlex("有新的通知");
                            Msglist.tz=true;
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onClose(int code, String reason) {
                    Log.i(TAG, "Connection lost..");
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }
    /**
     * 发送消息
     *
     * @param msg
     */
    public static void sendMessage(String msg) {
        if (mConnect.isConnected()) {
            mConnect.sendTextMessage(msg);

        } else {
            Log.i(TAG, "no connection!!");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        titlex("连接断开");
        mConnect.disconnect();
    }
    public static  void onclose() {
        mConnect.disconnect();
    }

    public void titlex(String x) {
 /*       Intent intent = new Intent(MsgIntentService.this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);*/

        //1.获取系统通知的管理者
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //2.用notification工厂 创建一个notification
        Notification noti = new Notification.Builder(this)
                .setContentTitle(x)
                .setContentText("")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
               // .setContentIntent(contentIntent)
                .build();
        //3.把notification显示出来
        nm.notify(1, noti);
    }
}
