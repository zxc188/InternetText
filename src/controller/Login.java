package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SQLDao;
import domain.User;
import util.Replaces;

public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		RequestDispatcher dispatcher;
		ServletContext context = getServletConfig().getServletContext();
		if (sess.getAttribute("login") != null && sess.getAttribute("login").equals("1")) {
			User user = (User) sess.getAttribute("user");
			if (user.getRoot().equals("0")) {
				/*
				 * 用户跳转
				 */
				dispatcher = context.getRequestDispatcher("/WEB-INF/student/home.jsp");
				dispatcher.forward(request, response);
			} else {
				/*
				 * 管理员跳转
				 */
				dispatcher = context.getRequestDispatcher("/WEB-INF/admin/adminhome.jsp");
				dispatcher.forward(request, response);
			}
		} else {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username == null) {
				username = "";
			}
			if (password == null) {
				password = "";
			}
			SQLDao dao = new SQLDao();
			User user = dao.Login(username, password);
			if(user!=null){
				int many=(int)context.getAttribute("howmany");
				user=dao.Getgreads(user,many);
			}
			if (user == null) {
				sess.setAttribute("login", "0");
				response.sendRedirect("../index.jsp");
			} else {
				String root = user.getRoot();
				if (root.equals("0")) {
					/*
					 * 用户登录
					 */
					//System.out.println(user.getUsername()+""+user.getOne()+""+user.getTwo()+""+user.getThree()+""+user.getFour());
					sess.setAttribute("user", user);
					sess.setAttribute("login", "1");
					dispatcher = context.getRequestDispatcher("/WEB-INF/student/home.jsp");
					dispatcher.forward(request, response);
				} else {
					/*
					 * 管理员登录
					 */
					sess.setAttribute("user", user);
					sess.setAttribute("login", "1");
					dispatcher = context.getRequestDispatcher("/WEB-INF/admin/adminhome.jsp");
					dispatcher.forward(request, response);
				}

			}
		}
	}
}
