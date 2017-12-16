package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SQLDao;
import domain.Problem;
import domain.Text;

@WebServlet("/Servlet/AdminChange")
public class AdminChange extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		RequestDispatcher dispatcher;
		ServletContext context = getServletConfig().getServletContext();
		ServletContext application = this.getServletContext();
		if (sess.getAttribute("login") != null && sess.getAttribute("login").equals("1")) {
			String which1=request.getParameter("text");
			String index1=request.getParameter("pro");
			int which=Integer.parseInt(which1);
			int index=Integer.parseInt(index1);
			SQLDao dao = new SQLDao();
			Problem pro=dao.GetProblem(which, index);
			request.setAttribute("pro", pro);
			request.setAttribute("text", which+"");
			request.setAttribute("index", index+"");
			dispatcher = context.getRequestDispatcher("/WEB-INF/admin/changetext.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../index.jsp");
		}
	}

}
