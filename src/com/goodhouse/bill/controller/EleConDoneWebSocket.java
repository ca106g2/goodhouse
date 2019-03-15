package com.goodhouse.bill.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@WebListener()
@ServerEndpoint("/EleConDoneWebSocket")
public class EleConDoneWebSocket implements ServletContextListener{
	
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext con = sce.getServletContext();
		con.setAttribute("webSocketSession", allSessions);
	}
	
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		allSessions.add(userSession);
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
	}

}
