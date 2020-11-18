package dao;

import domain.Biz;
import domain.report_biz;
import domain.reviews;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

public class bizDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());
    public List<Biz> selectAll (){

        //1.编写sql
        String sql="select * from bizS ";
        //2.调用
        List<Biz> b=template.query(sql,new BeanPropertyRowMapper<Biz>(Biz.class));

        return b;
    }
    public boolean delBiz(Biz biz){
        String sql="DELETE FROM `onlineOrder`.`bizS` WHERE `bizId` = ?";
        int update = template.update(sql,biz.getBizId());
        if (update==1){
            return true;
        }
        return false;
    }


    public boolean addBiz(Biz biz) {
        String sql="INSERT INTO `onlineOrder`.`bizS`(`bizName`, `bizTel`,`sex`,`bizNickname`,`bizAge`,`bizAddr`,`bizPwd`) VALUES(?, ?,?,?,?,?,?)";
        int update = template.update(sql,biz.getBizName(),biz.getBizTel(),biz.getSex(),biz.getBizNickname(),biz.getBizAge(),biz.getBizAddr(),biz.getBizPwd());
        if (update==1){
            return true;
        }
        return false;
    }

    public boolean editBiz(Biz biz) {
        String sql="UPDATE `onlineOrder`.`bizS` SET `bizName` = ?  ,`bizAddr` = ? ,`bizStatus` = ? WHERE `bizId` = ?";
        int update = template.update(sql, biz.getBizName(), biz.getBizAddr(), biz.getBizStatus(), biz.getBizId());
        if (update==1){
            return true;
        }
        return false;

    }

    public Biz login(Biz loginbiz) {
        try {
            //1.编写sql
            String sql="select * from bizS where bizName=? and bizPwd=?";

            //2.调用
            Biz biz = template.queryForObject(sql, new BeanPropertyRowMapper<Biz>(Biz.class)
                    , loginbiz.getBizName(), loginbiz.getBizPwd());

            return biz;
        }catch (DataAccessException e){

            return null;
        }
    }

    public boolean registerBiz(Biz b) {
        //1.根据用户判断是否存在
        Biz biz = findByBizname(b.getBizName());
        System.out.println(biz);
        //判断U是否为Null
        if (biz!=null){
            //用户名存在
            return false;
        }
        //保存用户信息
        sava(b);
        return true;
    }

    private void sava(Biz b) {
        String sql="INSERT INTO `onlineOrder`.`bizS`(`bizName`, `bizPwd`, `bizTel`, `sex`, `bizNickname`, `bizAddr`) VALUES (?,?,?,?,?,?)";
        template.update(sql,b.getBizName(),b.getBizPwd(),b.getBizTel(),b.getSex(),b.getBizNickname(),b.getBizAddr());
    }

    private Biz findByBizname(String bizName) {
        Biz biz=null;
        try {
            String sql="select * from bizS where bizName=?";
            biz = template.queryForObject(sql, new BeanPropertyRowMapper<Biz>(Biz.class), bizName);
            //2.执行sql
        } catch (DataAccessException e) {

        }
        System.out.println(biz);
        return biz;
    }

    public List<reviews> finReviews(String bizId) {
        String sql="SELECT\n" +
                "\torderList.reviewsLv, \n" +
                "\torderList.reviewsInfo, \n" +
                "\torderList.foodId, \n" +
                "\tfood.foodName, \n" +
                "\torderList.foodNum, \n" +
                "\t`order`.orderInfo, \n" +
                "\t`order`.bizId,orderList.bizReply\n" +
                "FROM\n" +
                "\torderList\n" +
                "\tINNER JOIN\n" +
                "\tfood\n" +
                "\tON \n" +
                "\t\torderList.foodId = food.foodId\n" +
                "\tINNER JOIN\n" +
                "\t`order`\n" +
                "\tON \n" +
                "\t\torderList.orderInfo = `order`.orderInfo\n" +
                "WHERE\n" +
                "\torderList.reviewsStatus = 1 AND\n" +
                "\t`order`.bizId = ?";
        List<reviews> b=template.query(sql,new BeanPropertyRowMapper<reviews>(reviews.class),bizId);
        return b;
    }


    //商家修改自己的信息
    public boolean changeBizInfo(Biz biz) {
        String sql="UPDATE `onlineOrder`.`bizS` SET `bizName` = ?, `bizPwd` = ?, `bizTel` = ?, `bizNickname` = ? WHERE `bizId` = ?";
        int update = template.update(sql, biz.getBizName(),biz.getBizPwd(),biz.getBizTel(),biz.getBizNickname(),biz.getBizId());
        if (update==1){
            return true;
        }
        return false;
    }
    //
    public Biz checkBizName(String bizName) {
        try {
            //1.编写sql
            String sql="SELECT * FROM bizS WHERE bizName = ?";

            //2.调用
            Biz biz = template.queryForObject(sql, new BeanPropertyRowMapper<Biz>(Biz.class)
                    , bizName);
            return biz;
        }catch (DataAccessException e){
            return null;
        }
    }

    public List<report_biz> report_for_biz(String bizId) {
        try {
            //1.编写sql
            String sql="SELECT\n" +
                    "\treport_biz.*\n" +
                    "FROM\n" +
                    "\treport_biz\n" +
                    "WHERE\n" +
                    "\treport_biz.bizId = ?\n" +
                    "\n" +
                    "\tlimit 7";

            //2.调用
            List<report_biz> biz = template.query(sql, new BeanPropertyRowMapper<report_biz>(report_biz.class)
                    , bizId);

            return biz;
        }catch (DataAccessException e){
            return null;
        }
    }
}

