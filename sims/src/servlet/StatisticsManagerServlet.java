package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PageInfo;
import bean.ScoreLevel;
import service.ClassService;
import service.OtherService;
import util.JDBCUtil;

public class StatisticsManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JDBCUtil jdbc = null;
		try {
			jdbc = new JDBCUtil();
			request.setAttribute("classList", new ClassService().findAll(jdbc));
		} catch (SQLException e) {
			e.printStackTrace(); // 可改进用log4j写到日志
			request.setAttribute("resultStr", "数据库繁忙-1，请稍后再试！");
		}
		if(request.getParameter("semester")==null || request.getParameter("classId")==null){
			request.getRequestDispatcher("/login/limit/statistics.jsp").forward(request, response);
			return;
		}
		
		Integer semester = Integer.parseInt(request.getParameter("semester"));
		Integer classId = Integer.parseInt(request.getParameter("classId"));
		request.setAttribute("semester", semester);
		request.setAttribute("classId", classId);
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
		
		
		OtherService ssService = new OtherService();
		ArrayList<ScoreLevel> scoreLevelList = null;
		
		try {
			scoreLevelList = ssService.findScoreList(semester, classId, pageInfo, jdbc);
			request.setAttribute("scoreLevelList", scoreLevelList);
			request.setAttribute("pageInfo", pageInfo);
		} catch (SQLException e) {
			e.printStackTrace(); // 可改进用log4j写到日志
			request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
		}
		request.getRequestDispatcher("/login/limit/statistics.jsp").forward(request, response);
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
