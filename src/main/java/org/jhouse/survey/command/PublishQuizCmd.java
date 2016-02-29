package org.jhouse.survey.command;

import org.jhouse.survey.manager.WSSessionManager;
import org.jhouse.survey.vo.QuizVo;
import org.json.simple.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * Created by jhouse on 10/12/14.
 */
public class PublishQuizCmd implements Commander {
    WSSessionManager wsSessionManagerService;

    QuizVo quiz;
    String pongUser;
    WebSocketSession webSocketSession;

    public PublishQuizCmd(QuizVo quiz, String pongUser,WSSessionManager wsSessionManagerService) {
        this.quiz = quiz;
        this.pongUser = pongUser;
        this.wsSessionManagerService =wsSessionManagerService;
        webSocketSession = wsSessionManagerService.get(pongUser);
    }

    @Override
    public void execute() {
        String msg = "success";

        JSONObject quizJsonObject = new JSONObject();

        quizJsonObject.put("userName", pongUser);
        quizJsonObject.put("type", "publishQuiz");
        quizJsonObject.put("quizNumber", quiz.getNumber());
        quizJsonObject.put("quizDescription", quiz.getDescription());
        quizJsonObject.put("msg", msg);


        try {
            if(webSocketSession != null ) {
                webSocketSession.sendMessage(new TextMessage(quizJsonObject.toJSONString()));
            }
        } catch (IOException e) {
            System.out.println("The user websocket is disconnected");
            e.printStackTrace();


        }

        System.out.println(quizJsonObject.toJSONString());
    }


}
