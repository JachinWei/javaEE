package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.PageInfo;
import bean.Score;
import bean.ScoreChoice;
import bean.Student;
import dao.ScoreDao;
import dao.StudentDao;
import util.JDBCUtil;

//对主要涉及成绩表处理逻辑的服务
public class ScoreService {
	// 根据id获取成绩表所有数据
	public Score findById(int id, JDBCUtil jdbc) throws SQLException {
		ScoreDao scoreDao = new ScoreDao();
		return scoreDao.findById(id, jdbc);
	}

	// 根据课程编号获取成绩表所有数据
	public ArrayList<Score> findByCourseId(int courseId, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		ScoreDao scoreDao = new ScoreDao();
		return scoreDao.findByCourseId(courseId, pageInfo, jdbc);
	}
	

	// 获取成绩表关联信息
	public ArrayList<ScoreChoice> getByCourseId(int courseId, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException{
		ArrayList<Score> scoreList = this.findByCourseId(courseId, pageInfo, jdbc);
		StudentDao stuDao = new StudentDao();
		ArrayList<ScoreChoice> scList = new ArrayList<ScoreChoice>(); 
		for(int i=0;i<scoreList.size();i++){
			Student stu = stuDao.findById(scoreList.get(i).getStuId(), jdbc);
			ScoreChoice sc = new ScoreChoice();
			sc.setStuName(stu.getName());
			sc.setStuNum(stu.getStuNumber());
			sc.setId(scoreList.get(i).getId());
			sc.setScore(scoreList.get(i).getScore());
			scList.add(sc);
		}
		return scList; 		
	}

	// 更新成绩表的数据
	public void update(Score score, JDBCUtil jdbc) throws SQLException {
		ScoreDao scoreDao = new ScoreDao();
		scoreDao.update(score, jdbc);
	}

	// 添加成绩表信息
	public void save(Score score, JDBCUtil jdbc) throws SQLException {
		ScoreDao scoreDao = new ScoreDao();
		scoreDao.save(score, jdbc);
	}

	// 删除成绩表信息
	public void delete(int id, JDBCUtil jdbc) throws SQLException {
		ScoreDao scoreDao = new ScoreDao();
		scoreDao.deleteById(id, jdbc);
	}
	
}
