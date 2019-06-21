package com.whpu.onlineShoppingMall.utils;

import java.io.Serializable;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Properties;  
 
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;  
import org.hibernate.MappingException;  
import org.hibernate.dialect.Dialect;  
import org.hibernate.engine.SessionImplementor;  
import org.hibernate.id.Configurable;  
import org.hibernate.id.IdentifierGenerator;  
import org.hibernate.type.Type;  

public class KeyUtils implements IdentifierGenerator, Configurable {
	
	private static final int IDLENG = 8;  
    private static final String DDHHMMSS = "ddhhmmss";  
      
    private static String getCurrentDate(){  
        return new SimpleDateFormat(DDHHMMSS).format(new Date());  
    }  
  
    public Serializable  generate(SessionImplementor session, Object obj) throws HibernateException {              
        return  new StringBuilder().append(getCurrentDate()).append(RandomStringUtils.randomNumeric(IDLENG));  
    }  
  
    public void configure(Type type, Properties params, Dialect d) throws MappingException {  
          
    }  
    
    public static void main(String[] args) {
    	
    	for(int i=0;i<10;i++){
    		String newid = new SimpleDateFormat("ddhhmmss").format(new Date())+((int)(Math.random()*9000)+1000);
        	System.out.println(newid);
    	}
        /*int random = 32; 
        System.out.println(RandomStringUtils.randomNumeric(random)); 
        System.out.println(RandomStringUtils.randomAscii(random)); 
        System.out.println(RandomStringUtils.randomAlphabetic(random)); 
        System.out.println(RandomStringUtils.randomAlphanumeric(random));*/  
          
    }  
}
