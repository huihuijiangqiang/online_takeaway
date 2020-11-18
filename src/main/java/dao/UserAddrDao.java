package dao;

import domain.UserAddr;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

public class UserAddrDao {

    //声明JDBC Template对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());


    /**
     * 根据userAddrId增删改
     * @param userAddr
     */
    public void addUA(UserAddr userAddr){
        String sql="INSERT INTO `onlineOrder`.`userAddr`( `userId`, `userName`, `userTel`, `u_province`, `u_city`, `u_county`, `u_info`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql,userAddr.getUserId(),userAddr.getUserName(),userAddr.getUserTel(),userAddr.getU_province(),userAddr.getU_city(),userAddr.getU_county(),userAddr.getU_info());

    }
    public  void delUA(UserAddr userAddr){
        String sql="DELETE FROM `onlineOrder`.`userAddr` WHERE `userAddrId` = ?";
        template.update(sql,userAddr.getUserAddrId());
    }
    public void editUA(UserAddr o){
        String sql="UPDATE `onlineOrder`.`userAddr` SET `userId` = ?, `userName` = ?, `userTel` = ?, `u_province` = ?, `u_city` = ?, `u_county` = ?, `u_info` = ? WHERE `userAddrId` = ?";
        template.update(sql,o.getUserId(),o.getUserName(),o.getUserTel(),o.getU_province(),o.getU_city(),o.getU_county(),o.getU_info(),o.getUserAddrId());
    }


    /**
     *通过userId查询收货地址
      * @param
     * @return
     */
    public List<UserAddr> searchUserAddr(UserAddr userAddr){
        String sql="select * from userAddr where userId=?";
        List<UserAddr> ff = template.query(sql,new BeanPropertyRowMapper<UserAddr>(UserAddr.class),userAddr.getUserId());
        System.out.println(ff);
        return ff;
    }


    public List<UserAddr> getUserAddrById(UserAddr user) {
        String sql="select * from userAddr where userAddrId=?";
        List<UserAddr> ff = template.query(sql,new BeanPropertyRowMapper<UserAddr>(UserAddr.class),user.getUserAddrId());
        System.out.println(ff);
        return ff;
    }
}
