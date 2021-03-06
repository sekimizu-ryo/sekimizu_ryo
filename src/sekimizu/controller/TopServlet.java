package sekimizu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import sekimizu.beans.Comment;
import sekimizu.beans.Posts;
import sekimizu.beans.Users;
import sekimizu.service.CommentService;
import sekimizu.service.PostService;
import sekimizu.service.UserService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {
		String Category = request.getParameter("category");
		String Sdate = request.getParameter("StartDate");
		String Edate = request.getParameter("EndDate");

		if (StringUtils.isEmpty(Sdate) == true) {
			Sdate="2017-10-01";

		}else{
			request.setAttribute("StartDate",Sdate);
		}
		if(StringUtils.isEmpty(Edate) == true){
			Date date = new Date();
			SimpleDateFormat EndDate = new SimpleDateFormat("yyyy-MM-dd");
			Edate = EndDate.format(date); // format(date)のdateは、Date date = new Date();のdate

		}else
			request.setAttribute("EndDate",Edate);


		List<Posts> posts = new PostService().getAllPost(Sdate,Edate,Category);
		List<Comment> comments = new CommentService().getComment();
		List<Users> users = new UserService().getAllUser();
		request.setAttribute("category",Category);
		request.setAttribute("posts", posts);
		request.setAttribute("users",users );
		request.setAttribute("comments",comments);
		request.getRequestDispatcher("top.jsp").forward(request,response);
	}
}
