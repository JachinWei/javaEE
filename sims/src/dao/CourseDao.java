package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Course;
import bean.PageInfo;
import util.JDBCUtil;

public class CourseDao {
	// 添加数据入表
	public Integer save(Course course, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "insert into sims_course (title, credit) values('" + course.getTitle() + "'," + course.getCredit()
				+ ")";
		return jdbc.update(sql);
	}

	// // 根据id删除，返回删除的数目
	// public Integer deleteById(int[] id, JDBCUtil jdbc) throws SQLException {
	// jdbc = new JDBCUtil();
	// StringBuffer strBuf = new StringBuffer();
	// int i = 0;
	// for (; i < id.length - 1; i++) {
	// strBuf.append(id[i]);
	// strBuf.append(",");
	// }
	// strBuf.append(id[i]);
	// String sql = "delete from sims_course where id in (" + strBuf.toString()
	// + ")";
	// return jdbc.update(sql);
	// }

	// 根据id删除，返回删除的数目
	public Integer deleteById(int id, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "delete from sims_course where id = " + id;
		return jdbc.update(sql);
	}

	// 修改表信息
	public Integer update(Course course, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "update sims_course set title= '" + course.getTitle() + "', credit = " + course.getCredit()
				+ " where id = " + course.getId();
		return jdbc.update(sql);
	}

	// 根据课程名查找，返回列表
	public ArrayList<Course> findByTitle(String title, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "select * from sims_course where title like '%" + title + "%'";
		ArrayList<Course> arrayList = new ArrayList<Course>();
		jdbc.query(sql);
		while (jdbc.next()) {
			arrayList.add(this.transform(jdbc));
		}
		return arrayList;
	}

	// 根据课程名查找，返回列表
	public Course findById(int id, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "select * from sims_course where id = " + id;
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	// 根据title查询数据,分页
	public ArrayList<Course> findByTitle(String title, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		String sqlCount = "select count(*) from sims_course where title like '%" + title + "%'";
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
		String sql = "select * from sims_course where title like '%" + title + "%'" + " limit " + startIndex + ","
				+ (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<Course> stuList = new ArrayList<Course>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	// 根据credit查询数据,分页
	public ArrayList<Course> findByCredit(int credit, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		String sqlCount = "select count(*) from sims_course where credit = " + credit;
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
		String sql = "select * from sims_course where credit = " + credit + " limit " + startIndex + ","
				+ (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<Course> stuList = new ArrayList<Course>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	private Course transform(JDBCUtil jdbc) throws SQLException {
		Course course = new Course();
		course.setId(jdbc.getInt("id"));
		course.setTitle(jdbc.getString("title"));
		course.setCredit(jdbc.getInt("credit"));
		return course;
	}
}
