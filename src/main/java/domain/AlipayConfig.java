package domain;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102400753434";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBeMJYl4pyeJWIMD1mIlj6fD+KZBik3d/amnXD3vHba4/HWc17YZvlCrCISlKufbVm4RC2/QSm5e+naRnwwoCu8ixqmaBT4t0uPhKu2RJsxPLiEpxrHh/v942b/0/O7m01q3bI/YwiskNjx/t1tTN6nU+Us8sOANJvizhuorUzcVIJsNn8sHQv/d9GbfBflFJO4sDqmsE0uX3+mXftaUZYmeyhikHkNH8tqoa799MzvgJXv2alXnW+c3z82+l28V24HkdqzIqqxq0/1jenzeP4ISzOa76jhcEdsljXrvXCsUmO/KktxotGLm/EuLcr1BxdjIlLNYgvMRnlPHyf80O1AgMBAAECggEASmk/OW+PMI9BUc5321VuUjhKxJ0W1Np4J8mSWYqu8m71aZuhT4If+RmVsB8XOhYhxgkgdCsjG1EX1d2//PGsx9+Dy5iT3uKKQGOIlVlJD556AZi0sZPUwzRB/SBVnBbISYpMcOPYQLC3rodnbJVCCvySmmJjF589UsJvIpb9EZ0AqoMADgpigPz9Cz8GMNL0O/3BUsjaj1nij+g9yfH8ydkH6sfmooS7UZqO4VizpSbri7idjz9gyAA2JvCd7ZY6Sq6O9x1uaqq+vRk2v+3IHGB/vU8Lai3Xz4XgAxhx02JCFy89x63HK5ygNDM9uU0wTGYnkN/oAMq+xu7IUr3BmQKBgQDbJtgB8xGN2qikrfoMQN53JODJwhDEIGQhHHQsSHviu0gu0AMNPNUgcH1TXXZEA5gqg+Std9bnNFnKntyWnZ9zHJBhn2uLwycPW6kDGjkeAbUNRI7JSd6R23FOB1Gn5xnv/I0Djlz2adh+ABHFuzKof2bCj6NxMtxPvaZVrjsuNwKBgQCXPbuyQU8TtikT/UXQaTFwHIzu2Wl6a6qepgSGQbZb6IgPtHeazbWloAGoke6OX5x+N5Kd1E+s1Ej8fxdrHH8PZexpzMNAEdl8pLzU51+7hZ9GAiu9TBsQ/burV7HjW0Vtb3coTRTnZwquKezJCZMgzYGnGCu9rIHQKj19LrYHcwKBgQDE/qrHPdb2u6pCtf0CTMFtOiIyWpc1paQ8O1aWg+WQzya4pdVIbflq+pM9f9MSmmSLOxoxcHsmQ+G/CIRjg3bUQ8XpayOCc79Fx/AqUMUtGv3D2m+7swI1/JtiN6QIGO4w0sVzf+JCwqyPvsI7CuYjnrohhHq4A68UovbFKCQciwKBgBvE0O6kwSEsZBRwf14JwnMEOvgr4QsCM7yXXONNs27ingDMMN2ygXSckVFjIfrwISqnh3sFHW/tNk1uBF29SLMKEsNGPl9IJ7eUA7vPk6s5mInhIijKjXmsLMYD/vxwqKm0Opuis4DdUX+y4O/0mxf9w51rebSEtP0ElG+koPVzAoGAW3LSXcUkzQuxXo0RVwBWpJJ4oXT9mPyaOA2snrM/HViIGXDw9wHd248ws4Q+mTOhKxQLyxPVgMO56poPF4CWnkBFSUTfbAluGH1V0CjfYuUlDsTbd3EFz8DAe7hdjtlgYezTPrmX+iYNXiHmR+4STXbpZk4qPh9Y0aZYz9Fmlgo=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvErnytJ88R6q7BOxeW/QHw8/lre1J1FmfAFK2V/BZjWE/EU2jjuJDOPYvWrT3WGkHD2SHNpPGbPCn6EZH0Xr9l6Zqna5+L9veDbSRtXtg1N4tREy62fFDOa8+QqasC18OFftzK2A85yslh4mcxHqAnq3SUtfVMP8gTH01u/bhxp3XIxy2sDy5KFgFe5HQHi/fWTTnTNkFNZmqlFqWGfvayxf6iWedumoTL2LUe1HeyiE1vWDcUdxqK2CNuTBFLv3Av1ZCzoDF+F79ToK8v/SYIO7B3a4NoVP+23ZSmFrs8kBpskbzVQG7F1g3S0v7JPE1SDup8uJwfh88yOEedxyLQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/15_war/oUser/index.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/15_war/oUser/paysucess.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
