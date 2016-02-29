package org.jhouse.survey.manager;

import org.jhouse.survey.vo.ElectVo;
import org.jhouse.survey.vo.QuizVo;
import org.jhouse.survey.vo.RankVo;
import org.jhouse.survey.vo.UserVo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by jhouse on 12/16/14.
 */
@Component
public class QuizManager {
    private static final List<QuizVo> quizList = Collections.synchronizedList(new ArrayList<QuizVo>());
    private ConcurrentLinkedQueue<ElectVo> electQueue = new ConcurrentLinkedQueue<ElectVo>();
    private Map<Integer, LinkedList<RankVo>> resultPageMap = new HashMap<Integer, LinkedList<RankVo>>();
    private LinkedList<RankVo> resultPage;


    private ArrayList<String> electedPeerList = new ArrayList<String>();

    @Autowired
    private WSSessionManager wsSessionManager;
    @Autowired
    private UserManager userManager;

    public QuizManager() {
        QuizVo quiz1 = new QuizVo(1, "가장 Red Hatter 인거 같은 사람은?");
        QuizVo quiz2 = new QuizVo(2, "외모나 성격이나 제일 나이스할 것 같은 사람은?");
        QuizVo quiz3 = new QuizVo(3, "마음 속 이야기까지 털어놓을정도로 믿어도 될것 같은 사람은?");
        QuizVo quiz4 = new QuizVo(4, "어렸을때 이성 꽤나 울렸을 거 같은 사람은?");
        QuizVo quiz5 = new QuizVo(5, "자신이 어떤일을 당하면 나중에라도 꼭 복수할 것 같은 사람은?");
        QuizVo quiz6 = new QuizVo(6, "이성에게 모든 것을 다 주면서 최선을 다할 거 같은 사람은?");
        QuizVo quiz7 = new QuizVo(7, "술이 가장 셀것 같은 사람은?");
        QuizVo quiz8 = new QuizVo(8, "화나면 제일 무서울 거 같은 사람은?");
        QuizVo quiz9 = new QuizVo(9, "외모와 달리 반전의 매력을 가지고 있는 사람은?");
        QuizVo quiz10 = new QuizVo(10, "한번 마음 먹으면, 세상이 끝나도 꼭 해내고 말것 같은 사람은?");
        quizList.add(quiz1);
        quizList.add(quiz2);
        quizList.add(quiz3);
        quizList.add(quiz4);
        quizList.add(quiz5);
        quizList.add(quiz6);
        quizList.add(quiz7);
        quizList.add(quiz8);
        quizList.add(quiz9);
        quizList.add(quiz10);
    }

    public List<QuizVo> getQuizList() {
        return quizList;
    }

    public QuizVo getQuizVo(int num) {
        return quizList.get(num - 1);
    }

    public void addElectToQueue(ElectVo electVo) {
        electQueue.add(electVo);

        JSONObject electedPeopleCountJsonObject = new JSONObject();

        electedPeopleCountJsonObject.put("electedPeopleCount", electQueue.size());
        electedPeopleCountJsonObject.put("type", "electedPeopleCount");
        try {
            WebSocketSession webSocketSession = wsSessionManager.get("admin");
            if (webSocketSession != null) {
                webSocketSession.sendMessage(new TextMessage(electedPeopleCountJsonObject.toJSONString()));
            }
        } catch (IOException e) {
            System.out.println("The user websocket is disconnected");
            e.printStackTrace();
        }
        electedPeerList.add(electVo.getUserName());
        System.out.println(electedPeopleCountJsonObject.toJSONString());

    }

    public ArrayList<String> getNotElectPeople() {
        Map<Integer, UserVo> peerMap = userManager.getPeerMap();
        ArrayList<String> notElectedPeerList = new ArrayList<String>();
        for (int userId : peerMap.keySet()) {
            UserVo user = peerMap.get(userId);
            if (!electedPeerList.contains(user.getEngName())) {
                notElectedPeerList.add(user.getKorName());
            }
        }
        return notElectedPeerList;
    }

    public void resultProcess(int quizNumber) {
        QuizVo quizVo = getQuizVo(quizNumber);
//
//        if(quizVo.getResult().size() != 0){
//            lineUpElectedPeople(quizVo);
//        }else {
        resultPage = new LinkedList<RankVo>();
        quizVo = putElectVoToResultMap(quizVo);
        lineUpElectedPeople(quizVo);
//        }
        resultPageMap.put(quizNumber, resultPage);
    }

    public LinkedList<RankVo> getResultPage(int quizNumber) {
        return resultPageMap.get(quizNumber);
    }


    private void lineUpElectedPeople(QuizVo quizVo) {
        Map<Integer, Integer> rankMap = new HashMap<Integer, Integer>();
        Set resultSet = quizVo.getResult();
        int totalUserCount = resultSet.size();
        Iterator<ElectVo> it = resultSet.iterator();
        ElectVo electVo = null;
        while (it.hasNext()) {
            electVo = it.next();
            Integer electedCount = 0;
            if (rankMap.get(electVo.getElectedPerson()) != null) {
                electedCount = rankMap.get(electVo.getElectedPerson()) + 1;
                rankMap.put(electVo.getElectedPerson(), electedCount);
            } else {
                rankMap.put(electVo.getElectedPerson(), 1);
            }
        }

        Map<Integer, Integer> sortedRankMap = sortByValues(rankMap);
        resultPage = generateResultPage(3, sortedRankMap, totalUserCount);
    }

    /*
    @Param rankCount : 상위 몇 위까지 뽑을건지 선택
            sortedRankMap : 선택받은 숫자 순서대로 정렬된 맵
     */
    private LinkedList<RankVo> generateResultPage(int rankCount, Map<Integer, Integer> sortedRankMap, int totalUserCount) {
        Map<Integer, UserVo> peerMap = userManager.getPeerMap();
        Iterator<Integer> userNameList = sortedRankMap.keySet().iterator();
        LinkedList<RankVo> resultRank = new LinkedList<RankVo>();

        while (rankCount-- > 0 && userNameList.hasNext()) {
            Integer userId = userNameList.next();
            UserVo userVo = peerMap.get(userId);
            Integer electedCount = sortedRankMap.get(userId);
            rankCheck(userVo, electedCount, resultRank, totalUserCount);
        }

        return resultRank;

    }

    private void rankCheck(UserVo userVo, Integer electedCount, LinkedList<RankVo> lastRank, int totalUserCount) {
        RankVo seniorUser = lastRank.peekLast();

        if (seniorUser == null) {
            lastRank.add(new RankVo(userVo, RankVo.RANK.FIRST_CLASS, electedCount, totalUserCount));
        } else if (seniorUser.getElectedCount() == electedCount) {
            lastRank.add(new RankVo(userVo, seniorUser.getRank(), electedCount, totalUserCount));
        } else if (seniorUser.getElectedCount() > electedCount) {
            lastRank.add(new RankVo(userVo, RankVo.RANK.changeIntToValue(seniorUser.getRank().getIntValue() + 1), electedCount, totalUserCount));
        } else {
            lastRank.removeLast();
            lastRank.add(new RankVo(userVo, seniorUser.getRank(), electedCount, totalUserCount));
            lastRank.add(new RankVo(seniorUser.getUserVo(), RankVo.RANK.changeIntToValue(seniorUser.getRank().getIntValue() + 1), seniorUser.getElectedCount(), totalUserCount));
        }

    }

    private QuizVo putElectVoToResultMap(QuizVo quizVo) {
        ElectVo electVo = null;
        while (electQueue.peek() != null) {
            electVo = electQueue.poll();
            quizVo.addResult(electVo);

        }
        return quizVo;
    }

    private <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {

            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K, V> sortedMap = new LinkedHashMap<K, V>();

        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


    public void resetQueue() {
        electQueue.clear();
    }
}
