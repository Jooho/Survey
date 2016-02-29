package org.jhouse.survey.vo;

/**
 * Created by jhouse on 12/16/14.
 */
public class UserVo implements Comparable<UserVo> {
    private int id;
    private String engName;
    private String korName;

    public UserVo() {
    }
    public UserVo( String korName, String engName) {
        this.korName = korName;
        this.engName = engName;
    }
    public UserVo(int id, String korName, String engName) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(UserVo o) {
        return korName.compareTo(o.korName);
    }
}
