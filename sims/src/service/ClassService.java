package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.MyClass;
import bean.PageInfo;
import dao.ClassDao;
import util.JDBCUtil;

//对主要涉及用户表处理逻辑的服务
public class ClassService {
	// 判断班级是否存在, 正确返回true，错误返回false
	public boolean isClassExist(Integer classId, JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		if (classDao.findById(classId, jdbc) == null) {
			return false;
		}
		return true;
	}

	// 根据id获取班级表所有数据
	public MyClass findById(int id, JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		return classDao.findById(id, jdbc);
	}

	// 根据年级获取班级表所有数据
	public ArrayList<MyClass> findByGrade(int grade, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		return classDao.findByGrade(grade, pageInfo, jdbc);
	}

	// 根据班名获取班级表所有数据
	public ArrayList<MyClass> findByTtile(String title, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		return classDao.findByTitle(title, pageInfo, jdbc);
	}

	// 根据获取班级表所有数据
	public ArrayList<MyClass> findAll(JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		return classDao.findByTitle("", jdbc);
	}

	// 更新班级表的数据
	public void update(MyClass myClass, JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		classDao.update(myClass, jdbc);
	}

	// 添加班级表信息
	public void save(MyClass myClass, JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		classDao.save(myClass, jdbc);
	}

	// 删除班级表信息
	public void delete(int id, JDBCUtil jdbc) throws SQLException {
		ClassDao classDao = new ClassDao();
		classDao.deleteById(id, jdbc);
	}
}
