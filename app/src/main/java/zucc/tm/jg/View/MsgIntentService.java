package zucc.tm.jg.View;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import zucc.tm.jg.Util.curUrl;

/**
 * Created by dy on 2016/8/29.
 */
public class MsgIntentService extends IntentService {
    private static final String wsurl = "ws://"+ curUrl.url+"/socket";
    private static WebSocketConnection mConnect = new WebSocketConnection();
    private static final String TAG = "MainActivity";

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
                  /*  JSONObject sendMsg = new JSONObject();
                    try {
                        sendMsg.put("userId", "user1");
                        sendMsg.put("request", request);
                        sendMsg.put("message", "");
                        sendMsg.put("userType", "user");
                        sendMsg.put("close", "false");
                        String msg = sendMsg.toString();
                        mConnect.sendTextMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                }

                @Override
                public void onTextMessage(String payload) {
                    Log.i(TAG, payload);
                    JSONObject receiveMsg = null;
                  /*  try {
                        receiveMsg = new JSONObject("");
                       String userId = receiveMsg.getString("userId");
                        request = receiveMsg.getString("request");
                        String message = receiveMsg.getString("message");
                        MessageBean msg = new MessageBean(message, MessageBean.RECEIVE, new Date());
                        msgList.add(msg);
                        msgAdapter.notifyDataSetChanged();
                        msgListView.setSelection(msgList.size());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

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
        mConnect.disconnect();
    }

}
