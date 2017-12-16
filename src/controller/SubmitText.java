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

public class SubmitText extends HttpServlet {

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
			int many=(int)context.getAttribute("howmany");
			User user=(User)sess.getAttribute("user");
			int gread = 0;
			int which = Integer.parseInt(request.getParameter("Text"));
			String wh=request.getParameter("Text");
			which=which-1;
			SQLDao dao = new SQLDao();
			domain.Text text = dao.GetText(wh);
			LinkedList<String> result_list=new LinkedList<>();
			LinkedList<String> submit_list=new LinkedList<>();
			for(int j=0;j<text.getList().size();j++){
				String result_str=text.getList().get(j).getResult();
				result_str=result_str.replace(" ", "");
				result_list.add(result_str);
			}
			for(int j=0;j<text.getList().size();j++){
				String fir = request.getParameter(which + "pro"+j);
				if(fir==null){
					fir="E";
				}
				submit_list.add(fir);
			}
			double add=100/(text.getList().size());
			for(int j=0;j<text.getList().size();j++){
				if(submit_list.get(j)!=null&&submit_list.get(j).equals(result_list.get(j))){
					gread+=add;
				}
			}
			/*String one = text.getList().get(0).getResult();
			one = one.replace(" ", "");
			String two = text.getList().get(1).getResult();
			two = two.replace(" ", "");
			String fir = request.getParameter(which + "pro0");
			String sec = request.getParameter(which + "pro1");
			System.out.println(fir+"111\n"+sec+"111\n"+one+"111\n"+two+"111\n");
			
			if (fir!=null&&fir.equals(one)) {
				gread += 50;
			}
			if (sec.equals(two)) {
				gread += 50;
			}
			if(fir==null){
				
			}*/
			dao.Updategread(user.getUsername(), wh, gread);
			user=dao.Getgreads(user, many);
			sess.setAttribute("user", user);
			dispatcher = context.getRequestDispatcher("/Servlet/Text");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("../index.jsp");
		}

	}

}
