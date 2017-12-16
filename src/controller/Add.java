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

@WebServlet("/Servlet/Add")
public class Add extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int g=-100;
		HttpSession sess = request.getSession();
		RequestDispatcher dispatcher;
		ServletContext context = getServletConfig().getServletContext();
		ServletContext application=this.getServletContext();  
		if(sess.getAttribute("login")!=null&&sess.getAttribute("login").equals("1")){
			LinkedList<Problem> list=new LinkedList<>();
			for(int i=0;i<2;i++){
				Problem pro=new Problem();
				pro.setPro(request.getParameter("pro"+i));
				pro.setOne(request.getParameter("one"+i));
				pro.setTwo(request.getParameter("two"+i));
				pro.setThree(request.getParameter("three"+i));
				pro.setFour(request.getParameter("four"+i));
				pro.setResult(request.getParameter("result"+i));
				list.add(pro);
				System.out.println(pro.getPro());
			}
			int many=(int)application.getAttribute("howmany");
			SQLDao dao=new SQLDao();
			g=dao.Addtext(many, list);
			if(g!=-100){
				sess.setAttribute("issuccess", "1");
				application.setAttribute("howmany", many+1);
			}else{
				sess.setAttribute("issuccess", "0");
			}
			response.sendRedirect("AdminText");
		}else{
			response.sendRedirect("../index.jsp");
		}
	}

}
