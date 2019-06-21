package com.whpu.onlineShoppingMall.utils;

public class AlipayConfig {

	 // 1.商户appid
    public static String APPID = "2016100100638692";    
    
    //2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="";
    
    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiUNwawiF/TUD98AGXHP3Ybjs49sTJvw7i7nEmV5XXrnv1YLUepBYzGz/HXBDjcMhwoCEkvcxzIfBGtdhlFznCI4gSs+q//WUyoLSieY3wTCDcqRnSBZexvCF8bQBO+yJfRqZRDVooeBagpXdmdcUYEHg/dIqRBzzd+UE6Chg1kGfW+YEomnxeH0VuZumeawhHxA1U6E9oNK3xA45sZB35EIakKxTmv0mYPkAulBbqTsLMNpDoidaigdng151S9rb5xfTepXMVV51e1skRxEpjCqjfAzVYoLLztk+NfOeyA6QYs06kY50PrMPV6Ug7cf917r5bmqPv9TXKgHrz3mt0QIDAQAB";
    
    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1:8081/OnlineShoppingMall/order_callBack.action";
    
     //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://127.0.0.1:8081/OnlineShoppingMall/order_callBack.action";
    
    // 6.请求支付宝的网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";    
    
    // 7.编码
    public static String CHARSET = "UTF-8";
    
    // 8.返回格式
    public static String FORMAT = "json";
    
    // 9.加密类型
    public static String SIGNTYPE = "RSA2";

}
