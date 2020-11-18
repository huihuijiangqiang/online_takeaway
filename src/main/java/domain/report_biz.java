package domain;

import java.util.Date;

public class report_biz {
    private Date trime;
    private int report_bizid;
    private int orderNum;
    private int foodNum;
    private int reviewNum;
    private int bizId;

    public Date getTrime() {
        return trime;
    }

    public void setTrime(Date trime) {
        this.trime = trime;
    }

    public int getReport_bizid() {
        return report_bizid;
    }

    public void setReport_bizid(int report_bizid) {
        this.report_bizid = report_bizid;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    @Override
    public String toString() {
        return "report_biz{" +
                "trime=" + trime +
                ", report_bizid=" + report_bizid +
                ", orderNum=" + orderNum +
                ", foodNum=" + foodNum +
                ", reviewNum=" + reviewNum +
                ", bizId=" + bizId +
                '}';
    }
}
