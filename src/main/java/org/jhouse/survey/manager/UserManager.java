package org.jhouse.survey.manager;

import org.jhouse.survey.vo.UserVo;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by jhouse on 12/13/14.
 */
@Component
public class UserManager {
    private static final Map<String,String> loginedUserList = Collections.synchronizedMap(new LinkedHashMap<String,String>());
    private Map<Integer, UserVo> peerMap = new TreeMap<Integer, UserVo>();

    public UserManager(){
        loadPeerMap();
    }

    public boolean isUserNamePreserved(String username, String passwd) {

        if(loginedUserList.get(username) != null ){
            if(loginedUserList.get(username).equals(passwd))
                return false;
            else
                return true;
        }

        return false;
    }

    public void putUserName(String username, String passwd) {
        loginedUserList.put(username,passwd);
    }

    public Map<String,String> getLoginedUserList(){
        return loginedUserList;
    }

    public Map<Integer, UserVo> getPeerMap() {
        return peerMap;

    }
    public boolean isValidUser(String username){
        for(int id : peerMap.keySet()){
            UserVo userVo=peerMap.get(id);
            if(userVo.getEngName().equals(username))
                return true;
        }
            return false;
    }
    private void loadPeerMap() {
        Set<UserVo> tempSet = new TreeSet<UserVo>();

        tempSet.add(new UserVo("함재경", "jhahm"));
        tempSet.add(new UserVo("박기범", "kpark"));
        tempSet.add(new UserVo("박상근", "spark"));
        tempSet.add(new UserVo("여득만", "dyeo"));
        tempSet.add(new UserVo("반동준", "dban"));
        tempSet.add(new UserVo("목명균", "mmok"));
        tempSet.add(new UserVo("박세준", "sejpark"));
        tempSet.add(new UserVo("송기흥", "ksong"));
        tempSet.add(new UserVo("이철규", "clee"));
        tempSet.add(new UserVo("박준상", "jopark"));
        tempSet.add(new UserVo("윤서익", "syoon"));
        tempSet.add(new UserVo("차태진", "tcha"));
        tempSet.add(new UserVo("유명희", "myoo"));
        tempSet.add(new UserVo("이윤경", "ylee"));
        tempSet.add(new UserVo("이응직", "eyi"));
        tempSet.add(new UserVo("조희정", "hcho"));
        tempSet.add(new UserVo("황인찬", "ihwang"));
        tempSet.add(new UserVo("최원영", "wchoi"));
        tempSet.add(new UserVo("박준완", "jupark"));
        tempSet.add(new UserVo("김용기", "ykim"));
        tempSet.add(new UserVo("신준희", "jshin"));
        tempSet.add(new UserVo("김현수", "hykim"));
        tempSet.add(new UserVo("이형승", "hylee"));
        tempSet.add(new UserVo("허경", "khuh"));
        tempSet.add(new UserVo("김성헌", "thkim"));
        tempSet.add(new UserVo("김상운", "skim"));
        tempSet.add(new UserVo("김호중", "hokim"));
        tempSet.add(new UserVo("장진호", "jjang"));
        tempSet.add(new UserVo("허강욱", "kheo"));
        tempSet.add(new UserVo("김희태", "hukim"));
        tempSet.add(new UserVo("김승필", "sekim"));
        tempSet.add(new UserVo("심우택", "wshim"));
        tempSet.add(new UserVo("조형근", "hycho"));
        tempSet.add(new UserVo("소현진", "hso"));
        tempSet.add(new UserVo("이규석", "kyulee"));
        tempSet.add(new UserVo("정승환", "jseunghw"));
        tempSet.add(new UserVo("문종영", "jmoon"));
        tempSet.add(new UserVo("한진구", "jhan"));
        tempSet.add(new UserVo("이주호", "jlee"));
        tempSet.add(new UserVo("신재욱", "jashin"));
        tempSet.add(new UserVo("이승은", "selee"));
        tempSet.add(new UserVo("지혜중", "hjlee"));
        tempSet.add(new UserVo("조은지", "echo"));
        tempSet.add(new UserVo("인우현", "win"));
        tempSet.add(new UserVo("이현정", "hyulee"));
        tempSet.add(new UserVo("백송이", "sbaek"));
        tempSet.add(new UserVo("고승현", "seko"));

        int count = 1;
        for (UserVo userVo : tempSet) {
            int tempCount = count++;
            userVo.setId(tempCount);
            peerMap.put(tempCount, userVo);
        }
    }
}
