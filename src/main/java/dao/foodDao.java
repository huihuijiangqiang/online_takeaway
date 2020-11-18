package dao;

import domain.Food;
import domain.OrderList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

public class foodDao {

    //声明JDBC Template对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());



    public List<Food> selectAll (){
        //1.编写sql
        String sql="select * from food ORDER BY\n" +
                "\tfood.rank DESC, \n" +
                "\tfood.foodAddtime DESC";
        //2.调用
        List<Food> u=template.query(sql,new BeanPropertyRowMapper<Food>(Food.class));
        System.out.println(u);
        return u;
    }
    public void addFood(Food food){
        String sql="INSERT INTO `onlineOrder`.`food`(`foodName`, `foodPrice`, `foodDescription`,`foodType`,`foodInfo`,`foodPic`,`bizId`) VALUES (?, ?, ?,?,?,?,?)";
        template.update(sql,food.getFoodName(),food.getFoodPrice(),food.getFoodDescription(),food.getFoodType(),food.getFoodInfo(),food.getFoodPic(),food.getBizId());
    }
    public  void delFood(Food food){
        String sql="DELETE FROM `onlineOrder`.`food` WHERE `foodId` = ?";
        template.update(sql,food.getFoodId());
    }
    public boolean editFood(Food food){
        String sql="UPDATE `onlineOrder`.`food` SET `foodName` = ?  ,`foodPrice` = ? ,`foodDescription` = ? ,`foodType` = ?,`bizId` = ?,`foodInfo` = ? WHERE `foodId` = ?";

        int update = template.update(sql, food.getFoodName(), food.getFoodPrice(), food.getFoodDescription(), food.getFoodType(), food.getBizId(), food.getFoodInfo(), food.getFoodId());
        if (update==1){
            return true;
        }
        return false;
    }
    public List<Food> searchFood(Food food){

        String sql="select * from food where foodId=?";
        List<Food> ff = template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),food.getFoodId());
        System.out.println(ff);
        return ff;
    }



    public List<Food> selectBizFood(Food food) {
        String sql="SELECT * FROM `onlineOrder`.`food` WHERE bizId=?";
        List<Food> o=template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),food.getBizId());
        return o;
    }

    /**
     *查询未申请营销的商品
     * @param food
     * @return
     */
    public List<Food> selectNoMarket(Food food) {
        String sql="SELECT * FROM `onlineOrder`.`food` WHERE bizId=? AND testRank=0";
        List<Food> o=template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),food.getBizId());
        return o;
    }


    /**
     * 商家提交申请
     * 设置testRank
     * @param food
     * @return
     */
    public int fMarket(Food food) {
        String sql="UPDATE `onlineOrder`.`food` SET `testRank` = ? WHERE `foodId` = ?";
        int update = template.update(sql, food.getTestRank(), food.getFoodId());
        return update;
    }
    /**
     * 商家查询自己哪些商品进行了营销正在审核中
     * @param food
     * @return
     */
    public List<Food> bizSelectMarket(Food food) {
        String sql="SELECT\n" +
                "\tfood.* \n" +
                "FROM\n" +
                "\tfood \n" +
                "WHERE\n" +
                "\tfood.bizId = ? \n" +
                "\tAND food.testRank > 1";
        List<Food> u=template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),food.getBizId());
        return u;
    }
    /**
     * 商家查询自己哪些商品进行了营销
     * @param food
     * @return
     */
    public List<Food> bizSelectMarketing(Food food) {
        String sql="SELECT\n" +
                "\tfood.* \n" +
                "FROM\n" +
                "\tfood \n" +
                "WHERE\n" +
                "\tfood.bizId = ? \n" +
                "\tAND food.rank > 1";
        List<Food> u=template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),food.getBizId());
        return u;
    }



    /**
     * 管理员同意申请
     *
     */
    public int Market(Food food) {
        String sql="UPDATE `onlineOrder`.`food` SET `rank` = ? ,`testRank` = 0 WHERE `foodId` = ?";

        int update = template.update(sql, food.getRank(), food.getFoodId());
        return update;
    }
    /**
     *
     * 管理员哪些商品申请了营销
     */
    public List<Food> selectMarket(){
        String sql="SELECT\n" +
                "\tfood.*\n" +
                "FROM\n" +
                "\tfood\n" +
                "WHERE\n" +
                "\tfood.testRank > 1";
        List<Food> u=template.query(sql,new BeanPropertyRowMapper<Food>(Food.class));
        return u;
    }


    /**
     * 商家取消商品营销
     *
     */
    public int bizDelMarket(Food food){
        String sql="UPDATE `onlineOrder`.`food` SET `rank` = 1 WHERE `foodId` = ?";
        int update = template.update(sql, food.getFoodId());
        return update;

    }

    public List<Food> USer(Food food) {
        String sql="SELECT\n" +
                "\tonlineOrder.food.*\n" +
                "FROM\n" +
                "\tonlineOrder.food\n" +
                "WHERE\n" +
                "\tonlineOrder.food.foodName LIKE \"%\"?\"%\"";
        List<Food> f=template.query(sql,new BeanPropertyRowMapper<Food>(Food.class),food.getFoodName());
        return f;
    }

    public List<OrderList> fReviews(OrderList o) {
        String sql="SELECT\n" +
                "\torderList.*\n" +
                "FROM\n" +
                "\torderList\n" +
                "WHERE\n" +
                "\torderList.foodId = ? AND\n" +
                "\torderList.ReviewsStatus = 1";
        List<OrderList> f=template.query(sql,new BeanPropertyRowMapper<OrderList>(OrderList.class),o.getFoodId());
        return f;
    }
}
