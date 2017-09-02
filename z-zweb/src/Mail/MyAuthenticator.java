package Mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


class MyAuthenticator extends Authenticator{  
    String userName="hl215813615@sina.com";  
    String password="zhang3337439";  
    public MyAuthenticator(){  
          super();
    }  
    public MyAuthenticator(String userName,String password){  
    	super();
        this.userName=userName;  
        this.password=password;  
    }  
     protected PasswordAuthentication getPasswordAuthentication(){     
        return new PasswordAuthentication(userName, password);     
     }   
}