package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.bizDao;
import domain.Biz;
import domain.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/BizServlet/*")
public class BizServlet extends BaseServlet {
    /**
     * 管理员查询商家列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllBiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        bizDao dao1=new bizDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectAll());
        response.getWriter().write(json1);
    }

    /**
     * 管理员添加商家
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addBiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Biz biz=new Biz();
        try {
            BeanUtils.populate(biz,map);
            System.out.println(biz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        bizDao bizDao=new bizDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(bizDao.addBiz(biz));
        response.getWriter().write(json1);
    }

    /**
     * 管理员编辑商家
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editBiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Biz biz=new Biz();
        try {
            BeanUtils.populate(biz,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        bizDao bizDao=new bizDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(bizDao.editBiz(biz));
        response.getWriter().write(json1);

    }

    /**
     * 管理员删除商家
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delBiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Biz biz=new Biz();
        try {
            BeanUtils.populate(biz,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        bizDao bizDao=new bizDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(bizDao.delBiz(biz));
        response.getWriter().write(json1);
    }

    /**
     * 商家登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void BizLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        //3.创建User对象
        Biz loginbiz=new Biz();
        //3.2使用beanUtils封装
        try {
            BeanUtils.populate(loginbiz,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用dao的方法
        bizDao dao=new bizDao();
        Biz biz=dao.login(loginbiz);
        ResultInfo info=new ResultInfo();
        if (biz==null){
            //登录失败 用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("登录失败，用户名或密码错误");
        }
        if (biz!=null&&biz.getBizStatus().equals("0")){
            info.setFlag(false);
            info.setErrorMsg("未激活，请等待管理员激活账户");
        }
        //登录成功
        if(biz!=null&&biz.getBizStatus().equals("1")) {
            request.getSession().setAttribute("biz",biz);
            info.setFlag(true);

        }
        //相应数据
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    /**
     * 查找商家名称
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void finBizName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //从Session中获取登录用户
        Object biz=request.getSession().getAttribute("biz");
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),biz);

    }

    /**
     * 商家退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void BizQuit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁Session
        request.getSession().invalidate();
        //2.跳转
        response.sendRedirect(request.getContextPath()+"/oBusiness/login.html");
    }
    public void registerBiz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        Biz b=new Biz();
        try {
            BeanUtils.populate(b,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用
        bizDao bizDao=new bizDao();
        boolean flag= bizDao.registerBiz(b);
        ResultInfo info=new ResultInfo();
        if (flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("用户已存在！");
        }
        //将info对象转化为对象
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json对象写回输出流
        response.getWriter().write(json);
    }
    public void finReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String bizId=request.getParameter("bizId");
        bizDao dao1=new bizDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.finReviews(bizId));
        response.getWriter().write(json1);

    }

    /**
     * 更改用户信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void changeBizInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        //3.创建User对象
        Biz biz=new Biz();
        //3.2使用beanUtils封装
        try {
            BeanUtils.populate(biz,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        bizDao dao=new bizDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao.changeBizInfo(biz));
        response.getWriter().write(json1);
    }
    /**
     * 更改用户姓名 判断是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void checkBizName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String bizName=request.getParameter("bizName");

        bizDao dao=new bizDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao.checkBizName(bizName));
        response.getWriter().write(json1);
    }
    public void report_for_biz(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String bizId=request.getParameter("bizId");

        bizDao dao=new bizDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao.report_for_biz(bizId));
        response.getWriter().write(json1);
    }
}
