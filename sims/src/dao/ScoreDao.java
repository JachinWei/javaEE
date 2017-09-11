package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.PageInfo;
import bean.Score;
import util.JDBCUtil;

public class ScoreDao {
	// 根据id删除，返回删除的数目
	public Integer deleteById(int id, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "delete from sims_score where id = " + id;
		return jdbc.update(sql);
	}

	// 根据课程查询数据,分页
	public ArrayList<Score> findByCourseId(int courseId, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		String sqlCount = "select count(*) from sims_score where course_id = " + courseId;
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
		String sql = "select * from sims_score where course_id = " + courseId + " limit " + startIndex + ","
				+ (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<Score> stuList = new ArrayList<Score>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	// 根据课程查询数据,分页
	public ArrayList<Score> findByCourseId(int courseId, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_score where course_id = " + courseId;
		jdbc.query(sql);
		ArrayList<Score> stuList = new ArrayList<Score>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	// 修改分数表信息
	public Integer update(Score score, JDBCUtil jdbc) throws SQLException {
		String sql = "update sims_score set stu_id = " + score.getStuId() + ", course_id = " + score.getCourseId()
				+ ", semester =  " + score.getSemester() + ", score = " + score.getScore() + " where id = "
				+ score.getId();
		return jdbc.update(sql);
	}

	// 根据id查询
	public Score findById(Integer id, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_score where id = " + id;
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	// 添加数据入表
	public Integer save(Score score, JDBCUtil jdbc) throws SQLException {
		String sql = "insert into sims_score (stu_id, course_id, semester, score) values(" + score.getStuId() + ","
				+ score.getCourseId() + "," + score.getSemester() + "," + score.getScore() + ")";
		return jdbc.update(sql);
	}

	private Score transform(JDBCUtil jdbc) throws SQLException {
		Score score = new Score();
		score.setId(jdbc.getInt("id"));
		score.setCourseId(jdbc.getInt("course_id"));
		score.setStuId(jdbc.getInt("stu_id"));
		score.setSemester(jdbc.getInt("semester"));
		score.setScore(jdbc.getFloat("score"));
		return score;
	}
}