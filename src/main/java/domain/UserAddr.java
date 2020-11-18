package domain;

public class UserAddr {
    private int userAddrId;
    private int userId;
    private int u_default;
    private String userName;
    private String userTel;
    private String u_province;
    private String u_city;
    private String u_county;
    private String u_info;

    public int getUserAddrId() {
        return userAddrId;
    }

    public void setUserAddrId(int userAddrId) {
        this.userAddrId = userAddrId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getU_default() {
        return u_default;
    }

    public void setU_default(int u_default) {
        this.u_default = u_default;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getU_province() {
        return u_province;
    }

    public void setU_province(String u_province) {
        this.u_province = u_province;
    }

    public String getU_city() {
        return u_city;
    }

    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public String getU_county() {
        return u_county;
    }

    public void setU_county(String u_county) {
        this.u_county = u_county;
    }

    public String getU_info() {
        return u_info;
    }

    public void setU_info(String u_info) {
        this.u_info = u_info;
    }

    @Override
    public String toString() {
        return "UserAddr{" +
                "userAddrId=" + userAddrId +
                ", userId=" + userId +
                ", u_default=" + u_default +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", u_province='" + u_province + '\'' +
                ", u_city='" + u_city + '\'' +
                ", u_county='" + u_county + '\'' +
                ", u_info='" + u_info + '\'' +
                '}';
    }
}
