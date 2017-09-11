package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.PageInfo;
import bean.Student;
import dao.StudentDao;
import util.JDBCUtil;

//对涉及学生表处理逻辑的服务
public class StudentService {
	// 判断学生是否存在, 正确返回true，错误返回false
	public boolean isStuNumberExist(String stuNumber, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		if (stuDao.findByStuNumber(stuNumber, jdbc) == null) {
			return false;
		}
		return true;
	}

	// 判断学生是否已经注册
	public boolean isRegister(String stuNumber, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		Student student = stuDao.findByStuNumber(stuNumber, jdbc);
		if (student != null) {
			if (student.getUid() != 0) {
				return true;
			}
		}
		return false;
	}

	// 学生表与用户表关联
	public void updateUid(int uid, String stuNumber, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		Student stu = stuDao.findByStuNumber(stuNumber, jdbc);
		stu.setUid(uid);
		stuDao.update(stu, jdbc);
	}

	// 根据uid获取学生表所有数据
	public Student findByUid(int uid, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		Student stu = stuDao.findByUid(uid, jdbc);
		return stu;
	}

	// 根据id获取学生表所有数据
	public Student findById(int id, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		return stuDao.findById(id, jdbc);
	}

	// 根据学号获取学生表所有数据
	public Student findByStuNumber(String stuNumber, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		Student stu = stuDao.findByStuNumber(stuNumber, jdbc);
		return stu;
	}

	// 根据班级号获取学生表所有数据
	public ArrayList<Student> findByClassId(int classId, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		return stuDao.findByClassId(classId, pageInfo, jdbc);
	}

	// 更新学生表的数据
	public void update(Student stu, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		stuDao.update(stu, jdbc);
	}

	// 添加学生表信息
	public void save(Student stu, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		stuDao.save(stu, jdbc);
	}

	// 删除学生表信息
	public void delete(int id, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		stuDao.deleteById(id, jdbc);
	}

}
