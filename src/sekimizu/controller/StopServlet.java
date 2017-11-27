package sekimizu.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sekimizu.service.UserService;

@WebServlet(urlPatterns = { "/stop" })
public class StopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		new UserService().getworking(Integer.parseInt(request.getParameter("is_working")),(request.getParameter("id")));
		response.sendRedirect("userall");
	}

}
