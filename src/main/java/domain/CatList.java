package domain;

public class CatList {
    private int userId;
    private String foodName;
    private String foodPrice;
    private String bizName;
    private String foodPic;
    private String foodNum;
    private int listId;
    private int foodId;
    private int bizId;

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(String foodNum) {
        this.foodNum = foodNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }

    @Override
    public String toString() {
        return "CatList{" +
                "userId=" + userId +
                ", foodName='" + foodName + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", bizName='" + bizName + '\'' +
                ", foodPic='" + foodPic + '\'' +
                ", foodNum='" + foodNum + '\'' +
                ", listId=" + listId +
                ", foodId=" + foodId +
                ", bizId=" + bizId +
                '}';
    }
}
