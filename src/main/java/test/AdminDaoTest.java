package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.*;
import domain.*;
import org.junit.jupiter.api.Test;


public class AdminDaoTest {
    @Test
    public void testlogin(){
        Admin loginadmin=new Admin();
        loginadmin.setAdminName("admin02");
        loginadmin.setAdminPwd("pwdadmin02");
        adminDao dao=new adminDao();
        Admin admin=dao.login(loginadmin);

        System.out.println("111"+admin);
    }
    @Test
    public void  testJson() throws Exception {

        ObjectMapper mapper=new ObjectMapper();
        User user=new User();
        user.setUserName("1321322");
        user.setUserPwd("122345653");
        user.setUserTel("12312323");

        userDao dao1=new userDao();
        dao1.addUser(user);
        String json1 = mapper.writeValueAsString(dao1.selectAll());
        System.out.println("你好"+json1);


    }
    @Test
    public void  testJso1() throws Exception {

//        ObjectMapper mapper=new ObjectMapper();
//        User user=new User();
//        user.setUserId(2);
//
//        userDao dao1=new userDao();
//        dao1.delUser(user);
//        String json1 = mapper.writeValueAsString(dao1.selectAll());
//        System.out.println("你好"+json1);

        bizDao bizDao=new bizDao();

        System.out.println(bizDao.selectAll());

    }
    @Test
    public void  testJso2() throws Exception {

//        orderDao orderDao=new orderDao();
//        System.out.println(orderDao.selectAll());
        adminDao dao=new adminDao();
        System.out.println(dao.selectAll());
    }
    @Test
    public void  testJso3() throws Exception {

        foodDao dao=new foodDao();
        dao.selectAll();
    }
    @Test
    public void  testJso4() throws Exception {

        buyCar b=new buyCar();
        b.setUserId(1);
        b.setFoodId(10002);
        b.setFoodNum(1);
        userDao u=new userDao();
        u.addToCar(b);



    }
    @Test
    public void  testJso5() throws Exception {
        Order o=new Order();
        o.setOrderId(1000072);
        orderDao od=new orderDao();

        System.out.println(od.selectUserOrderInfoById(o));



    }
}
