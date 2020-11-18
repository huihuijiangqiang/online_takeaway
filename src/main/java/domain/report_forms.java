package domain;

import java.util.Date;

public class report_forms {
    private Date rtime;
    private int userNum;
    private int reviewNum;
    private int foodNum;
    private int orderNum;
    private int bizNum;

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getBizNum() {
        return bizNum;
    }

    public void setBizNum(int bizNum) {
        this.bizNum = bizNum;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    @Override
    public String toString() {
        return "report_forms{" +
                "rtime=" + rtime +
                ", userNum=" + userNum +
                '}';
    }
}
