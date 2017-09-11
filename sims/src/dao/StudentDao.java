package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.PageInfo;
import bean.Student;
import util.JDBCUtil;

public class StudentDao {
	// 根据学号查询数据
	public Student findByStuNumber(String stuNumber, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_student where stu_number = " + stuNumber;
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	// 根据uid查询数据
	public Student findByUid(int uid, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_student where uid = " + uid;
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	// 根据id查询数据
	public Student findById(int id, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_student where id = " + id;
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	// 根据classId查询数据,分页
	public ArrayList<Student> findByClassId(int classId, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
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
		String sql = "select * from sims_student where class_id = " + classId + " limit " + startIndex + ","
				+ (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<Student> stuList = new ArrayList<Student>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	// 根据classId查询数据,分页
	public ArrayList<Student> findByClassId(int classId, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_student where class_id = " + classId;
		jdbc.query(sql);
		ArrayList<Student> stuList = new ArrayList<Student>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	// 根据id删除，返回删除的数目
	public Integer deleteById(int id, JDBCUtil jdbc) throws SQLException {
		String sql = "delete from sims_student where id = " + id;
		return jdbc.update(sql);
	}

	// 修改表信息
	public Integer update(Student student, JDBCUtil jdbc) throws SQLException {
		String sql = "update sims_student set uid = " + student.getUid() + ", class_id = " + student.getClassId()
				+ ", name = '" + student.getName() + "', stu_number = '" + student.getStuNumber() + "', sex = "
				+ student.getSex();
		if (student.getEmail() != null) {
			sql += ", email = '" + student.getEmail() + "'";
		}
		if (student.getPhone() != null) {
			sql += ", phone = '" + student.getPhone() + "'";
		}
		if (student.getBirthday() != null) {
			sql += ", birthday = '" + student.getBirthday() + "'";
		}
		sql += " where id = " + student.getId();
		return jdbc.update(sql);
	}

	public Integer save(Student student, JDBCUtil jdbc) throws SQLException {
		String sql = "insert into sims_student (name, stu_number, class_id, email, phone) values('" + student.getName()
				+ "','" + student.getStuNumber() + "'," + student.getClassId() + ",'" + student.getEmail() + "','"
				+ student.getPhone() + "')";
		return jdbc.update(sql);
	}

	private Student transform(JDBCUtil jdbc) throws SQLException {
		Student stu = new Student();
		stu.setId(jdbc.getInt("id"));
		stu.setClassId(jdbc.getInt("class_id"));
		stu.setUid(jdbc.getInt("uid"));
		stu.setStuNumber(jdbc.getString("stu_number"));
		stu.setBirthday(jdbc.getDate("birthday"));
		stu.setEmail(jdbc.getString("email"));
		stu.setPhone(jdbc.getString("phone"));
		stu.setSex(jdbc.getInt("sex"));
		stu.setName(jdbc.getString("name"));
		return stu;
	}
}
