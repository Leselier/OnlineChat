package xcservlet.newfriend;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import xcbean.*;

public class Search extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		String keyword = request.getParameter("keyword");
		// int error = 0;
		if(keyword == null || ((keyword = keyword.trim()) != null && keyword.length() == 0)){
			out.print("<script>history.back();</script>");
			return;
		}

		XCUser[] users = XCUser.blurSearch(keyword);
		String[][] sUsers;
		if(users == null)
			sUsers = null;
		else{
			sUsers = new String[users.length][3];
			for(int i = 0; i < users.length; ++i){
				sUsers[i][0] = Integer.toString(users[i].getUser_id());
				sUsers[i][1] = users[i].getUser_nick();
				sUsers[i][2] = users[i].getUser_email();
			}
		}
		request.setAttribute("users", sUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/newfriend/searchResult.jsp");
		dispatcher.forward(request, response);
	}
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		XCUser xcUser = (XCUser)session.getAttribute("cUser");
		if(xcUser == null)
		{
			PrintWriter out = response.getWriter();
			response.sendRedirect("/");
			return ;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/newfriend/search.jsp");
		dispatcher.forward(request, response);
	}
	private void pError(HttpServletRequest request,
		HttpServletResponse response, String strErr) throws IOException, ServletException
	{
		// PrintWriter out = response.getWriter();
		// out.print(strErr);
		request.setAttribute("strErr", strErr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/user/login.jsp");
		dispatcher.forward(request, response);
	}
}