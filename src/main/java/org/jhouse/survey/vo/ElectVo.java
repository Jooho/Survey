package org.jhouse.survey.vo;

/**
 * Created by jhouse on 12/16/14.
 */
public class ElectVo {
    private String userName;
    private int electedPerson;
    private int quizNumber;

    public ElectVo() {
    }

    public int getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public ElectVo(String username, int electedPerson){
        this.userName =username;
        this.electedPerson=electedPerson;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getElectedPerson() {
        return electedPerson;
    }

    public void setElectedPerson(int electedPerson) {
        this.electedPerson = electedPerson;
    }
}
