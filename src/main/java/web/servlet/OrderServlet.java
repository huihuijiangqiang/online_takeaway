package web.servlet;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.orderDao;
import domain.AlipayConfig;
import domain.Order;
import domain.OrderList;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/OrderServlet/*")
public class OrderServlet extends BaseServlet {
    /**
     * 管理员查询全部订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void FindAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectAll());
        response.getWriter().write(json1);
    }

    /**
     * 管理员添加订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Order order=new Order();
        try {
            BeanUtils.populate(order,map);
            System.out.println(order);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao orderDao=new orderDao();
        orderDao.addOrder(order);
    }
    /**
     * 用户下单userAddOrder
     */
    public void userAddOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Order order=new Order();
        try {
            BeanUtils.populate(order,map);
            System.out.println(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao orderDao=new orderDao();
        orderDao.addOrder(order);
    }

    /**
     * 用户生成OrderList
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void userAddOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        String orderList = request.getParameter("orderList");

        System.out.println(orderList);
        JSONArray json = JSONArray.fromObject(orderList);//userStr是json字符串

        List<OrderList> myclasses = (List<OrderList>)JSONArray.toCollection(json,OrderList.class);
        Object[] o=myclasses.toArray();
        orderDao od=new orderDao();
        System.out.println(myclasses);
        for (int i = 0; i <o.length ; i++) {
            OrderList or=(OrderList)o[i];
            od.addoo(or);
        }


    }

    /**
     * 管理员编辑订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public void changeSendStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Order order=new Order();
        try {
            BeanUtils.populate(order,map);
            System.out.println(order);


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao orderDao=new orderDao();
        orderDao.changeSendStatus(order);
    }
    /***
     * 管理员删除订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Order order=new Order();
        System.out.println(map);
        try {
            BeanUtils.populate(order,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao orderDao=new orderDao();
        orderDao.delOrder(order);
    }
    /**
     * 商家查询自己的订单
     */

    public void FindBizOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Order order=new Order();
        System.out.println(map);
        try {
            BeanUtils.populate(order,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectBizAll(order));
        response.getWriter().write(json1);
    }

    /**
     * 用户查询自己的订单
     */
    public void FindUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Order order=new Order();
        System.out.println(map);
        try {
            BeanUtils.populate(order,map);
            System.out.println(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectUserOrder(order));
        response.getWriter().write(json1);
    }
    /**
     *
     * 用户查询订单信息
     *
     * FindUserOrderInfo
     */
    public void FindUserOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        OrderList order=new OrderList();
        System.out.println(map);
        try {
            BeanUtils.populate(order,map);
            System.out.println(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectUserOrderInfo(order));
        response.getWriter().write(json1);
    }

    /**
     * 获取单个订单信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void FindUserOrderInfoById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Order order=new Order();
        System.out.println(order);
        System.out.println(map);
        try {
            BeanUtils.populate(order,map);
            System.out.println(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.selectUserOrderInfoById(order));
        response.getWriter().write(json1);
    }
    /**
     * 添加评论
     addCommon
     */
    public void addCommon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        OrderList order=new OrderList();

        try {
            BeanUtils.populate(order,map);
            System.out.println("--------"+order);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.addCommon(order));
        response.getWriter().write(json1);
    }
    public void Alipay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        response.getWriter().write(result);
    }
//    changeOrderStatus

    /**
     * 支付宝支付成功后更改订单状态
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void changeOrderStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        OrderList order=new OrderList();

        try {
            BeanUtils.populate(order,map);
            System.out.println("--------"+order);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.AliPaySuccess(order));
        response.getWriter().write(json1);
    }
    /**
     * 更新用户评论
     * updateReviews
     */
    public void updateReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        OrderList order=new OrderList();

        try {
            BeanUtils.populate(order,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();

        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.updateReviews(order));
        response.getWriter().write(json1);
    }
    //updateSen

    /**
     * 更新配送状态
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateSen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderInfo=request.getParameter("orderInfo");
        System.out.println(orderInfo);
        orderDao d=new orderDao();
        d.upSen(orderInfo);
    }
    /**
     * bizRev
     * 商家回复评价
     */
    public void bizRev(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        Map<String, String[]> map = request.getParameterMap();
        OrderList order=new OrderList();

        try {
            BeanUtils.populate(order,map);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.bizRev(order));
        response.getWriter().write(json1);
    }
    //upStatus
    public void upStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        Integer orderId=Integer.parseInt(request.getParameter("orderId"));
        orderDao dao1=new orderDao();
        ObjectMapper mapper=new ObjectMapper();
        String json1 = mapper.writeValueAsString(dao1.upStatus(orderId));
        response.getWriter().write(json1);
    }
}
