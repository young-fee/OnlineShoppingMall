package com.whpu.onlineShoppingMall.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
* @date: 2019-3-23 
* @author: yangtz
* @title: MailUtils 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class MailUtils {

	/**
	 * 邮件发送
	* @date: 2019-3-23 
	* @author: yangtz
	* @title: sendMail 
	* @param receive  收件人
	* @param code  激活码
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public static void sendMail(String receive,String code){
		
		//获得链接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("service@shop.com", "service");
			}
		});
		//创建一个邮件对象
		Message message = new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress("service@shop.com"));
			//设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(receive));
			//设置主题
			message.setSubject("网上商城官方激活邮件");
			//设置正文
			message.setContent("<h1>您正在注册网上商城账号，点下面链接完成验证！</h1><h3><a href='http://127.0.0.1:8081/OnlineShoppingMall/user_active.action?code="+code+"'>http://127.0.0.1:8081/OnlineShoppingMall/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//发送
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		sendMail("test@shop.com", "123456798");
	}
}
