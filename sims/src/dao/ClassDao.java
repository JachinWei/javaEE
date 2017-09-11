package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.MyClass;
import bean.PageInfo;
import util.JDBCUtil;

public class ClassDao {
	public static void main(String[] args) throws SQLException {
		ClassDao demo = new ClassDao();
		JDBCUtil jdbc = new JDBCUtil();
		// 测试添加功能
		MyClass obj1 = new MyClass("国贸1班", 2013);
		MyClass obj2 = new MyClass("国贸2班", 2013);
		System.out.println(demo.save(obj1, jdbc));
		System.out.println(demo.save(obj2, jdbc));

		// 测试查找功能
		ArrayList<MyClass> arr = demo.findByTitle("国贸", jdbc);
		for (MyClass obj : arr) {
			System.out.println(obj.getId() + " " + obj.getTitle() + " " + obj.getGrade());
		}

		// 测试修改功能
		arr.get(0).setTitle("国贸3班");
		demo.update(arr.get(0), jdbc);
		arr = demo.findByTitle("国贸", jdbc);
		for (MyClass obj : arr) {
			System.out.println(obj.getId() + " " + obj.getTitle() + " " + obj.getGrade());
		}

		// 测试删除功能
		// int[] id = { arr.get(0).getId(), arr.get(1).getId() };
		// System.out.println(demo.deleteById(id, jdbc));
	}

	// 添加数据入表
	public Integer save(MyClass myClass, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "insert into sims_class (title, grade) values('" + myClass.getTitle() + "'," + myClass.getGrade()
				+ ")";
		return jdbc.update(sql);
	}

	// 根据id批量删除，返回删除的数目
	// public Integer deleteById(int[] id, JDBCUtil jdbc) throws SQLException {
	// jdbc = new JDBCUtil();
	// StringBuffer strBuf = new StringBuffer();
	// int i = 0;
	// for (; i < id.length - 1; i++) {
	// strBuf.append(id[i]);
	// strBuf.append(",");
	// }
	// strBuf.append(id[i]);
	// String sql = "delete from sims_class where id in (" + strBuf.toString() +
	// ")";
	// return jdbc.update(sql);
	//
	// }

	// 根据id删除，返回删除的数目
	public Integer deleteById(int id, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "delete from sims_class where id = " + id;
		return jdbc.update(sql);
	}

	// 根据grade查询数据,分页
	public ArrayList<MyClass> findByGrade(int grade, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		String sqlCount = "select count(*) from sims_class where grade = " + grade;
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
		String sql = "select * from sims_class where grade = " + grade + " limit " + startIndex + ","
				+ (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<MyClass> stuList = new ArrayList<MyClass>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	// 根据title查询数据,分页
	public ArrayList<MyClass> findByTitle(String title, PageInfo pageInfo, JDBCUtil jdbc) throws SQLException {
		String sqlCount = "select count(*) from sims_class where title like '%" + title + "%'";
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
		String sql = "select * from sims_class where title like '%" + title + "%'" + " limit " + startIndex + ","
				+ (startIndex + PageInfo.PAGE_SIZE);
		jdbc.query(sql);
		ArrayList<MyClass> stuList = new ArrayList<MyClass>();
		while (jdbc.next()) {
			stuList.add(this.transform(jdbc));
		}
		return stuList;
	}

	// 修改班级表信息
	public Integer update(MyClass myClass, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "update sims_class set title= '" + myClass.getTitle() + "', grade = " + myClass.getGrade()
				+ " where id = " + myClass.getId();
		return jdbc.update(sql);
	}

	// 根据班级名查找，返回列表
	public ArrayList<MyClass> findByTitle(String title, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "select * from sims_class where title like '%" + title + "%'";
		ArrayList<MyClass> arrayList = new ArrayList<MyClass>();
		jdbc.query(sql);
		while (jdbc.next()) {
			arrayList.add(this.transform(jdbc));
		}
		return arrayList;
	}

	public MyClass findById(Integer id, JDBCUtil jdbc) throws SQLException {
		jdbc = new JDBCUtil();
		String sql = "select * from sims_class where id = " + id;
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	private MyClass transform(JDBCUtil jdbc) throws SQLException {
		MyClass myClass = new MyClass();
		myClass.setId(jdbc.getInt("id"));
		myClass.setTitle(jdbc.getString("title"));
		myClass.setGrade(jdbc.getInt("grade"));
		return myClass;
	}
}
