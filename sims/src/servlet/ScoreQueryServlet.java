package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CourseScoreSeq;
import bean.PageInfo;
import bean.Student;
import service.OtherService;
import service.StudentService;
import util.JDBCUtil;

public class ScoreQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("semester") == null){
			request.getRequestDispatcher("/login/limit/scoreQuery.jsp").forward(request, response);
			return;
		}
		JDBCUtil jdbc = null;
		Integer semester = Integer.parseInt(request.getParameter("semester"));
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
		
		OtherService otherService = new OtherService();
		ArrayList<CourseScoreSeq> seqList = null;
		try {
			jdbc = new JDBCUtil();
			Integer uid = (Integer)request.getSession().getAttribute("uid");
			Student stu = new StudentService().findByUid(uid, jdbc);
			Integer stuId = stu.getId();
			request.setAttribute("semester", semester);
			seqList = otherService.getCourseScoreList(semester, stuId, pageInfo, jdbc);
			request.setAttribute("seqList", seqList);
			request.setAttribute("pageInfo", pageInfo);
		} catch (SQLException e) {
			e.printStackTrace(); // 可改进用log4j写到日志
			request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
		}
		request.getRequestDispatcher("/login/limit/scoreQuery.jsp").forward(request, response);
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
