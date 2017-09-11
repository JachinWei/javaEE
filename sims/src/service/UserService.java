package service;

import java.sql.SQLException;

import bean.User;
import dao.UserDao;
import util.JDBCUtil;

//对主要涉及用户表处理逻辑的服务
public class UserService {
	//判断用户是否存在, 正确返回true，错误返回false
	public boolean isUserExist(String username, JDBCUtil jdbc) throws SQLException{
		UserDao userDao = new UserDao();
		if(userDao.findByUsername(username, jdbc) == null){
			return false;
		}
		return true;
	}
	
	//判断用户登录信息是否正确, 正确返回User，错误返回null
	public User login(String username, String password, JDBCUtil jdbc) throws SQLException{
		UserDao userDao = new UserDao();
		User user = userDao.findByUsername(username, jdbc);
		if(user != null){
			if(password != null && password.equals(user.getPassword())){
				return user;
			}
		}
		return null;
	}
	
	//用户与学号做绑定
	public void register(String username, String password, String stuNumber, JDBCUtil jdbc) throws SQLException{
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPowerLevel(3);
		
		UserDao userDao = new UserDao();
		userDao.save(user, jdbc);
		user = userDao.findByUsername(username, jdbc);
		
		StudentService stuService = new StudentService();
		stuService.updateUid(user.getId(), stuNumber, jdbc);
	}
	
	//根据username获取所有数据
	public User findByUsername(String username, JDBCUtil jdbc) throws SQLException{
		UserDao userDao = new UserDao();
		User user = userDao.findByUsername(username, jdbc);
		return user;
	}
	
	//修改密码
	public void updatePwd(String password, String username, JDBCUtil jdbc) throws SQLException{
		if(password != null){
			UserDao userDao = new UserDao();
			User user = userDao.findByUsername(username, jdbc);
			user.setPassword(password);
			userDao.update(user, jdbc);
		}
	}
	
}
