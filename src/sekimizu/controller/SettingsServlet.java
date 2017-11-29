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

		String id = request.getParameter("id");
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
			editUser.setPassword(request.getParameter("passwordconfirm"));
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
			editUser.setPassword(request.getParameter("passwordconfirm"));
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

		String id = request.getParameter("id");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String passwordconfirm =request.getParameter("passwordconfirm");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログイン名を入力してください");
		}else if (!login_id.matches("^[0-9A-Za-z]{6,20}")) {
			messages.add("ログイン名は半角英数字で6文字以上20文字以下としてください");
		}

		if(StringUtils.isEmpty(password) == true){

		}else if (!password.matches("^[0-9A-Za-z!?@#$%]{1,20}")) {
			messages.add("パスワードは半角文字で6文字以上20文字以下としてください");
			}

		if (!password.equals(passwordconfirm)) {
			messages.add("パスワードが一致しません。");
		}

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名称を入力してください");
		}

		UserService usersSelect = new UserService();
		Users user = usersSelect.getSettingLoginid(login_id,id);
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
