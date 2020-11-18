package domain;

import java.math.BigDecimal;
import java.util.Date;

public class Food {
    private int foodId;
    private String foodName;
    private BigDecimal foodPrice;
    private BigDecimal foodDescription;
    private Date foodAddtime;
    private String foodPic;
    private String foodType;
    private int foodNum;
    private Integer bizId;
    private String foodInfo;
    private int rank;
    private int testRank;

    public int getTestRank() {
        return testRank;
    }

    public void setTestRank(int testRank) {
        this.testRank = testRank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public BigDecimal getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(BigDecimal foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Date getFoodAddtime() {
        return foodAddtime;
    }

    public void setFoodAddtime(Date foodAddtime) {
        this.foodAddtime = foodAddtime;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodDescription=" + foodDescription +
                ", foodAddtime=" + foodAddtime +
                ", foodPic='" + foodPic + '\'' +
                ", foodType='" + foodType + '\'' +
                ", foodNum=" + foodNum +
                ", bizId=" + bizId +
                ", foodInfo='" + foodInfo + '\'' +
                ", rank=" + rank +
                ", testRank=" + testRank +
                '}';
    }
}
