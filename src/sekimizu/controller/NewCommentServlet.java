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

import org.apache.commons.lang.StringUtils;

import sekimizu.beans.Comment;
import sekimizu.beans.Users;
import sekimizu.service.CommentService;

@WebServlet(urlPatterns = { "/comment" })
public class NewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		Users loginUser = (Users) session.getAttribute("loginUser");
		List<String> comments = new ArrayList<String>();


		if (isValid(request, comments) == true) {
			Comment comment = new Comment();
			comment.setUserId((loginUser.getId()));
			comment.setPostId(Integer.parseInt(request.getParameter("postid")));
			comment.setText(request.getParameter("text"));
			new CommentService().register(comment);
			response.sendRedirect("./");

		} else {
			Comment comment = new Comment();
			comment.setUserId((loginUser.getId()));
			comment.setPostId(Integer.parseInt(request.getParameter("postid")));
			comment.setText(request.getParameter("text"));
			session.setAttribute("errorMessages", comments);
			response.sendRedirect("./");

		}
	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String text = request.getParameter("text");

		if (StringUtils.isEmpty(text) == true) {
			comments.add("コメントを入力してください");
		}
		if (500 < text.length()) {
			comments.add("500文字以下で入力してください");
		}
		if (comments.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
