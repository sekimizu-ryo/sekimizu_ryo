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

import sekimizu.beans.Posts;
import sekimizu.beans.Users;
import sekimizu.service.PostService;

@WebServlet(urlPatterns = { "/post" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("post.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> posts = new ArrayList<String>();
		HttpSession session = request.getSession();
		Users loginUser = (Users) session.getAttribute("loginUser");


		if (isValid(request, posts) == true) {


			Posts post = new Posts();
			post.setuser_id(String.valueOf(loginUser.getId()));
			post.setSubject(request.getParameter("subject"));
			post.setText(request.getParameter("text"));
			post.setCategory(request.getParameter("category"));
			new PostService().register(post);
			response.sendRedirect("./");

		} else {

			Posts post = new Posts();
			post.setuser_id(String.valueOf(loginUser.getId()));
			post.setSubject(request.getParameter("subject"));
			post.setText(request.getParameter("text"));
			post.setCategory(request.getParameter("category"));
			session.setAttribute("errorMessages", posts);
			request.setAttribute("post", post);
			request.getRequestDispatcher("post.jsp").forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> posts) {

		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isEmpty(subject) == true) {
			posts.add("件名がありません。");
		}

		if (StringUtils.isEmpty(text) == true) {
			posts.add("本文がありません。");
		}

		if (StringUtils.isEmpty(category) == true) {
			posts.add("カテゴリーがありません。");
		}

		if (30 < subject.length()) {
			posts.add("件名は30文字以下で入力してください");
		}

		if (1000 < text.length()) {
			posts.add("本文は1000文字以下で入力してください");
		}

		if (10 < category.length()) {
			posts.add("カテゴリーは10文字以下で入力してください");
		}

		if (posts.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
