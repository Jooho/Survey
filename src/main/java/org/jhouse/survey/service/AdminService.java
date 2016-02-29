package org.jhouse.survey.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.jhouse.survey.command.PublishQuizCmd;
import org.jhouse.survey.manager.QuizManager;
import org.jhouse.survey.manager.UserManager;
import org.jhouse.survey.manager.WSSessionManager;
import org.jhouse.survey.vo.ElectVo;
import org.jhouse.survey.vo.QuizVo;
import org.jhouse.survey.vo.RankVo;
import org.jhouse.survey.vo.UserVo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jhouse on 12/14/14.
 */
@Service
public class AdminService {
    @Autowired
    UserManager userManager;

    @Autowired
    WSSessionManager wsSessionManager;

    @Autowired
    QuizManager quizManager;

    private ArrayList<QuizVo> quizVoList;
    private PublishQuizCmd publishQuizCmd;


    public AdminService() {

    }

    public JSONObject getQuizList() {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject rootJson = new JSONObject();
        JSONArray quizListJson = new JSONArray();
        List<QuizVo> quizList = quizManager.getQuizList();
        try {
            for (QuizVo quizVo : quizList) {
                quizListJson.add(mapper.valueToTree(quizVo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rootJson.put("quizList", quizListJson);
        return rootJson;
    }

    public boolean publishQuiz(int quizNum) {
        for (String pongUserId : userManager.getLoginedUserList().keySet()) {
            publishQuizCmd = new PublishQuizCmd(quizManager.getQuizVo(quizNum), pongUserId, wsSessionManager);
            publishQuizCmd.execute();
        }
        quizManager.resetQueue();
        return true;
    }

    public boolean electQuiz(ElectVo electVo) {
        boolean result = true;
        try {
            quizManager.addElectToQueue(electVo);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public JSONObject showResult(QuizVo quizVo) {
        quizManager.resultProcess(quizVo.getNumber());
        LinkedList<RankVo> resultPage = quizManager.getResultPage(quizVo.getNumber());

        ObjectMapper mapper = new ObjectMapper();
        JSONObject rootJson = new JSONObject();
        JSONArray resultListJson = new JSONArray();

        try {
            while (resultPage.peek() != null)

                resultListJson.add(mapper.valueToTree(resultPage.poll()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        rootJson.put("resultList", resultListJson);
        rootJson.put("quizDescription", quizManager.getQuizVo(quizVo.getNumber()).getDescription());

        return rootJson;
    }

    public JSONObject peerList() {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject rootJson = new JSONObject();
        JSONArray peerMapJson = new JSONArray();
        Map<Integer, UserVo> peerMap = userManager.getPeerMap();
        try {
            for (int id : peerMap.keySet()) {
                UserVo userVo = peerMap.get(id);
                peerMapJson.add(mapper.valueToTree(userVo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rootJson.put("peerList", peerMapJson);
        return rootJson;
    }

    public JSONObject disposeNotElectedPeerList() {
        JSONObject rootJson = new JSONObject();
        JSONArray notElectedPeerListJson = new JSONArray();
        ArrayList<String> peerMap = quizManager.getNotElectPeople();
        try {
//            for (String userName : peerMap) {
//                notElectedPeerListJson.add("{userName : "+ userName +"}");
            notElectedPeerListJson.addAll(peerMap);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        rootJson.put("notElectedPeerList", notElectedPeerListJson);
        System.out.println(notElectedPeerListJson.toJSONString());
        return rootJson;
    }



}
