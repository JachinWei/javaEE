package bean;

//班级表
public class MyClass {
	private int id;// 主键
	private String title;// 班级名称
	private int grade;// 年级

	public MyClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyClass(String title, Integer grade) {
		super();
		this.title = title;
		this.setGrade(grade);;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		if(grade == null){
			grade = 0;
		}
		this.grade = grade;
	}

}
