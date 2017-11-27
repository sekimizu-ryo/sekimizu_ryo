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

@WebServlet(urlPatterns = { "/settings" })

public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Users loginUser = (Users) session.getAttribute("loginUser");

		String id = request.getParameter("id");
		loginUser.setId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("id", id);
		Users editUser = new UserService().getId(id);
		request.setAttribute("editUser", editUser);
		List<Branches> branches = new branchesService().getBranches();
		List<departments>departments= new departmentsService().getDepartments();
		request.setAttribute("branches",branches);
		request.setAttribute("departments",departments);
		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();


		if (isValid(request, messages) == true) {


			Users editUser = new Users();

			editUser.setId(Integer.parseInt(request.getParameter("id")));
			editUser.setlogin_id(request.getParameter("login_id"));
			editUser.setPassword(request.getParameter("password"));
			editUser.setName(request.getParameter("name"));
			editUser.setbranch_id(Integer.parseInt(request.getParameter("branch_id")));
			editUser.setdepartment_id(Integer.parseInt(request.getParameter("department_id")));
			new UserService().update(editUser);
			response.sendRedirect("userall");

		} else {
			Users editUser = new Users();
			editUser.setId(Integer.parseInt(request.getParameter("id")));
			editUser.setlogin_id(request.getParameter("login_id"));
			editUser.setPassword(request.getParameter("password"));
			editUser.setName(request.getParameter("name"));
			editUser.setbranch_id(Integer.parseInt(request.getParameter("branch_id")));
			editUser.setdepartment_id(Integer.parseInt(request.getParameter("department_id")));
			session.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", editUser);
			List<Branches> branches = new branchesService().getBranches();
			List<departments>departments= new departmentsService().getDepartments();
			request.setAttribute("branches",branches);
			request.setAttribute("departments",departments);
			request.getRequestDispatcher("settings.jsp").forward(request,response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログイン名を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
