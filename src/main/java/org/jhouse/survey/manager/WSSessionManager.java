package org.jhouse.survey.manager;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jhouse on 10/9/14.
 */
@Component
public class WSSessionManager {

    private static final Map<String,WebSocketSession> wsSessionList = Collections.synchronizedMap(new HashMap<String,WebSocketSession>());

    public WebSocketSession get(String userId){
        return wsSessionList.get(userId);
    }

    public void put(String userId, WebSocketSession session){
        System.out.println("websocket session is added to WSChannelManager for " + userId + " : session = " +session.getId());

        wsSessionList.put(userId, session);
        System.out.println(contain(userId));

    }


    public void remove(String userId) {
        System.out.println("websocket session is removed from WSChannelManager for " + userId + " : session = " + wsSessionList.get(userId).getId());

        wsSessionList.remove(userId);
        System.out.println(contain(userId));
    }

    public boolean contain(String userId){
        return wsSessionList.containsKey(userId);
    }

    //admin 을 제외한 사람수 리턴
    public int getTotalSessionCount(){
        return  wsSessionList.size();
    }
}

