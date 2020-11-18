package dao;

import domain.Admin;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

/**
 * 操作数据库中admin表的类
 */
public class adminDao {

    //声明JDBC Template对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());
    /**
     * 登录方法
     * @param loginAdmin
     * @return
     */
    public Admin login(Admin loginAdmin){
        try {
            //1.编写sql
            String sql="select * from admin where adminName=? and adminPwd=?";

            //2.调用
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class)
                    , loginAdmin.getAdminName(), loginAdmin.getAdminPwd());

            return admin;
        }catch (DataAccessException e){

            return null;
        }

    }

    public List<Admin> selectAll() {
        //1.编写sql
        String sql="select * from admin ";
        //2.调用
        List<Admin> b=template.query(sql,new BeanPropertyRowMapper<Admin>(Admin.class));

        return b;

    }

    public Admin checkAdminName(String adminName) {
        try {
            //1.编写sql
            String sql="SELECT * FROM admin WHERE adminName = ?";

            //2.调用
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class)
                    , adminName);

            return admin;
        }catch (DataAccessException e){

            return null;
        }



    }

    public boolean changeAdminInfo(Admin admin) {
        String sql="UPDATE `onlineOrder`.`admin` SET `adminName` = ?, `adminPwd` = ?, `adminTel` = ?, `adminNickname` = ? WHERE `adminId` = ?";
        int update = template.update(sql, admin.getAdminName(), admin.getAdminPwd(), admin.getAdminTel(), admin.getAdminNickname(), admin.getAdminId());
        if (update==1){
            return true;
        }
        return false;
    }
}
