package bean;

//用户表
public class User {
	private int id;// 主键
	private String username;// 用户名
	private String password;// 密码
	private int powerLevel;// 权限级别：权限级别-超级管理员=1，管理员=2，学生=3
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String username, String password, Integer powerLevel) {
		super();
		this.setId(id);
		this.username = username;
		this.password = password;
		this.setPowerLevel(powerLevel);
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		if(id == null){
			id = 0;
		}
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(Integer powerLevel) {
		if(powerLevel == null){
			powerLevel = 0;
		}
		this.powerLevel = powerLevel;
	}

}
