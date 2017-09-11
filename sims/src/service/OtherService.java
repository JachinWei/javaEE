package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.CourseScoreSeq;
import bean.PageInfo;
import bean.ScoreLevel;
import dao.OtherDao;
import util.JDBCUtil;

public class OtherService {
	public ArrayList<ScoreLevel> findScoreList(int semester, int classId, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException{
		OtherDao otherDao = new OtherDao();
		return otherDao.findScoreList(semester, classId, pageInfo, jdbc);
	}
	
	public ArrayList<CourseScoreSeq> getCourseScoreList(int semester, int stuId, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException{
		OtherDao otherDao = new OtherDao();
		return otherDao.getCourseScoreList(semester, stuId, pageInfo, jdbc);
	}
}
