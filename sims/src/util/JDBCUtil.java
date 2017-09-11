package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
//	public final static String drv = "com.mysql.jdbc.Driver";// 数据库类型
//	public final static String url = "jdbc:mysql://120.77.247.4:3306/sims?characterEncoding=utf-8";// 数据库网址
//	public final static String usr = "wyg";// 用户名
//	public final static String pwd = "19950629";// 密码
	
	public final static String drv = "com.mysql.jdbc.Driver";// 数据库类型
	public final static String url = "jdbc:mysql://localhost:3306/sims?characterEncoding=utf-8&useOldAliasMetadataBehavior=true";// 数据库网址
	public final static String usr = "root";// 用户名
	public final static String pwd = "root";// 密码
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	static {
		try {
			Class.forName(drv);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public JDBCUtil() throws SQLException {
		conn = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.usr, JDBCUtil.pwd);
		stmt = conn.createStatement();	
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		this.closeALL();
	}
	
	public void rollback() throws SQLException{
		conn.rollback();
	}
	
	public void setAutoCommit(boolean f) throws SQLException{
		conn.setAutoCommit(f);
	}
	
	public void commit() throws SQLException{
		conn.commit();
	}

	public void closeALL() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public void query(String sql) throws SQLException {
		rs = stmt.executeQuery(sql);
	}

	public Integer update(String sql) throws SQLException {
		return stmt.executeUpdate(sql);
	}

	// rs的下一条记录是否存在
	public boolean next() throws SQLException {
		return rs.next();
	}
	
	//获取总记录数
	public Integer getCount() throws SQLException{
		if(this.next()){
			return rs.getInt(1);
		}
		return null;
	}

	// 获取字符串类型字段的值，字段值为null型的，按照空字符串处理
	public String getString(String field) throws SQLException {
		return rs.getString(field);
	}

	// 获取整数类型字段的值
	public Integer getInt(String field) throws SQLException {
		return rs.getInt(field);
	}

	// 获取日期类型字段的值
	public Date getDate(String field) throws SQLException {
		return rs.getDate(field);
	}

	// 获取实数类型字段的值
	public Float getFloat(String field) throws SQLException {
		return rs.getFloat(field);
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

}
