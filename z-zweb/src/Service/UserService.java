package Service;


import java.util.List;

import javax.mail.MessagingException;

import Mail.SendMail;
import Dao.HibnDao;
import bean.User;

public class UserService {

	//保存User
	 public void add(User user)
	    {
	        
	         new HibnDao().insert(user);
	    }
	 
	 //账号查询
 public int reg_select_e(User user){
		 
		 
		 return  new HibnDao().emlSelect(user);
	 }
	 
	 
 public int reg_select_n(User user){
	 
	 
	 return  new HibnDao().ninSelect(user);
	 
 }
	 //登陆查询
	 public String login_select(User user){
		 
		 
		 return  new HibnDao().pwdSelect(user);
	 }

	 public int checking(User user) {
		
		 return new HibnDao().checking(user);
	}
	 
  
    }
	

