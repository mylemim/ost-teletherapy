package ost.teletherapy.project.controllers;

import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class StatisticsController {
	
    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        System.out.println(message);
    	for (Session peer : client.getOpenSessions()) {
            peer.getBasicRemote().sendObject(message);
        }
    }
}