package test;

import java.sql.SQLException;

import org.junit.Test;

import service.StudentService;
import service.UserService;
import util.JDBCUtil;

public class ServiceTest {

	@Test
	public void test(){
		UserService userService = new UserService();
		JDBCUtil jdbc;
		String stuNumber = "201641404417";
		StudentService stuService = new StudentService();
		try {
			jdbc = new JDBCUtil();
			if(stuService.isRegister(stuNumber, jdbc)){
				System.out.println("学号已注册");
				return;	
			}
			if(userService.isUserExist("cjq17", jdbc)){
				System.out.println("用户已注册");
				return;	
			}
			userService.register("cjq17", "cjq123", stuNumber, jdbc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
