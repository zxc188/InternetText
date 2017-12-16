package controller;

import java.io.IOException;

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

@WebServlet("/Servlet/AdminDelete")
public class AdminDelete extends HttpServlet {

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
			String which=request.getParameter("text");
			String index=request.getParameter("pro");
			SQLDao dao=new SQLDao();
			boolean app=dao.DeletePro(which, index);
			int many=(int)context.getAttribute("howmany");
			if(!app){
				application.setAttribute("howmany", many-1);
			}
			sess.setAttribute("issuccess", "1");
			response.sendRedirect("AdminText");
		} else {
			response.sendRedirect("../index.jsp");
		}
	}

}
