package android.com.chat.controller;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnOpen;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import android.com.chat.model.ChatMessage;
import android.com.chat.model.State;

@ServerEndpoint("/ChatWS/{userName}")
public class ChatWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();

	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		// 設定成500KB為了配合Android bundle傳送大小
		int maxBufferSize = 500 * 1024;
		userSession.setMaxTextMessageBufferSize(maxBufferSize);
		userSession.setMaxBinaryMessageBufferSize(maxBufferSize);
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			session.getAsyncRemote().sendText(stateMessageJson);
		}

		String text = String.format(
				"Session ID = %s, connected; userName = %s%nusers: %s%nmaxTextMessageBufferSize = %s",
				userSession.getId(), userName, userNames, userSession.getMaxTextMessageBufferSize());
		System.out.println(text);
	}

	// 此方法接收String型式資料
	@OnMessage
	public void onMessage(Session userSession, String message) {

//		System.out.println("message received: " + message);
//		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
//		String messageType = chatMessage.getMessageType();
//		System.out.println("messageType = " + messageType);
//		String receiver = chatMessage.getReceiver();
//		Session receiverSession = sessionsMap.get(receiver);
//		if (receiverSession != null && receiverSession.isOpen()) {
//			// 如果訊息內容包含圖片，必須將資料改成byte型式傳送，否則Android端會因為資料量過大而無法接收
//			if (messageType.equals("image")) {
//				int imageLength = chatMessage.getContent().getBytes().length;
//				System.out.println("image length = " + imageLength);
//				receiverSession.getAsyncRemote().sendBinary(ByteBuffer.wrap(message.getBytes()));
//			} else {
//				receiverSession.getAsyncRemote().sendText(message);
//			}
//		}

		for (Map.Entry<String, Session> entry : sessionsMap.entrySet()) {
			
			
			
			System.out.println("message received: " + message);
			ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
			String messageType = chatMessage.getMessageType();
			System.out.println("messageType = " + messageType);
			
			
			if (entry.getValue() != null && entry.getValue().isOpen()) {
				// 如果訊息內容包含圖片，必須將資料改成byte型式傳送，否則Android端會因為資料量過大而無法接收
				if (messageType.equals("image")) {
					int imageLength = chatMessage.getContent().getBytes().length;
					System.out.println("image length = " + imageLength);
					entry.getValue().getAsyncRemote().sendBinary(ByteBuffer.wrap(message.getBytes()));
				} else {
					entry.getValue().getAsyncRemote().sendText(message);
					System.out.println("entry.getValue() = " + entry.getValue());
				}
			}
		}

	}

	// 此方法接收byte型式資料
	@OnMessage
	public void OnMessage(Session userSession, ByteBuffer bytes) {
		String message = new String(bytes.array());
		System.out.println("ByteBuffer Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
