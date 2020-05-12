package android.com.path.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/WatchWS/{userName}")//{userName}特殊機制
public class WatchWS {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());//這裡的session不是Servlet的session是websocket的session(等於連線)
	//synchronizedSet:回傳已經同步處理好的集合
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen //類似init()
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {//Session userSession等於連線
		connectedSessions.add(userSession);//先把連線加入集合
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen())//確認連線有開
				session.getAsyncRemote().sendText(message);//
		}
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError 
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
