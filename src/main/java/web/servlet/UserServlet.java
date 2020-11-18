package web.servlet;

import Service.UserService;
import Service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserAddrDao;
import dao.userDao;
import domain.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/UserServlet/*")
public class UserServlet<getUserInfo> extends BaseServlet {
    //声明User
    private User user=new User();
    /**
     *添加用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        //User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        userDao.addUser(user);
    }

    /**
     * 删除用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        //User user=new User();
        try {
            BeanUtils.populate(user,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        userDao.delUser(user);
    }

    /**
     * 管理员编辑用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
       // User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        boolean b = userDao.editUser(user);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(String.valueOf(b));
    }

    /***
     * 查询所有用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Service 查询
        UserService service=new UserServiceImpl();
        List<User> lis = service.findAll();
        //2.序列化List为json
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(lis);
//        String json=service.findAllJson();

        //3.响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用
        userDao userD=new userDao();
        boolean flag= userD.registerUser(user);
        ResultInfo info=new ResultInfo();
        if (flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！");
        }
        //将info对象转化为对象
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json对象写回输出流
        response.getWriter().write(json);

    }
    /**
     * 用户登录
     */
    public void UserLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        //3.创建User对象
        User loginuser=new User();
        //3.2使用beanUtils封装
        try {
            BeanUtils.populate(loginuser,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用dao的方法
        userDao dao=new userDao();
        User user=dao.login(loginuser);
        ResultInfo info=new ResultInfo();
        if (user==null){
            //登录失败 用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("登录失败");
        }
        if (user!=null&&user.getUserStatus().equals("0")){
            info.setFlag(false);
            info.setErrorMsg("账号已被禁用");
        }
        //登录成功
        if(user!=null&&user.getUserStatus().equals("1")) {
            request.getSession().setAttribute("user",user);
            info.setFlag(true);

        }
        //相应数据
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        //3.创建User对象
        User changeUser=new User();
        //3.2使用beanUtils封装
        try {
            BeanUtils.populate(changeUser,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用dao的方法
        userDao dao=new userDao();
        int i = dao.changePassword(changeUser);

        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        String json = mapper.writeValueAsString(i);
        //将json对象写回输出流
        response.getWriter().write(json);
    }

    /**
     * 查询登录用户名
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void finUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //从Session中获取登录用户
        User user=new User();
        Object uu=request.getSession().getAttribute("user");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),uu);

    }
    public void UserQuit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁Session
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/oUser/index.html");

    }
    public void UserQuit1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁Session
        request.getSession().invalidate();


    }
    /**
     * 用户查询自己购物车里的商品信息
     */
    public void byCarList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        CatList c=new CatList();
        try {
            BeanUtils.populate(c,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(userDao.selectCar(c));
        response.getWriter().write(json1);
    }
    public void FindUserAddr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        UserAddr c=new UserAddr();
        try {
            BeanUtils.populate(c,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserAddrDao d=new UserAddrDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(d.searchUserAddr(c));
        response.getWriter().write(json1);
    }
    /**
     * 用户像购物车中增加商品
     */
    public void addToCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        buyCar c=new buyCar();
        try {
            BeanUtils.populate(c,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        boolean b = userDao.addToCar(c);
        response.getWriter().write(String.valueOf(b));
    }

    /**
     * 修改购物车内商品的数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delBuyCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        //User user=new User();
        buyCar b=new buyCar();
        try {
            BeanUtils.populate(b,map);


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        userDao.delBuyCar(b);
    }
//    changeBuyCarNum
    public void changeBuyCarNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        //User user=new User();
        buyCar b=new buyCar();
        try {
            BeanUtils.populate(b,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        userDao.changeBuyCarNum(b);
    }
    public void toPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String[] s=request.getParameterValues("foods");
        int[] a =new int[s.length];
        userDao userDao=new userDao();
        CatList bc = null;
        ObjectMapper mapper=new ObjectMapper();
        StringBuilder json1 = new StringBuilder();

        for (int i = 0; i <s.length ; i++) {
            a[i]=Integer.parseInt(s[i]);

            json1.append(mapper.writeValueAsString(userDao.selectList(a[i]))+",");
        }
        String jj="["+json1.substring(0, json1.length() - 1)+"]";
        response.getWriter().write(jj);
    }
//    getUserInfo 获取用户信息
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();

        try {
            BeanUtils.populate(user,map);


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao userDao=new userDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(userDao.getUserInfo(user));
        response.getWriter().write(json1);
    }

    /**
     *
     * 用户增加收货地址
     * saveUserAddr
     */
    public void saveUserAddr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        UserAddr user=new UserAddr();

        try {
            BeanUtils.populate(user,map);
            System.out.println(user);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserAddrDao uDao=new UserAddrDao();

        uDao.addUA(user);

    }
    /**
     *
     * 用户删除收货地址
     * delUserAddr
     */
    public void delUserAddr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        UserAddr user=new UserAddr();

        try {
            BeanUtils.populate(user,map);
            System.out.println(user);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserAddrDao uDao=new UserAddrDao();
        uDao.delUA(user);

    }
    /**
     * 获取地址信息
     */
    public void FindUserAddrById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        UserAddr user=new UserAddr();
        try {
            BeanUtils.populate(user,map);


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserAddrDao uDao=new UserAddrDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(uDao.getUserAddrById(user));
        response.getWriter().write(json1);

    }

    /**
     *
     * 用户增加收货地址
     * saveUserAddr
     */
    public void editUserAddr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        UserAddr user=new UserAddr();

        try {
            BeanUtils.populate(user,map);
            System.out.println(user);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserAddrDao uDao=new UserAddrDao();

        uDao.editUA(user);

    }
    /**
     * 更改用户姓名 判断是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String userName=request.getParameter("userName");
        System.out.println(userName);
        userDao dao=new userDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao.checkUserName(userName));
        response.getWriter().write(json1);
    }
}
