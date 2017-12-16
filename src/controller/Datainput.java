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

@WebServlet("/Servlet/Datainput")
public class Datainput extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int resu = 0;
		HttpSession sess = request.getSession();
		RequestDispatcher dispatcher;
		ServletContext context = getServletConfig().getServletContext();
		ServletContext application = this.getServletContext();
		if (sess.getAttribute("login") != null && sess.getAttribute("login").equals("1")) {
			if (request.getParameter("submit")!=null&&request.getParameter("submit").equals("提交")) {
				Problem problem = new Problem();
				String which1 = request.getParameter("text");
				String index1 = request.getParameter("index");
				String pro = request.getParameter("pro");
				String one = request.getParameter("one");
				String two = request.getParameter("two");
				String three = request.getParameter("three");
				String four = request.getParameter("four");
				String result = request.getParameter("result");
				problem.setPro(pro);
				problem.setOne(one);
				problem.setTwo(two);
				problem.setThree(three);
				problem.setFour(four);
				problem.setResult(result);
				SQLDao dao = new SQLDao();
				/*
				 * 启动事务更新数据表
				 * */
				resu=dao.ChangePro(which1, index1, problem);
				if(resu==1){
					sess.setAttribute("issuccess", "1");
				}else{
					sess.setAttribute("issuccess", "0");
				}
			}
			response.sendRedirect("AdminText");
		} else {
			response.sendRedirect("../index.jsp");
		}
	}

}
