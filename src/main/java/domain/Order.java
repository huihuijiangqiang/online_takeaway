package domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private String orderStatus;
    private String cause;
    private int bizId;
    private String payStatus;
    private String sendStatus;
    private Date addTime;
    private int foodId;
    private String orderInfo;

    private Integer userAddrId;

    public Integer getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(Integer userAddrId) {
        this.userAddrId = userAddrId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderStatus='" + orderStatus + '\'' +
                ", cause='" + cause + '\'' +
                ", bizId=" + bizId +
                ", payStatus='" + payStatus + '\'' +
                ", sendStatus='" + sendStatus + '\'' +
                ", addTime=" + addTime +
                ", foodId=" + foodId +
                ", orderInfo='" + orderInfo + '\'' +
                ", userAddrId=" + userAddrId +
                '}';
    }
}
