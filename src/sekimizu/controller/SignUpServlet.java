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

import sekimizu.beans.Branches;
import sekimizu.beans.Users;
import sekimizu.beans.departments;
import sekimizu.service.UserService;
import sekimizu.service.branchesService;
import sekimizu.service.departmentsService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<Branches> branches = new branchesService().getBranches();
		List<departments>departments= new departmentsService().getDepartments();
		request.setAttribute("branches",branches);
		request.setAttribute("departments",departments);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {

			Users users = new Users();
			users.setlogin_id(request.getParameter("login_id"));
			users.setName(request.getParameter("name"));
			users.setPassword(request.getParameter("password"));
			users.setbranch_id(Integer.parseInt(request.getParameter("branch_id")));
			users.setdepartment_id(Integer.parseInt(request.getParameter("department_id")));
			new UserService().register(users);
			response.sendRedirect("userall");

		} else {
			Users users = new Users();
			users.setlogin_id(request.getParameter("login_id"));
			users.setName(request.getParameter("name"));
			users.setbranch_id(Integer.parseInt(request.getParameter("branch_id")));
			users.setdepartment_id(Integer.parseInt(request.getParameter("department_id")));
			session.setAttribute("errorMessages", messages);
			request.setAttribute("users", users);
			List<Branches> branches = new branchesService().getBranches();
			List<departments>departments= new departmentsService().getDepartments();
			request.setAttribute("branches",branches);
			request.setAttribute("departments",departments);
			request.getRequestDispatcher("signup.jsp").forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages ) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログイン名を入力してください");
			}else if (!login_id.matches("^[0-9A-Za-z]{1,20}")) {
				messages.add("ログイン名は半角英数字で6文字以上20文字以下としてください");
				}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}else if (!password.matches("^[0-9A-Za-z!?@#$%]{1,20}")) {
			messages.add("パスワードは半角文字で6文字以上20文字以下としてください");
			}

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}

		if (10 < name.length()) {
			messages.add("名称は10文字以下で入力してください");
		}

		UserService usersSelect = new UserService();
		Users user = usersSelect.getLoginId(login_id);
		HttpSession session = request.getSession();

		if (user != null) {
		messages.add("既にログインIDが使用されています");
		session.setAttribute("errorMessages", messages);
		}


		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
