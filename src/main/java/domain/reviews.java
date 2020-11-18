package domain;

public class reviews {
    private String reviewsLv;
    private String reviewsInfo;
    private String foodName;
    private String orderInfo;
    private String bizReply;
    private Integer foodId;
    private Integer bizId;
    private Integer foodNum;

    public String getBizReply() {
        return bizReply;
    }

    public void setBizReply(String bizReply) {
        this.bizReply = bizReply;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Integer getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(Integer foodNum) {
        this.foodNum = foodNum;
    }

    @Override
    public String toString() {
        return "reviews{" +
                "reviewsLv='" + reviewsLv + '\'' +
                ", reviewsInfo='" + reviewsInfo + '\'' +
                ", foodName='" + foodName + '\'' +
                ", orderInfo='" + orderInfo + '\'' +
                ", bizReply='" + bizReply + '\'' +
                ", foodId=" + foodId +
                ", bizId=" + bizId +
                ", foodNum=" + foodNum +
                '}';
    }
}
