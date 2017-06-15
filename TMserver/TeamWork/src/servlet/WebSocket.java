package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import Dao.Notice;
import bean.tb_notice;
import net.sf.json.JSONArray;

@ServerEndpoint("/socket")
public class WebSocket {

	// connect key为session的ID，value为此对象this
	private static final HashMap<String, Object> connect = new HashMap<String, Object>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据

	@OnOpen
	public void onOpen(Session session) {
		
	}

	/**
	 * 收到客户端消息时触发
	 * 
	 * @param relationId
	 * @param userCode
	 * @param message
	 * @return
	 */
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");

	@OnMessage
	public String onMessage(Session session, String message) {
		System.out.println(message);
		try {
			JSONObject msg = new JSONObject(message);
			if (msg.getString("type").equals("connect")) {
				connect.put(msg.getString("phone"), session);
				sendToUser(msg.getString("phone"), "你好");
			} else if (msg.getString("type").equals("msg3")) {

				String id = msg.getString("id");
				String msgs = msg.getString("msg");
				String name = msg.getString("name");
				Notice notice = new Notice();
				tb_notice tb_n = new tb_notice(Integer.parseInt(id), name, "msg3", msgs, sdf.format(new Date()));
				notice.add(tb_n);

				org.json.JSONArray phone = msg.getJSONArray("phone");
				
				for (int i = 0; i < phone.length(); i++) {
					JSONObject js = phone.getJSONObject(i);
					JSONObject json = new JSONObject();
					json.accumulate("id", id);
					json.accumulate("msg", msgs);
					json.accumulate("name", msg.getString("name"));
					json.accumulate("time", sdf.format(new Date()));
					json.accumulate("type", "msg");
					
					sendToUser(js.getString("phone"), json.toString());
				}

			}else if (msg.getString("type").equals("ggc")){
				JSONObject json = new JSONObject();
				json.accumulate("type", "gg");
				sendToUser(msg.getString("phone"), json.toString());
			}else if (msg.getString("type").equals("tzc")){
				JSONObject json = new JSONObject();
				json.accumulate("type", "tz");
				sendToUser(msg.getString("phone"), json.toString());
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	/**
	 * 发送给指定用户
	 * 
	 * @param user
	 *            用户名
	 * @param msg
	 *            发送的消息
	 * @param session
	 */
	private void sendToUser(String user, String msg) {

		Session client = (Session) connect.get(user);
		try {
			if (client!=null&&client.isOpen()) {
				client.getBasicRemote().sendText(msg);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 异常时触发
	 * 
	 * @param relationId
	 * @param userCode
	 * @param session
	 */
	@OnError
	public void onError(Throwable throwable, Session session) {

		System.out.print("onError" + throwable.toString());
	}

	/**
	 * 关闭连接时触发
	 * 
	 * @param relationId
	 * @param userCode
	 * @param session
	 */
	@OnClose
	public void onClose(Session session) {
		
		for ( Entry<String, Object> entry : connect.entrySet()) {
			if ((Session)entry.getValue()==session) {
				connect.remove(entry.getKey());
				break;
			}
		}
		System.out.print("onClose ");
	}
}