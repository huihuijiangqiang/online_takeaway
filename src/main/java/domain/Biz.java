package domain;

public class Biz {
    private int bizId;
    private String bizName;
    private String  bizPwd;
    private String bizTel;
    private String sex;
    private String bizNickname;
    private String bizStatus;
    private String bizAge;
    private String bizQualification;
    private String bizInfo;
    private String biz_register_time;
    private String bizAddr;
    private String bizCard;

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getBizPwd() {
        return bizPwd;
    }

    public void setBizPwd(String bizPwd) {
        this.bizPwd = bizPwd;
    }

    public String getBizTel() {
        return bizTel;
    }

    public void setBizTel(String bizTel) {
        this.bizTel = bizTel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBizNickname() {
        return bizNickname;
    }

    public void setBizNickname(String bizNickname) {
        this.bizNickname = bizNickname;
    }

    public String getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(String bizStatus) {
        this.bizStatus = bizStatus;
    }

    public String getBizAge() {
        return bizAge;
    }

    public void setBizAge(String bizAge) {
        this.bizAge = bizAge;
    }

    public String getBizQualification() {
        return bizQualification;
    }

    public void setBizQualification(String bizQualification) {
        this.bizQualification = bizQualification;
    }

    public String getBizInfo() {
        return bizInfo;
    }

    public void setBizInfo(String bizInfo) {
        this.bizInfo = bizInfo;
    }

    public String getBiz_register_time() {
        return biz_register_time;
    }

    public void setBiz_register_time(String biz_register_time) {
        this.biz_register_time = biz_register_time;
    }

    public String getBizAddr() {
        return bizAddr;
    }

    public void setBizAddr(String bizAddr) {
        this.bizAddr = bizAddr;
    }

    public String getBizCard() {
        return bizCard;
    }

    public void setBizCard(String bizCard) {
        this.bizCard = bizCard;
    }

    @Override
    public String toString() {
        return "Biz{" +
                "bizId=" + bizId +
                ", bizName='" + bizName + '\'' +
                ", bizPwd='" + bizPwd + '\'' +
                ", bizTel='" + bizTel + '\'' +
                ", sex='" + sex + '\'' +
                ", bizNickname='" + bizNickname + '\'' +
                ", bizStatus='" + bizStatus + '\'' +
                ", bizAge='" + bizAge + '\'' +
                ", bizQualification='" + bizQualification + '\'' +
                ", bizInfo='" + bizInfo + '\'' +
                ", biz_register_time='" + biz_register_time + '\'' +
                ", bizAddr='" + bizAddr + '\'' +
                ", bizCard='" + bizCard + '\'' +
                '}';
    }
}
