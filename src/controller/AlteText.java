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

public class AlteText extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int f = -100;
		HttpSession sess = request.getSession();
		RequestDispatcher dispatcher;
		ServletContext context = getServletConfig().getServletContext();
		ServletContext application = this.getServletContext();
		if (sess.getAttribute("login") != null && sess.getAttribute("login").equals("1")) {
			String whichs[]=request.getParameterValues("which");
			String problem=request.getParameter("pro");
			String one=request.getParameter("one");
			String two=request.getParameter("two");
			String three=request.getParameter("three");
			String four=request.getParameter("four");
			String result=request.getParameter("result");
			SQLDao dao = new SQLDao();
			Problem pro=new Problem();
			pro.setPro(problem);
			pro.setOne(one);
			pro.setTwo(two);
			pro.setThree(three);
			pro.setFour(four);
			pro.setResult(result);
			for(String str : whichs){
				f=dao.Inserttotext(str, pro);
				if (f != -100) {
					sess.setAttribute("issuccess", "1");
				} else {
					sess.setAttribute("issuccess", "0");
				}
			}
			response.sendRedirect("AdminText");
		} else {
			response.sendRedirect("../index.jsp");
		}
	}

}
