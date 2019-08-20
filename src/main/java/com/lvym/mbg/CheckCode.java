package com.lvym.mbg;
/**
 * 验证码生成类
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CheckCode {

	public String getCheckCode(){
		Random random = new Random();
		String sRand="";
		for (int i=0;i<4;i++){
	    String rand=String.valueOf(random.nextInt(10));
	    sRand+=rand;
		}
	    return sRand;
	}
	
	 public static void main(String[] args) { 
		 String timeStr="2011-10-18 15:24:25"; 
		 DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		 Date date=null; 
		 long timeLong=0; 
		 long timeAdded=0; 
		 try{ 
		 date=df.parse(timeStr); 
		 timeLong=date.getTime(); 
		 System.out.println("long:"+timeLong); 
		 Calendar c=Calendar.getInstance(); 
		 c.setTime(date); 
		 c.add(Calendar.MINUTE,40); 
		 timeAdded=c.getTimeInMillis(); 
		 System.out.println("Added time:"+c.getTime()); 
		 System.out.println("Added 40 minutes:"+timeAdded); 
		 }catch(ParseException e){ 
			 e.printStackTrace(); 
		 } 
		 }
}
