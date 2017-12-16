package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SQLDao;
import domain.User;

public class AdminGrerads extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		RequestDispatcher dispatcher;
		ServletContext context = getServletConfig().getServletContext();
		ServletContext application=this.getServletContext();  
		if(sess.getAttribute("login")!=null&&sess.getAttribute("login").equals("1")){
			SQLDao dao=new SQLDao();
			int many=(int)context.getAttribute("howmany");
			LinkedList<User> list=dao.Admingetgreads(many);
			request.setAttribute("allgreads", list);
			request.setAttribute("many", many+"");
			dispatcher = context.getRequestDispatcher("/WEB-INF/admin/admingreads.jsp");
			dispatcher.forward(request, response);
		}else{
			response.sendRedirect("../index.jsp");
		}
	}

}
