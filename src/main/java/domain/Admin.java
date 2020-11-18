package domain;

import java.util.Date;

/**
 * 管理员的实体类
 */
public class Admin {
    private int adminId;
    private String adminName;
    private String adminPwd;
    private String  adminTel;
    private String  sex;
    private String  adminNickname;
    private String  adminStatus;
    private Date admin_register_time;
    private String  adminInfo;
    private String  adminAddr;

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdminNickname() {
        return adminNickname;
    }

    public void setAdminNickname(String adminNickname) {
        this.adminNickname = adminNickname;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public Date getAdmin_register_time() {
        return admin_register_time;
    }

    public void setAdmin_register_time(Date admin_register_time) {
        this.admin_register_time = admin_register_time;
    }

    public String getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(String adminInfo) {
        this.adminInfo = adminInfo;
    }

    public String getAdminAddr() {
        return adminAddr;
    }

    public void setAdminAddr(String adminAddr) {
        this.adminAddr = adminAddr;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminTel='" + adminTel + '\'' +
                ", sex='" + sex + '\'' +
                ", adminNickname='" + adminNickname + '\'' +
                ", adminStatus='" + adminStatus + '\'' +
                ", admin_register_time=" + admin_register_time +
                ", adminInfo='" + adminInfo + '\'' +
                ", adminAddr='" + adminAddr + '\'' +
                '}';
    }
}
