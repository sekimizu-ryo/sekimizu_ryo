package sekimizu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sekimizu.beans.Users;
import sekimizu.service.UserService;

@WebServlet(urlPatterns = { "/userall" })

public class UserAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<Users> users = new UserService().getAllUser();
		request.setAttribute("users", users);
		request.getRequestDispatcher("userall.jsp").forward(request, response);
	}
}

