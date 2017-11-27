package sekimizu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sekimizu.beans.Users;
import sekimizu.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		Users users = loginService.login(login_id, password);

		HttpSession session = request.getSession();
		if (users != null ) {
			session.setAttribute("loginUser", users);
			response.sendRedirect("./");
		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("login");
		}
	}

}
