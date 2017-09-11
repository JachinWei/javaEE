package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.MyClass;
import bean.PageInfo;
import service.ClassService;
import util.JDBCUtil;

public class ClassManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		JDBCUtil jdbc = null;
		if ("classMain".equals(method)) { // 班级管理-获取基本界面
			request.getRequestDispatcher("/login/limit/classManager.jsp").forward(request, response);
		} else if ("add".equals(method)) {
			String status = request.getParameter("status");
			request.setAttribute("status", "get");
			if ("save".equals(status)) { // 界面-添加功能
				String title = request.getParameter("title");
				Integer grade = Integer.parseInt(request.getParameter("grade"));
				MyClass myClass = new MyClass();
				myClass.setTitle(title);
				myClass.setGrade(grade);
				ClassService classService = new ClassService();
				request.setAttribute("myClass", myClass);
				try {
					jdbc = new JDBCUtil();
					classService.save(myClass, jdbc);
					request.setAttribute("resultStr", "添加成功!");
				} catch (SQLException e) {
					e.printStackTrace(); // 可改进用log4j写到日志
					request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
				}
			}
			request.getRequestDispatcher("/login/limit/classManager.jsp").forward(request, response);
		} else if ("search".equals(method)) {
			request.setAttribute("status", "search");
			String searchStr = request.getParameter("searchStr");
			request.setAttribute("searchStr", searchStr);
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
			ClassService classService = new ClassService();
			ArrayList<MyClass> classList = null;
			int grade;
			try {
				grade = Integer.parseInt(searchStr);
			} catch (NumberFormatException e) {
				grade = 1;
			}
			// 按年级查找
			try {
				jdbc = new JDBCUtil();
				if (grade > 1000) {
					classList = classService.findByGrade(grade, pageInfo, jdbc);
				} else {
					classList = classService.findByTtile(searchStr, pageInfo, jdbc);
				}
				request.setAttribute("classList", classList);
				request.setAttribute("pageInfo", pageInfo);
			} catch (SQLException e) {
				e.printStackTrace(); // 可改进用log4j写到日志
				request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
			}
			request.getRequestDispatcher("/login/limit/classManager.jsp").forward(request, response);
		} else if ("delete".equals(method)) {
			int id = Integer.parseInt(request.getParameter("id"));
			ClassService classService = new ClassService();
			try {
				jdbc = new JDBCUtil();
				classService.delete(id, jdbc);
				request.getRequestDispatcher("/myclass?method=search").forward(request, response);
				return;
			} catch (SQLException e) {
				request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
			}
			request.getRequestDispatcher("/login/limit/classManager.jsp").forward(request, response);
		} else if ("update".equals(method)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			Integer grade = Integer.parseInt(request.getParameter("grade"));

			ClassService classService = new ClassService();
			MyClass myClass = null;
			try {
				jdbc = new JDBCUtil();
				myClass = classService.findById(id, jdbc);
				myClass.setTitle(title);
				myClass.setGrade(grade);
				classService.update(myClass, jdbc);
				request.setAttribute("resultStr", "修改成功!");
			} catch (SQLException e) {
				request.setAttribute("resultStr", "数据库繁忙，请稍后再试！");
			}
		} else if ("upload".equals(method)) {
			File file = new File(this.getServletContext().getRealPath("/upload/1.txt"));
			// 消息提示
			String message = "";
			try {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024 * 1024);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
				// 2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 3、判断提交上来的数据是否是上传表单的数据
				if (!ServletFileUpload.isMultipartContent(request)) {
					// 按照传统方式获取数据
					message = "上传方式异常";
					return;
				}
				// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
				upload.setFileSizeMax(1024 * 1024);
				// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				ArrayList<FileItem> list = (ArrayList<FileItem>) upload.parseRequest(request);
				for (FileItem item : list) {
					// 得到上传的文件名称，
					String filename = item.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 得到上传文件的扩展名
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
					// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
					if ("txt".equals(fileExtName)) {
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						FileOutputStream out = new FileOutputStream(file);
						byte buffer[] = new byte[1024];
						int len = 0;
						while ((len = in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
						in.close();
						out.close(); // 删除处理文件上传时生成的临时文件
						item.delete();
						message = "文件上传成功！";
					}
				}
			} catch (FileUploadBase.FileSizeLimitExceededException e) {
				e.printStackTrace();
				request.setAttribute("message", "单个文件超出最大值！！！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			} catch (Exception e) {
				message = "文件上传失败！";
				e.printStackTrace();
			}

			ArrayList<String> strList = this.encrypt();
			ClassService classService = new ClassService();
			MyClass myclass = new MyClass();
			try {
				for (int i = 0; i < strList.size(); i++) {
					String[] strArray = strList.get(i).split(",");
					myclass.setTitle(strArray[0]);
					myclass.setGrade(Integer.parseInt(strArray[1]));
					classService.save(myclass, jdbc);
				}
				message += "-解析完成，已加入数据库!";
			} catch (SQLException e) {
				message = "文件解析失败！";
			}

			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	private ArrayList<String> encrypt() {
		File file = new File(this.getServletContext().getRealPath("/upload/1.txt"));
		ArrayList<String> strList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String temp = null;
			while ((temp = br.readLine()) != null) {// 使用readLine方法，一次读一行
				strList.add(temp);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strList;
	}

}
