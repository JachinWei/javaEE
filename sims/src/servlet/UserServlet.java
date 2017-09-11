package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.User;
import service.StudentService;
import service.UserService;
import util.JDBCUtil;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = (String) request.getParameter("method");
		JDBCUtil jdbc = null;
		if ("login".equals(method)) {
			// 1.获取参数
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// 2.前端校验格式
			// 3.调用service层
			UserService userService = new UserService();
			// 4、根据结果跳转到不同页面
			try {
				jdbc = new JDBCUtil();
				if (!userService.isUserExist(username, jdbc)) {
					request.setAttribute("resultStr", "该用户不存在!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				}
				User user = userService.login(username, password, jdbc);
				if (user == null) {
					request.setAttribute("resultStr", "密码错误!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				}
				// 设置session
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUsername());
				session.setAttribute("powerLevel", user.getPowerLevel());
				session.setAttribute("uid", user.getId());
				request.getRequestDispatcher("/login/index.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace(); 
				request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} else if ("register".equals(method)) {
			
			// 1.获取参数
			String stuNumber = request.getParameter("stuNumber");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// 2.前端校验格式
			// 3.调用service层
			UserService userService = new UserService();
			StudentService stuService = new StudentService();
			// 4、根据结果跳转到不同页面
			try {
				jdbc = new JDBCUtil();
				if (!stuService.isStuNumberExist(stuNumber, jdbc)) {
					request.setAttribute("resultStr", "该学号存在!");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
					return;
				}
				if (stuService.isRegister(stuNumber, jdbc)) {
					request.setAttribute("resultStr", "该学号已注册!");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
					return;
				}
				if (userService.isUserExist(username, jdbc)) {
					request.setAttribute("resultStr", "该用户名已注册!");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
					return;
				}
				// 事务操作
				jdbc.setAutoCommit(false);
				userService.register(username, password, stuNumber, jdbc);
				jdbc.commit();
				jdbc.setAutoCommit(true);
				request.getRequestDispatcher("/register_jump.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace(); // 可改进用log4j写到日志
				if (jdbc != null) {
					try {
						jdbc.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
		} else if ("logout".equals(method)) {
			HttpSession session = request.getSession(false);// 防止创建Session
			if (session == null) {

			} else {
				session.invalidate();
			}
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else if("manager".equals(method)){
			String status = (String) request.getParameter("status");
			HttpSession session = request.getSession(false);// 防止创建Session
			String username = (String) session.getAttribute("username");
			Integer powerLevel = (Integer) session.getAttribute("powerLevel");
			if ("get".equals(status)) {
				UserService userService = new UserService();
				try {
					jdbc = new JDBCUtil();
					User user = userService.findByUsername(username, jdbc);
					request.setAttribute("user", user);
					if (powerLevel == 3) {
						StudentService stuService = new StudentService();
						Student stu = stuService.findByUid(user.getId(), jdbc);
						request.setAttribute("stu", stu);
					}
					request.getRequestDispatcher("/login/limit/userManager.jsp").forward(request, response);
				} catch (SQLException e) {
					request.setAttribute("resultStr", "数据库繁忙!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} else if ("save".equals(status)) {
				UserService userService = new UserService();
				try {
					jdbc = new JDBCUtil();
					if (request.getParameter("password") != null && request.getParameter("password")!="") {
						userService.updatePwd((String) request.getParameter("password"), username, jdbc);
					}
					User user = userService.findByUsername(username, jdbc);
					request.setAttribute("user", user);
					if (powerLevel == 3) {
						StudentService stuService = new StudentService();
						Student stu = stuService.findByUid(user.getId(), jdbc);
						if (request.getParameter("name") != null) {
							stu.setName((String) request.getParameter("name"));
						}
						if (request.getParameter("email") != null) {
							stu.setEmail((String) request.getParameter("email"));
						}
						if (request.getParameter("sex") != null) {
							stu.setSex(Integer.parseInt(request.getParameter("sex")));
						}
						if (request.getParameter("birthday") != null) {
							long times;
							try {
								times = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"))
										.getTime();
								stu.setBirthday(new Date(times));
							} catch (ParseException e) {
								request.setAttribute("resultStr", "日期转换失败!");
							}
						}
						if (request.getParameter("phone") != null) {
							stu.setPhone((String) request.getParameter("phone"));
						}
						stuService.update(stu, jdbc);
						request.setAttribute("stu", stu);
					}
					request.setAttribute("resultStr", "保存成功!");
					request.getRequestDispatcher("/login/limit/userManager.jsp").forward(request, response);
				} catch (SQLException e) {
					request.setAttribute("resultStr", "数据库繁忙!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
