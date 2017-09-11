
package dao;

import java.sql.SQLException;

import bean.User;
import util.JDBCUtil;

public class UserDao {
	// 根据用户名查询数据
	public User findByUsername(String username, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_user where username = '" + username + "'";
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	// 根据用户表主键查询数据
	public User findById(int id, JDBCUtil jdbc) throws SQLException {
		String sql = "select * from sims_user where id = " + id;
		jdbc.query(sql);
		if (jdbc.next()) {
			return this.transform(jdbc);
		}
		return null;
	}

	// 修改表信息
	public Integer update(User user, JDBCUtil jdbc) throws SQLException {
		String sql = "update sims_user set password= '" 
				+ user.getPassword()
				+ "' where id = " + user.getId();
		return jdbc.update(sql);
	}

	// 添加数据入表
	public Integer save(User user, JDBCUtil jdbc) throws SQLException {
		String sql = "insert into sims_user (username, password, power_level) values('" + user.getUsername() + "','"
				+ user.getPassword() + "'," + user.getPowerLevel() + ")";
		return jdbc.update(sql);
	}

	private User transform(JDBCUtil jdbc) throws SQLException {
		User user = new User();
		user.setId(jdbc.getInt("id"));
		user.setUsername(jdbc.getString("username"));
		user.setPassword(jdbc.getString("password"));
		user.setPowerLevel(jdbc.getInt("power_level"));
		return user;
	}
}
