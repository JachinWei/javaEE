package bean;

//分数表
public class Score {
	private int id;// 主键
	private int stuId;// 学生表外键
	private int courseId;// 课程表外键
	private int semester;// 学期
	private float score;// 分数
	
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		if(semester == null){
			semester = 0;
		}
		this.semester = semester;
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

	public int getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		if(stuId == null){
			stuId = 0;
		}
		this.stuId = stuId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		if(courseId == null){
			courseId = 0;
		}
		this.courseId = courseId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(Float score) {
		if(score == null){
			score = (float) 0.0;
		}
		this.score = score;
	}
}
