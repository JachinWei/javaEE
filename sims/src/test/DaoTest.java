package test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import bean.Course;
import bean.User;
import dao.CourseDao;
import dao.UserDao;
import util.JDBCUtil;

public class DaoTest {

	@Test
	//对UserDao类进行测试
	public void test() {
		UserDao userDao = new UserDao();
		JDBCUtil jdbc;
		try {
			jdbc = new JDBCUtil();
			System.out.println("存在用户root!测试");
			User user = userDao.findByUsername("root", jdbc);
			if(user != null){
				System.out.println("没错!真的存在用户"+user.getUsername());
			}
			System.out.println("不存在用户ro!测试");
			user = userDao.findByUsername("ro", jdbc);
			if(user == null){
				System.out.println("没错！真的不存在该用户");
			}
			
			
		} catch (SQLException e) {
			System.out.println("数据库异常!");
		}
	}
	
	@Test
	//CourseDao测试
	public void test2() throws SQLException{
		CourseDao demo = new CourseDao();
		JDBCUtil jdbc = new JDBCUtil();
		// 测试添加功能
		Course obj1 = new Course("计算机操作系统", 3);
		Course obj2 = new Course("就业指导", 3);
		demo.save(obj1, jdbc);
		demo.save(obj2, jdbc);

		// 测试查找功能
		ArrayList<Course> arr = demo.findByTitle("就业指导", jdbc);
		for (Course course : arr) {
			System.out.println(course.getId() + " " + course.getTitle() + " " + course.getCredit());
		}

		// 测试修改功能
		arr.get(0).setTitle("就业指导修改版");
		demo.update(arr.get(0), jdbc);
		arr = demo.findByTitle("就业指导", jdbc);
		for (Course course : arr) {
			System.out.println(course.getId() + " " + course.getTitle() + " " + course.getCredit());
		}

		// 测试删除功能
//		int[] id = { arr.get(0).getId() };
//		System.out.println(demo.deleteById(id, jdbc));

	}

}
