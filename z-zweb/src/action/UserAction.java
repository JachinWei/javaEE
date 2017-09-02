package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ModelDriven;




import Dao.HibnDao;
import Mail.Send;
import Service.UserService;
import bean.User;

public class UserAction implements ModelDriven<User>{
	

	    //域模型方式
	    private User user= new User();
	    
	    public User getUser() {
	        return user;
	    }

	    @Override
		public User getModel() {
			// TODO Auto-generated method stub
			return user;
		}
	 
	    //注册
	    public String register()
	    {
	        String rtn ="fail";
	        int flag=0;
	        int flags=0;
	        int flagss=0;
	        try{
	        	 System.out.println(user);
	        	
	        	// 调用Service层（模型层，业务逻辑层）保存
	        	flag= new UserService().reg_select_e(user);
	        	
	        	flags= new UserService().reg_select_n(user);
	        	
	        	flagss=new UserService().checking(user);
	        	
	        	new Send();
	        	
	        	if(flag==1 &&flags==1 && flagss==1){
	        	
	        	 new UserService().add(user);
	        	  
	        	 rtn="success";
	        	}
	    
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        return rtn;
	    }
	  

		//邮箱认证
	 public String checkEm(User user){
		 
		 System.out.println(user);
		 
		 return "success";
	 }
	 
	 
	 //登陆认证
	 public String loginUser(){
		 
		 String rtn ="fail";
		 String pwd=null;
		 
		 //System.out.println(user);
		 try{
		//获取查询得到的密码	 
		 pwd=new UserService().login_select(user);
		 
		 //密码是否正确
		 if(user.getPassword().equals(pwd)){
			 
			 rtn="success";
		 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		 return rtn;
	 }
}

