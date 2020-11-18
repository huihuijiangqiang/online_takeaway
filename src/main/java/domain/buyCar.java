package domain;

import java.util.Date;

public class buyCar {
    private int listId;
    private int userId;
    private int foodId;
    private int foodNum;
    private Date foodAddtime;

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getFoodAddtime() {
        return foodAddtime;
    }

    public void setFoodAddtime(Date foodAddtime) {
        this.foodAddtime = foodAddtime;
    }

    @Override
    public String toString() {
        return "buyCar{" +
                "listId=" + listId +
                ", userId=" + userId +
                ", foodId=" + foodId +
                ", foodNum=" + foodNum +
                ", foodAddtime=" + foodAddtime +
                '}';
    }
}
