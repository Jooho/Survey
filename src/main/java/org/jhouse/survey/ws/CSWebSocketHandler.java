package org.jhouse.survey.ws;

import org.jhouse.survey.manager.WSSessionManager;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

/**
 * Created by jhouse on 10/9/14.
 */
public class CSWebSocketHandler extends AbstractWebSocketHandler {
    @Autowired
    WSSessionManager wsSessionManager;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payloadMessage = (String) message.getPayload();
        System.out.println("payloadMessage");
        System.out.println(payloadMessage);
        session.sendMessage(new TextMessage(payloadMessage));
    }

    // Connection이 구성된 후, 호출되는 method
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        System.out.println("afterConnectionEstablished");

        wsSessionManager.put(session.getAttributes().get("username").toString(), session);
        sendConnectedPeopleCount();
        System.out.println("connected : " + session.getAttributes().get("username").toString());


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Connection이 종료된 후, 호출되는 method
        super.afterConnectionClosed(session, status);
        System.out.println("afterConnectionClosed");
        wsSessionManager.remove(session.getAttributes().get("username").toString());
        sendConnectedPeopleCount();
        System.out.println("connected : " + session.getAttributes().get("username").toString());

    }

    public void sendConnectedPeopleCount() {

        JSONObject connectedPeopleCount = new JSONObject();
        int connectedUserCount = 0;
        if (wsSessionManager.get("admin") == null) {
            connectedUserCount = wsSessionManager.getTotalSessionCount();
        } else {
            connectedUserCount = wsSessionManager.getTotalSessionCount() - 1;
        }
        connectedPeopleCount.put("connectedPeopleCount", connectedUserCount);
        connectedPeopleCount.put("type", "connectedPeopleCount");
        try {
            WebSocketSession webSocketSession = wsSessionManager.get("admin");
            if (webSocketSession != null) {
                webSocketSession.sendMessage(new TextMessage(connectedPeopleCount.toJSONString()));
            }
        } catch (IOException e) {
            System.out.println("The user websocket is disconnected");
            e.printStackTrace();
        }

        System.out.println(connectedPeopleCount.toJSONString());

    }

}
