package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.CourseScoreSeq;
import bean.PageInfo;
import bean.ScoreLevel;
import util.JDBCUtil;

public class OtherDao {
	public static void main(String[] args) throws SQLException {
//		ScoreLevelDao obj = new ScoreLevelDao();
//		JDBCUtil jdbc = new JDBCUtil();
//		PageInfo pageInfo = new PageInfo();
//		pageInfo.setIndexPage(1);
//		int semester = 1;
//		int classId = 4;
//		ScoreLevel demo = null;
//		ArrayList<ScoreLevel> array = obj.findScoreList(semester, classId, pageInfo, jdbc);
//		for (int i = 0; i < array.size(); i++) {
//			demo = array.get(i);
//			System.out.println(demo.getTotalScore() + "-" + demo.getLevel());
//		}
		OtherDao obj = new OtherDao();
		JDBCUtil jdbc = new JDBCUtil();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setIndexPage(1);
		int semester = 1;
		int stuId = 4;
		CourseScoreSeq demo = null;
		ArrayList<CourseScoreSeq> array = obj.getCourseScoreList(semester, stuId, pageInfo, jdbc);
		for (int i = 0; i < array.size(); i++) {
			demo = array.get(i);
			System.out.println(demo.getTitle() + "-" + demo.getScore());
		}
	}

	public ArrayList<CourseScoreSeq> getCourseScoreList(int semester, int stuId, PageInfo pageInfo, JDBCUtil jdbc)
			throws SQLException {
		String sqlCount = "select count(*) from sims_score where semester = " + semester + " and stu_id = " + stuId;
		jdbc.query(sqlCount);
		pageInfo.setCount(jdbc.getCount());
		if (pageInfo.getCount() != 0) {
			pageInfo.setTotalPages(pageInfo.getCount() % PageInfo.PAGE_SIZE == 0
					? pageInfo.getCount() / PageInfo.PAGE_SIZE : pageInfo.getCount() / PageInfo.PAGE_SIZE + 1);
			if (pageInfo.getIndexPage() > pageInfo.getTotalPages()) {
				pageInfo.setIndexPage(pageInfo.getTotalPages());
			}
		}
		int startIndex = (pageInfo.getIndexPage() - 1) * PageInfo.PAGE_SIZE;
		String sql = "select b.title title, b.credit credit, a.score score"
				+ " from sims_score as a left join sims_course as b on a.course_id = b.id  " 
				+ " where a.semester = " + semester + " and a.stu_id = " + stuId
				+" limit "+ startIndex + "," + (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<CourseScoreSeq> stuList = new ArrayList<CourseScoreSeq>();
		while (jdbc.next()) {
			stuList.add(this.transform2(jdbc));
		}
		return stuList;
	}

	public ArrayList<ScoreLevel> findScoreList(int semester, int classId, PageInfo pageInfo, JDBCUtil jdbc)
			throws SQLException {
		String sqlCount = "select count(*) from sims_student where class_id = " + classId;
		jdbc.query(sqlCount);
		pageInfo.setCount(jdbc.getCount());
		if (pageInfo.getCount() != 0) {
			pageInfo.setTotalPages(pageInfo.getCount() % PageInfo.PAGE_SIZE == 0
					? pageInfo.getCount() / PageInfo.PAGE_SIZE : pageInfo.getCount() / PageInfo.PAGE_SIZE + 1);
			if (pageInfo.getIndexPage() > pageInfo.getTotalPages()) {
				pageInfo.setIndexPage(pageInfo.getTotalPages());
			}
		}
		int startIndex = (pageInfo.getIndexPage() - 1) * PageInfo.PAGE_SIZE;
		String sql = "select a.stu_number id, a.class_id class_id, b.semester semester, sum(b.score) sum"
				+ " from sims_student as a left join sims_score as b on a.id = b.stu_id  " + " group by a.id"
				+ " having class_id = " + classId + " and semester = " + semester + " order by sum desc" + " limit "
				+ startIndex + "," + (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<ScoreLevel> stuList = new ArrayList<ScoreLevel>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	private ScoreLevel transform(JDBCUtil jdbc) throws SQLException {
		ScoreLevel scoreLevel = new ScoreLevel();
		scoreLevel.setStuId(jdbc.getString("id"));
		scoreLevel.setTotalScore(jdbc.getFloat("sum"));
		scoreLevel.setLevel(jdbc.getRs().getRow());
		return scoreLevel;
	}
	
	private CourseScoreSeq transform2(JDBCUtil jdbc) throws SQLException {
		CourseScoreSeq obj = new CourseScoreSeq();
		obj.setTitle(jdbc.getString("title"));
		obj.setCredit(jdbc.getInt("credit"));
		obj.setScore(jdbc.getFloat("score"));
		return obj;
	}
}
