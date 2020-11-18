package dao;

import domain.CatList;
import domain.User;
import domain.buyCar;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

public class userDao {

    //声明JDBC Template对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());



    public List<User> selectAll (){

        //1.编写sql
        String sql="select * from user ";
        //2.调用
        List<User> u=template.query(sql,new BeanPropertyRowMapper<User>(User.class));

        return u;
    }
    public void addUser(User user){
        String sql="INSERT INTO `onlineOrder`.`user`(`userName`, `userPwd`, `userTel`,`userAge`,`sex`) VALUES (?, ?, ?,?,?)";
        template.update(sql,user.getUserName(),user.getUserPwd(),user.getUserTel(),user.getUserAge(),user.getSex());
    }
    public void delUser(User user){
        String sql="DELETE FROM `onlineOrder`.`user` WHERE `userId` = ?";
        template.update(sql,user.getUserId());
    }
    public boolean editUser(User user){
        System.out.println(user);
        String sql="UPDATE `onlineOrder`.`user` SET `userName` = ?  ,`userPwd` = ? ,`userTel` = ? ,`userAge`= ?,`userEmail`= ?,`userNickName`= ?,`trueName`= ?,`sex`= ?,`birthday` = ? WHERE `userId` = ?";

        int update = template.update(sql, user.getUserName(), user.getUserPwd(), user.getUserTel(), user.getUserAge(), user.getUserEmail(), user.getUserNickName(), user.getTrueName(), user.getSex(), user.getBirthday(), user.getUserId());
        if (update==1){
            return true;
        }
        return false;
    }

    /***
     * z注册用户
     * @param user
     * @return
     */
    public boolean registerUser(User user) {
        //1.根据用户判断是否存在
        User u = findByUsername(user.getUserName());
        //判断U是否为Null
        if (u!=null){
            //用户名存在
            return false;
        }
        //保存用户信息
        sava(user);
        return true;
    }
    private  User findByUsername (String userName){
        User user=null;

        //1定义sql
        try {
            String sql="select * from user where userName=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);
            //2.执行sql
        } catch (DataAccessException e) {

        }

        return user;
    }
    private void sava(User user){
        //1定义sql
        String sql="INSERT INTO `onlineOrder`.`user`(`userName`, `userPwd`, `userEmail`,`userNickName`,`userTel`,`sex`,`birthday`) VALUES (?, ?, ?,?,?,?,?)";
        //执行
        template.update(sql,user.getUserName(),
                user.getUserPwd(),

                user.getUserEmail(),
                user.getUserNickName(),
                user.getUserTel(),
                user.getSex(),
                user.getUserAge());
    }

    public User login(User loginuser) {
        try {
            //1.编写sql
            String sql="select * from user where userName=? and userPwd=?";

            //2.调用
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class)
                    , loginuser.getUserName(), loginuser.getUserPwd());

            return user;
        }catch (DataAccessException e){

            return null;
        }
    }
    public List<CatList> selectCar(CatList catList){
        //1.编辑sql
        String sql="SELECT\n" +
                "\tfood.foodName, \n" +
                "\tfood.foodPrice, \n" +
                "\tbuyCar.foodNum, \n" +
                "\tbizS.bizName, \n" +
                "\tbuyCar.userId, \n" +
                "\tfood.foodPic, buyCar.listId,food.bizId,\n" +
                "\tbuyCar.foodAddtime\n" +
                "FROM\n" +
                "\tfood\n" +
                "\tINNER JOIN\n" +
                "\tbizS\n" +
                "\tON \n" +
                "\t\tfood.bizId = bizS.bizId\n" +
                "\tINNER JOIN\n" +
                "\tbuyCar\n" +
                "\tON \n" +
                "\t\tbuyCar.foodId = food.foodId\n" +
                "WHERE\n" +
                "\tbuyCar.userId = ?\n" +
                "ORDER BY\n" +
                "\tbuyCar.foodAddtime DESC";
        List<CatList> u=template.query(sql,new BeanPropertyRowMapper<CatList>(CatList.class),catList.getUserId());
        return u;

    }

    public boolean addToCar(buyCar c) {
        /**
         * 判断商品是否存在
         */
        buyCar buyCar = findBuy(c.getUserId(),c.getFoodId());
        String sql="INSERT INTO `onlineOrder`.`buyCar`(`userId`, `foodId`, `foodNum`) VALUES (?,?,?)";
        String sql1="UPDATE `onlineOrder`.`buyCar` SET `foodNum` = ? WHERE `userId` = ? AND `foodId`=?";

        if (buyCar==null) {
            System.out.println("没有这个用户");
            System.out.println(c);
            int flag= template.update(sql,c.getUserId(),c.getFoodId(),c.getFoodNum());
            if (flag==1){
                return true;
            }
        }else {

            System.out.println("有数据");
            int i = buyCar.getFoodNum() + c.getFoodNum();
            int flag= template.update(sql1,i,c.getUserId(),c.getFoodId());
            if (flag==1){
                return true;
            }
        }
        //1.添加CarList

        return false;
    }

    private buyCar findBuy(int userId, int foodId) {
        buyCar bc=null;
        String sq="SELECT\n" +
                "\tbuyCar.*\n" +
                "FROM\n" +
                "\tbuyCar\n" +
                "WHERE\n" +
                "\tbuyCar.userId = ?\n" +
                " AND\n" +
                "\tbuyCar.foodId = ?";
        try {
            String sql="select * from user where userName=?";
            bc = template.queryForObject(sq, new BeanPropertyRowMapper<buyCar>(buyCar.class), userId,foodId);
            //2.执行sql
        } catch (DataAccessException e) {
        }
        return bc;
    }

    /**
     * 删除购物车商品
     * @param b
     */

    public void delBuyCar(buyCar b) {

        String sql="DELETE FROM `onlineOrder`.`buyCar` WHERE `listId` = ?";
        int update = template.update(sql, b.getListId());

    }

    /**
     * 更改购物车内商品数量
     * @param b
     */

    public void changeBuyCarNum(buyCar b) {
        String sql="UPDATE `onlineOrder`.`buyCar` SET `foodNum` = ? WHERE `listId` = ?";
        template.update(sql,b.getFoodNum(),b.getListId());
    }

    public CatList selectList(int i) {
        CatList c=null;
        String sql="SELECT\n" +
                "\tbuyCar.foodNum, \n" +
                "\tfood.foodName, \n" +
                "\tfood.foodPic, \n" +
                "\tfood.foodPrice, \n" +
                "\tbuyCar.listId, \n" +
                "\tbuyCar.userId, \n" +
                "\tbuyCar.foodId, food.bizId,\n" +
                "\tbizS.bizId\n" +

                "FROM\n" +
                "\tbuyCar\n" +
                "\tINNER JOIN\n" +
                "\tfood\n" +
                "\tON \n" +
                "\t\tbuyCar.foodId = food.foodId\n" +
                "\tINNER JOIN\n" +
                "\tbizS\n" +
                "\tON \n" +
                "\t\tfood.bizId = bizS.bizId\n" +
                "WHERE\n" +
                "\tbuyCar.listId = ?";

        try {
            c = template.queryForObject(sql, new BeanPropertyRowMapper<CatList>(CatList.class), i);
            System.out.println(c);
            //2.执行sql
        } catch (DataAccessException e) {
        }
        return c;
    }

    public User getUserInfo(User user) {
        try {
            //1.编写sql
            String sql="select * from user where userId=?";
            //2.调用
            User u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class)
                    , user.getUserId());
            return u;
        }catch (DataAccessException e){
            return null;
        }

    }

    public int changePassword(User changeUser) {
        String sql="UPDATE `onlineOrder`.`user` SET `userPwd` = ? WHERE `userName` = ?";
        int update = template.update(sql, changeUser.getUserPwd(), changeUser.getUserName());
        return update;
    }



    public User checkUserName(String userName) {
        try {
            //1.编写sql
            String sql="SELECT * FROM user WHERE userName = ?";

            //2.调用
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class)
                    , userName);
            return user;
        }catch (DataAccessException e){
            return null;
        }
    }
}
