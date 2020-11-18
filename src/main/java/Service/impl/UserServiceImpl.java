package Service.impl;

import Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDaoInterface;
import dao.impl.UserDaoInterfaceImpl;
import domain.User;
import redis.clients.jedis.Jedis;
import util.JedisUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoInterface dao=new UserDaoInterfaceImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public String findAllJson() {
        return null;
    }

    /**
     * 使用Redis缓存
     * @return
     */
//    @Override
//    public String findAllJson() {
//        //1.从redis中查询数据
//        Jedis jedis= JedisUtil.getJedis();
//        jedis.auth("wang123#");
//        String user_json = jedis.get("user");
//        //2.判断是否为null
//        if(user_json==null){
//            System.out.println("无数据");
//            //redis中没有数据
//            //2.1从数据库中查询
//            List<User> us = dao.findAll();
//            //2.2将list序列化
//            ObjectMapper mapper=new ObjectMapper();
//            try {
//                user_json=  mapper.writeValueAsString(us);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            //将json存入
//            jedis.set("user",user_json);
//            //归还连接
//            jedis.close();
//        }else {
//            System.out.println("有数据");
//        }
//
//        return user_json;
//    }
}
