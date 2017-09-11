package bean;

import java.sql.Date;

//学生表
public class Student {
	private int id;// 主键
	private int uid;// 用户表外键
	private int classId;// 班级表外键s
	private String stuNumber;// 学号
	private String name;// 姓名
	private String email;// 邮箱
	private int sex;// 性别
	private Date birthday;// 生日
	private String phone;// 手机号

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Integer id, Integer uid, Integer classId, String stuNumber, String name) {
		super();
		this.setId(id);
		this.setUid(uid);
		this.setClassId(classId);
		this.stuNumber = stuNumber;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		if(uid == null){
			uid = 0;
		}
		this.uid = uid;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		if(classId == null){
			classId = 0;
		}
		this.classId = classId;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		if(sex == null){
			sex = 0;
		}
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
