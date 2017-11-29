package sekimizu.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sekimizu.beans.Users;

@WebFilter(urlPatterns={"/settings","/userall","/signup"})
public class AuthentiCationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		Users loginUser = (Users) session.getAttribute("loginUser");
		List<String> messages = new ArrayList<String>();

		if (loginUser == null) {
			messages.add("ログアウトされています。ログインしてください。");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse)response).sendRedirect("login");
			return ;

		}else
		if (loginUser.getbranch_id() != 1||loginUser.getdepartment_id() != 1) {
			messages.add("権限がありません");
				session.setAttribute("errorMessages", messages);
				((HttpServletResponse)response).sendRedirect("./");
				return ;
		}
		chain.doFilter(request, response); // サーブレットを実行
		System.out.println("AuthentiCationFilterが実行されました。");
		}
	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void destroy() {
	}

}