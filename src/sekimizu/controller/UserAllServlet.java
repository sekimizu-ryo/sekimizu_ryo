package sekimizu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sekimizu.beans.Branches;
import sekimizu.beans.Users;
import sekimizu.beans.departments;
import sekimizu.service.UserService;
import sekimizu.service.branchesService;
import sekimizu.service.departmentsService;

@WebServlet(urlPatterns = { "/userall" })

public class UserAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<Users> users = new UserService().getAllUser();
		List<Branches> branches = new branchesService().getBranches();
		List<departments>departments= new departmentsService().getDepartments();
		request.setAttribute("branches",branches);
		request.setAttribute("departments",departments);
		request.setAttribute("users", users);
		request.getRequestDispatcher("userall.jsp").forward(request, response);
	}
}

