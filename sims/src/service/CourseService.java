package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import bean.Course;
import bean.MyClass;
import bean.PageInfo;
import bean.Score;
import bean.Student;
import dao.ClassDao;
import dao.CourseDao;
import dao.ScoreDao;
import dao.StudentDao;
import util.JDBCUtil;

//对主要涉及课程表处理逻辑的服务
public class CourseService {
	// 根据id获取课程表所有数据
	public Course findById(int id, JDBCUtil jdbc) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findById(id, jdbc);
	}

	// 根据班名获取课程表所有数据
	public ArrayList<Course> findByTtile(String title, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findByTitle(title, pageInfo, jdbc);
	}

	// 根据获取班级表所有数据
	public ArrayList<Course> findAll(JDBCUtil jdbc) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findByTitle("", jdbc);
	}

	// 根据学分获取课程表所有数据
	public ArrayList<Course> findByCredit(int credit, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findByCredit(credit, pageInfo, jdbc);
	}

	// 更新课程表的数据
	public void update(Course course, JDBCUtil jdbc) throws SQLException {
		CourseDao courseDao = new CourseDao();
		courseDao.update(course, jdbc);
	}

	// 添加课程表信息
	public void save(Course course, JDBCUtil jdbc) throws SQLException {
		CourseDao courseDao = new CourseDao();
		courseDao.save(course, jdbc);
	}

	// 删除课程表信息
	public void delete(int id, JDBCUtil jdbc) throws SQLException {
		CourseDao courseDao = new CourseDao();
		courseDao.deleteById(id, jdbc);
	}

	public boolean binding(int courseId, int classId, JDBCUtil jdbc) throws SQLException {
		StudentDao stuDao = new StudentDao();
		ScoreDao scoreDao = new ScoreDao();
		ClassDao classDao = new ClassDao();
		MyClass myClass = classDao.findById(classId, jdbc);
		
		// 计算学期
		Calendar now = Calendar.getInstance();
		int semester = (int) ((now.get(Calendar.YEAR) - myClass.getGrade()) * 2
				+ Math.round(now.get(Calendar.MONTH) / 12.0));
		
		ArrayList<Score> scoreList = scoreDao.findByCourseId(courseId, jdbc);
		ArrayList<Student> stuArray = stuDao.findByClassId(classId, jdbc);
		Set<Integer> set=new HashSet<Integer>();
		int sl = 0;
		int sa = 0;
		for(;sl<scoreList.size();sl++){
			set.add(scoreList.get(sl).getStuId());
		}
		for(;sa<stuArray.size();sa++){
			set.add(stuArray.get(sa).getId());
		}
		if(sl + sa != set.size()){
			return false;
		}
		for (int i = 0; i < stuArray.size(); i++) {
			Score score = new Score();
			score.setCourseId(courseId);
			score.setStuId(stuArray.get(i).getId());
			score.setSemester(semester);
			scoreDao.save(score, jdbc);
		}
		return true;
	}
}
