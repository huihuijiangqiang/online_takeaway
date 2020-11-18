package dao.impl;

import dao.UserDaoInterface;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

public class UserDaoInterfaceImpl implements UserDaoInterface {
    //1.声明成员变量
    private JdbcTemplate template=new JdbcTemplate(JDBCUtil.getDataSource());
    @Override
    public List<User> findAll() {
        //1.定义Sql
        String sql="select * from user ";
        //2.调用
        List<User> u=template.query(sql,new BeanPropertyRowMapper<User>(User.class));
        return u;
    }
}
