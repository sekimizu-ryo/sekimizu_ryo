package sekimizu.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sekimizu.service.CommentService;

@WebServlet(urlPatterns = { "/commentdelete" })
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
			new  CommentService().delete(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("./");
	}

}
