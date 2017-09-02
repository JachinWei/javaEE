package Mail;

import javax.mail.MessagingException;

import Mail.SendMail;

public class Send {
	
	public  Send() {
		
	 send();
	}
	public void send() {
		 try {  
	            new SendMail().sendMessage("smtp.sina.com", "hl215813615@sina.com",  
	                    "zhang3337439", "13631788569@163.com", "nihao",  
	                    "---------------wrwe-----------",  
	                    "text/html;charset=gb2312");  
	        } catch (MessagingException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    } 
	 }  
	

