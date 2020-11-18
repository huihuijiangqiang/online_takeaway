package dao;


import domain.Order;
import domain.OrderList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

public class orderDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());
    public List<Order> selectAll (){

        //1.编写sql
        String sql="SELECT * FROM `onlineOrder`.`order` ";
        //2.调用
        List<Order> o=template.query(sql,new BeanPropertyRowMapper<Order>(Order.class));

        return o;
    }
    public void delOrder(Order order){
        String sql="DELETE FROM `onlineOrder`.`order` WHERE `orderId` = ? ";
        template.update(sql,order.getOrderId());
    }
    public void addOrder(Order order){

        String sql="INSERT INTO `onlineOrder`.`order`( `userId`, `orderStatus`, `cause`, `bizId`, `payStatus`, `sendStatus`, `foodId`,`orderInfo`,`userAddrId`) VALUES (?,?,?,?,?,?,?,?,?)";
        template.update(sql,order.getUserId(),order.getOrderStatus(),order.getCause(),order.getBizId(),order.getPayStatus(),order.getSendStatus(),order.getFoodId(),order.getOrderInfo(),order.getUserAddrId());
    }

    public List<Order> selectBizAll(Order order) {
        String sql="SELECT * FROM `onlineOrder`.`order` WHERE bizId=? ORDER BY `order`.addTime DESC";
        List<Order> o=template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),order.getBizId());
        return o;
    }

    /**
     * 更改状态
     * @param order
     */
    public void changeSendStatus(Order order) {
        String sql="UPDATE `onlineOrder`.`order` SET `sendStatus` = '已配送' WHERE `orderId` = ?";
        template.update(sql,order.getOrderId());
    }

    public void addoo(OrderList orderList) {
        String sql="INSERT INTO `onlineOrder`.`orderList`(`orderInfo`, `foodId`, `foodNum`, `foodPrice`) VALUES (?, ?, ?, ?)";
        template.update(sql,orderList.getOrderInfo(),orderList.getFoodId(),orderList.getFoodNum(),orderList.getFoodPrice());

    }

    public List<Order> selectUserOrder(Order order) {
        String sql="SELECT * FROM `onlineOrder`.`order` WHERE userId=? ORDER BY `order`.addTime DESC";
        List<Order> o=template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),order.getUserId());
        return  o;


    }

    public List<OrderList> selectUserOrderInfo(OrderList order) {
        String sql="SELECT\n" +
                "\torderList.orderInfo, \n" +
                "\torderList.foodNum, \n" +
                "\torderList.foodId, \n" +
                "\torderList.foodPrice, \n" +
                "\tfood.foodPic, \n" +
                "\tfood.foodName, \n" +
                "\torderList.orderInfoId, \n" +
                "\t`order`.sendStatus \n" +
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
                "\t`order`.orderInfo = ?\n";
        List<OrderList> o=template.query(sql,new BeanPropertyRowMapper<OrderList>(OrderList.class),order.getOrderInfo());
        System.out.println(o);
        return  o;

    }

    public List<Order> selectUserOrderInfoById(Order order) {
        String sql="SELECT * FROM `onlineOrder`.`order` WHERE orderId=?";
        List<Order> o= template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),order.getOrderId());

        return o;
    }
    public int addCommon(OrderList o){
        String sql="UPDATE `onlineOrder`.`orderList` SET `ReviewsLv` = ?, `ReviewsInfo` = ? WHERE `foodId` = ? and `orderInfo`=?";
        int i = template.update(sql, o.getReviewsLv(), o.getReviewsInfo(), o.getFoodId(), o.getOrderInfo());
        return i;
    }

    public boolean AliPaySuccess(OrderList order) {
        String sql="UPDATE `onlineOrder`.`order` SET `orderStatus` = '已支付' WHERE `orderInfo` =? ";
        int update = template.update(sql, order.getOrderInfo());
        if (update==1){
            return true;
        }
        return false;
    }
    //UPDATE `onlineOrder`.`orderList` SET `reviewsLv` = '1', `reviewsInfo` = '1', `reviewsStatus` = 01 WHERE `orderInfoId` = 154
    //更新评论
    public boolean updateReviews(OrderList o){
        String sql="UPDATE `onlineOrder`.`orderList` SET `reviewsLv` = ?, `reviewsInfo` = ?, `reviewsStatus` = 1 WHERE `orderInfo` = ? And `foodId`=?";
        int update = template.update(sql, o.getReviewsLv(), o.getReviewsInfo(), o.getOrderInfo(), o.getFoodId());
        if (update==1){
            return true;
        }
        return false;
    }

    public void upSen(String orderInfo) {
        String sql="UPDATE `onlineOrder`.`order` SET `sendStatus` = '已评论' WHERE `orderInfo` = ?";
        template.update(sql,orderInfo);
    }

    /**
     * 商家回复评论
     * @param order
     * @return
     */
    public int bizRev(OrderList order) {
        String sql="UPDATE `onlineOrder`.`orderList` SET `bizReply` = ? WHERE `orderInfo` = ? And `foodId` = ?";
        int update = template.update(sql, order.getBizReply(), order.getOrderInfo(), order.getFoodId());
        return update;

    }

    public boolean upStatus(Integer orderId) {
        String sql="UPDATE `onlineOrder`.`order` SET `sendStatus` = '已收货' WHERE `orderId` = ?";
        int update = template.update(sql, orderId);
        if (update==1){
            return true;
        }
        return false;
    }
}
