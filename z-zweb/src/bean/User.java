package bean;




import Service.UserService;

public class User {
	private Integer id;
	private String nickname;
	private String password;
	private String email;
	private String emailVerifyCode;
	private String VerifyCode;
	private int isEmailVerify;
	
	
	public String getVerifyCode() {
		return VerifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		VerifyCode = verifyCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}
	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}
	public int getIsEmailVerify() {
		return isEmailVerify;
	}
	public void setIsEmailVerify(int isEmailVerify) {
		this.isEmailVerify = isEmailVerify;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", password="
				+ password + ", email=" + email + ", emailVerifyCode="
				+ emailVerifyCode + ", VerifyCode=" + VerifyCode
				+ ", isEmailVerify=" + isEmailVerify + "]";
	}
	

	
	

	
}
