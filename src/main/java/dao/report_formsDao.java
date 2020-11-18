package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.report_forms;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;
import util.JDBCUtil;
import util.JedisUtil;

import java.util.List;

public class report_formsDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());
    public List<report_forms> selectAll (){

        //1.编写sql
        String sql="SELECT * FROM (select *  from report_forms ORDER BY rtime  desc limit 20)aa ORDER BY rtime";
        //2.调用
        List<report_forms> o=template.query(sql,new BeanPropertyRowMapper<report_forms>(report_forms.class));

        return o;
    }
    public String findAllJson() {
        //1.从redis中查询数据
        Jedis jedis= JedisUtil.getJedis();
        jedis.auth("wang123#");
        String report_forms_json = jedis.get("report_forms");
        //2.判断是否为null
        if(report_forms_json==null){
            System.out.println("无数据");
            //redis中没有数据
            //2.1从数据库中查询
            List<report_forms> us = selectAll();
            //2.2将list序列化
            ObjectMapper mapper=new ObjectMapper();
            try {
                report_forms_json=  mapper.writeValueAsString(us);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json存入

            jedis.setex("report_forms",7200,report_forms_json);
            //归还连接

            jedis.close();
        }else {
            System.out.println("有数据");
        }

        return report_forms_json;
    }
}
