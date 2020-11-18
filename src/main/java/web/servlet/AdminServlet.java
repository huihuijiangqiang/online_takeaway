package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Order_amountDap;
import dao.adminDao;
import dao.report_formsDao;
import domain.Admin;
import domain.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AdminServlet/*")
public class AdminServlet extends BaseServlet {
    /**
     * 查询登录用户名
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void finAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //从Session中获取登录用户
        Object admin=request.getSession().getAttribute("admin");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),admin);

    }

    /**
     * 查询管理员列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void FindAllAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        adminDao dao1=new adminDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectAll());
        response.getWriter().write(json1);
    }

    /**
     * 管理员登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void AdminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        //3.创建User对象
        Admin loginadmin=new Admin();
        //3.2使用beanUtils封装
        try {
            BeanUtils.populate(loginadmin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用dao的方法
        adminDao dao=new adminDao();
        Admin admin=dao.login(loginadmin);
        ResultInfo info=new ResultInfo();
        if (admin==null){
            //登录失败 用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("登录失败");
        }
        //登录成功
        if(admin!=null) {
            request.getSession().setAttribute("admin",admin);
            info.setFlag(true);

        }
        //相应数据
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    /**
     * 管理员退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void AdminQuit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁Session
        request.getSession().invalidate();
        //2.跳转
        response.sendRedirect(request.getContextPath()+"/oAdmin/login.html");
    }

    /**
     * 订单金额分布
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void order_amount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //response.setContentType("text/html;charset=utf-8");
        Order_amountDap r=new Order_amountDap();
        ObjectMapper mapper=new ObjectMapper();
        //String json1 = mapper.writeValueAsString(r.selectAll());
        String json1 = r.findAllJson();

        response.getWriter().write(json1);
    }

    /**
     * 最新一周新增用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void report_forms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        report_formsDao r=new report_formsDao();
        ObjectMapper mapper=new ObjectMapper();
//        String json1 = mapper.writeValueAsString(r.selectAll());
        response.getWriter().write(r.findAllJson());
    }
    //cheackAdminName

    /**
     * 更改用户姓名 判断是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String adminName=request.getParameter("adminName");
        adminDao dao=new adminDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao.checkAdminName(adminName));
        response.getWriter().write(json1);
    }
    //changeAdminInfo

    /**
     * 更改用户信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void changeAdminInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        //3.创建User对象
        Admin admin=new Admin();
        //3.2使用beanUtils封装
        try {
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        adminDao dao=new adminDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao.changeAdminInfo(admin));
        response.getWriter().write(json1);
    }
}
