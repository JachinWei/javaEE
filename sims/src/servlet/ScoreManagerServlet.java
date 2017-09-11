package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PageInfo;
import bean.Score;
import bean.ScoreChoice;
import service.CourseService;
import service.ScoreService;
import util.JDBCUtil;

public class ScoreManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		JDBCUtil jdbc = null;
		
		try {
			jdbc = new JDBCUtil();
			request.setAttribute("courseList", new CourseService().findAll(jdbc));
		} catch (SQLException e) {
			e.printStackTrace(); // 可改进用log4j写到日志
			request.setAttribute("resultStr", "数据库繁忙-1，请稍后再试！");
		}
		if ("scoreMain".equals(method)) { // 班级管理-获取基本界面
			request.getRequestDispatcher("/login/limit/scoreManager.jsp").forward(request, response);
		} else if ("add".equals(method)) {
			String status = request.getParameter("status");
			request.setAttribute("status", "get");
			if ("save".equals(status)) { // 界面-添加功能
				Integer stuId = Integer.parseInt(request.getParameter("stuId")); 
				Integer courseId = Integer.parseInt(request.getParameter("courseId"));
				Integer semester = Integer.parseInt(request.getParameter("semester"));
				Float score = Float.parseFloat(request.getParameter("score"));
				
				Score scoreObj = new Score();
				scoreObj.setStuId(stuId);
				scoreObj.setCourseId(courseId);
				scoreObj.setSemester(semester);
				scoreObj.setScore(score);
				ScoreService scoreService = new ScoreService();
				
				request.setAttribute("scoreObj", scoreObj);
				try {
					scoreService.save(scoreObj, jdbc);
					request.setAttribute("resultStr", "添加成功!");
				} catch (SQLException e) {
					e.printStackTrace(); // 可改进用log4j写到日志
					request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
				}
			}
			request.getRequestDispatcher("/login/limit/scoreManager.jsp").forward(request, response);
		} else if ("search".equals(method)) {
			request.setAttribute("status", "search");
			if(request.getParameter("courseId").isEmpty()){
				request.getRequestDispatcher("/login/limit/scoreManager.jsp").forward(request, response);
				return;
			}
			Integer courseId = Integer.parseInt(request.getParameter("courseId"));
			request.setAttribute("courseId", courseId);
			String temp = request.getParameter("indexPage");
			Integer indexPage = null;
			if (temp != null) {
				indexPage = Integer.parseInt(temp);
			}
			if (indexPage == null || indexPage <= 0) {
				indexPage = 1;
			}
			PageInfo pageInfo = new PageInfo();
			pageInfo.setIndexPage(indexPage);
			ScoreService scoreService = new ScoreService();
			ArrayList<ScoreChoice> scList = null;
			try {
				scList = scoreService.getByCourseId(courseId, pageInfo, jdbc);
				request.setAttribute("scList", scList);
				request.setAttribute("pageInfo", pageInfo);
			} catch (SQLException e) {
				e.printStackTrace(); // 可改进用log4j写到日志
				request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
			}
			request.getRequestDispatcher("/login/limit/scoreManager.jsp").forward(request, response);
		} else if ("delete".equals(method)) {
			int id = Integer.parseInt(request.getParameter("id"));
			ScoreService scoreService = new ScoreService();
			try {
				scoreService.delete(id, jdbc);
				request.getRequestDispatcher("/score?method=search").forward(request, response);
				return;
			} catch (SQLException e) {
				request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
			}
			request.getRequestDispatcher("/login/limit/scoreManager.jsp").forward(request, response);
		} else if ("update".equals(method)) {
			ScoreService scoreService = new ScoreService();
			Score scoreObj = null;
			int id = Integer.parseInt(request.getParameter("id"));
			float score = Float.parseFloat(request.getParameter("score"));
			try {
				scoreObj = scoreService.findById(id, jdbc);
				scoreObj.setScore(score);
				scoreService.update(scoreObj, jdbc);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}

