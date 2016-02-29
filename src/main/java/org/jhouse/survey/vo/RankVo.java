package org.jhouse.survey.vo;

/**
 * Created by jhouse on 12/16/14.
 */
public class RankVo {
    private UserVo userVo;
    private RANK rank;
    private int totalUserCount;
    private int rate;
    private int electedCount;

    public RankVo(UserVo userVo, RANK rank, int electedCount, int totalUserCount) {
        this.userVo = userVo;
        this.electedCount = electedCount;
        this.rank = rank;
        this.totalUserCount = totalUserCount;
        this.rate = (electedCount * 100 / totalUserCount);
    }


    public RankVo() {
    }

    public int getElectedCount() {
        return electedCount;
    }

    public void setElectedCount(int electedCount) {
        this.electedCount = electedCount;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public RANK getRank() {
        return rank;
    }

    public void setRank(RANK rank) {
        this.rank = rank;
    }

    public int getRate() {

        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public enum RANK {
        FIRST_CLASS(1), SECOND_CLASS(2), THIRD_CLASS(3);

        private int value;

        private RANK(int value) {
            this.value = value;
        }

        public static RANK changeIntToValue(int value) {
            RANK returnRank;
            switch (value) {
                case 1:
                    returnRank = RANK.FIRST_CLASS;
                    break;
                case 2:
                    returnRank = RANK.SECOND_CLASS;
                    break;
                case 3:
                    returnRank = RANK.THIRD_CLASS;
                    break;
                default:
                    returnRank = RANK.FIRST_CLASS;
            }
            return returnRank;
        }

        public int getIntValue() {
            return value;
        }
    }

}
