package com.common;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.Map.Entry;


import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.*;

//@ServerEndpoint("/TogetherWS/{userName}")
@ServerEndpoint("/TogetherWS/{watchingMb_ids}")
public class TogetherWS {
	private static final Map<Session,String> connectedSessions = new HashMap<>();

	/*
	 * 憒����ttpSession��ervletContext敹�祕雿�
	 * ServerEndpointConfig.Configurator.modifyHandshake()嚗�
	 * ���ttps://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
//	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
	public void onOpen(@PathParam("watchingMb_ids") String watchingMb_ids, Session userSession) throws IOException {
		System.out.println("onOpen");
//		connectedSessions.put(userName,userSession);
		connectedSessions.put(userSession,watchingMb_ids);
		System.out.println(watchingMb_ids);
		String[] parts =watchingMb_ids.split("\\+");
		for (String string : parts) {
			System.out.println(string);
		}
//		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		String text = String.format("Session ID = %s, connected; watchingMb_ids = %s", userSession.getId(), watchingMb_ids);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("onMEssage");
//		1.String key="";
//		2.String value="";
		Session keySession=null;
		JSONObject msgJSON=null;
		String watchedId=null;
//		String watchedName=null;
//		String watchedContent=null;
//		String content=null;
		try {
			msgJSON = new JSONObject(message);
			watchedId=(String)msgJSON.get("watchedId");
//			watchedName=(String)msgJSON.get("watchedName");
//			watchedContent=(String)msgJSON.get("watchedContent");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (Entry<String, Session> entry : connectedSessions.entrySet()) {
		for (Entry<Session, String> entry : connectedSessions.entrySet()) {
			System.out.println("entry.getValue():"+entry.getValue());
			System.out.println("watchedId:"+watchedId);
//				    1.if(entry.getValue().getId().equals(userSession.getId())) {
//					2.if(entry.getKey().getId().equals(userSession.getId())) {
				    if(entry.getValue().equals(watchedId)) {
				    //如果拿到的message裡面的rcd_mb_id=正在觀察的watchingMb_ids則會拿到session
//				    	1.key = entry.getKey();
//				    	2.value = entry.getValue();
				    	keySession = entry.getKey();
				    	if (entry.getKey().isOpen()) {
				    		entry.getKey().getAsyncRemote().sendText(message);
				    	}
				    }
//				    if (entry.getValue().isOpen()){
//					entry.getValue().getAsyncRemote().sendText(message);
				    
		}
//		1.connectedSessions.get("getmemid").getAsyncRemote().sendText(message);
//		2.connectedSessions.get(key).getAsyncRemote().sendText(message);
//		1.System.out.println("[" + key + ":" + message + "]");
//		2.System.out.println("[" + value + ":" + message + "]");
		System.out.println("[" + keySession + ":" + message.toString() + "]");
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("onClose");
		for (Entry<Session, String> entry : connectedSessions.entrySet()) {
//		for (Entry<String, Session> entry : connectedSessions.entrySet()) {
//			   for (int i = 0; i <connectedSessions.size() ; i++) {
//				    if(entry.getValue()==userSession) {
				    if(entry.getKey()==userSession) {
				    	connectedSessions.remove(entry.getKey());
//				    }
			  }
		}
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s", userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
