package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.foodDao;
import domain.Food;
import domain.OrderList;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/FoodServlet/*")
public class FoodServlet extends BaseServlet {
    /**
     * 管理员查询所有food
     * 用户查询所有food
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void FindAllFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        foodDao dao1=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectAll());
        response.getWriter().write(json1);
    }
    /**
     * 管理员编辑food
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao foodDao=new foodDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(foodDao.editFood(food));
        response.getWriter().write(json1);
    }

    /**
     * 删除食品信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao foodDao=new foodDao();
        foodDao.delFood(food);
    }

    /**
     * 前端用户查询商品详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void foodSe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao foodDao=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(foodDao.searchFood(food));
        response.getWriter().write(json1);
    }

    /**
     * 商家查询食品列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void foodList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("foodList");
        response.setContentType("text/html;charset=utf-8");
        foodDao dao1=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectAll());
        response.getWriter().write(json1);
    }

    /**
     * 查询商家食品列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void FindBizFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao dao1=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectBizFood(food));
        response.getWriter().write(json1);
    }
    public void addFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
            System.out.println(food);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ff.addFood(food);
    }
    /**
     * 商家查询哪些商品没有申请
     */
    public void bizSelectNoMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.selectNoMarket(food));
        response.getWriter().write(json1);
    }

    /**
     *商家申请营销
     * marketing;
     */
    public void foodMarketing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
            System.out.println(food);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString( ff.fMarket(food));
        response.getWriter().write(json1);
    }
    /**
     * 商家查询哪些商品正在进行审核
     *
     */
    public void BizSelectMarketing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.bizSelectMarket(food));
        response.getWriter().write(json1);
    }
    /**
     * 商家查询哪些商品通过了申请
     *
     */
    public void BizSelectMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.bizSelectMarketing(food));
        response.getWriter().write(json1);
    }
    /**
     *管理员同意营销
     * marketing;
     */
    public void Marketing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
            System.out.println(food);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.Market(food));
        response.getWriter().write(json1);
    }
    /**
     * 管理员查询哪些商品申请了营销
     */
    public void AdminSelectMarketing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
            System.out.println(food);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.selectMarket());
        response.getWriter().write(json1);
    }

    /**
     * 商家取消营销
     *
     */
    public void BizDelMarketing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.bizDelMarket(food));
        response.getWriter().write(json1);
    }

    /**
     * 用户查询商品
     */

    public void USer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Food food=new Food();
        try {
            BeanUtils.populate(food,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.USer(food));
        response.getWriter().write(json1);
    }

    /**
     * 显示商品评价
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void FoodReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        OrderList o=new OrderList();
        try {
            BeanUtils.populate(o,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        foodDao ff=new foodDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(ff.fReviews(o));
        response.getWriter().write(json1);
    }
}
