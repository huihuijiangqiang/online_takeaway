package domain;

import java.math.BigDecimal;

public class OrderList {
    private String orderInfo;
    private int foodId;
    private int foodNum;
    private BigDecimal foodPrice;
    private String foodPic;
    private String foodName;
    private String sendStatus;
    private String reviewsLv;
    private String reviewsInfo;
    private String bizReply;
    private Integer reviewsStatus;


    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getReviewsLv() {
        return reviewsLv;
    }

    public void setReviewsLv(String reviewsLv) {
        this.reviewsLv = reviewsLv;
    }

    public String getReviewsInfo() {
        return reviewsInfo;
    }

    public void setReviewsInfo(String reviewsInfo) {
        this.reviewsInfo = reviewsInfo;
    }

    public String getBizReply() {
        return bizReply;
    }

    public void setBizReply(String bizReply) {
        this.bizReply = bizReply;
    }

    public Integer getReviewsStatus() {
        return reviewsStatus;
    }

    public void setReviewsStatus(Integer reviewsStatus) {
        this.reviewsStatus = reviewsStatus;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "orderInfo='" + orderInfo + '\'' +
                ", foodId=" + foodId +
                ", foodNum=" + foodNum +
                ", foodPrice=" + foodPrice +
                ", foodPic='" + foodPic + '\'' +
                ", foodName='" + foodName + '\'' +
                ", sendStatus='" + sendStatus + '\'' +
                ", reviewsLv='" + reviewsLv + '\'' +
                ", reviewsInfo='" + reviewsInfo + '\'' +
                ", bizReply='" + bizReply + '\'' +
                ", reviewsStatus=" + reviewsStatus +
                '}';
    }
}
