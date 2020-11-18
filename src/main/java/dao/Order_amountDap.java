package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Order_amount;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;
import util.JDBCUtil;
import util.JedisUtil;

import java.util.List;

public class Order_amountDap {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());
    public List<Order_amount> selectAll (){

        //1.编写sql
        String sql="SELECT * FROM Order_amount";
        //2.调用
        List<Order_amount> o=template.query(sql,new BeanPropertyRowMapper<Order_amount>(Order_amount.class));

        return o;
    }

    public String findAllJson() {
        //1.从redis中查询数据
        Jedis jedis= JedisUtil.getJedis();
        jedis.auth("wang123#");
        String order_amount_json = jedis.get("order_amount");
        //2.判断是否为null
        if(order_amount_json==null){

            //redis中没有数据
            //2.1从数据库中查询
            List<Order_amount> us = selectAll();
            //2.2将list序列化
            ObjectMapper mapper=new ObjectMapper();
            try {
                order_amount_json=  mapper.writeValueAsString(us);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json存入

            jedis.setex("order_amount",7200,order_amount_json);
            //归还连接

            jedis.close();
        }else {

        }

        return order_amount_json;
    }


}
