package bean;

//课程表
public class Course {
	private int id;// 主键
	private String title;// 课程名
	private int credit;// 学分

	public Course() {
		super();
	}

	public Course(String title, Integer credit) {
		super();
		this.title = title;
		this.setCredit(credit);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		if(credit == null){
			credit = 0;
		}
		this.credit = credit;
	}
}
