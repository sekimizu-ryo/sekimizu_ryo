package sekimizu.service;

import static sekimizu.utils.CloseableUtil.*;
import static sekimizu.utils.DBUtil.*;

import java.sql.Connection;

import sekimizu.beans.Users;
import sekimizu.dao.UserDao;
import sekimizu.utils.CipherUtil;

public class LoginService {

	public Users login(String login, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			Users user = userDao
					.getUser(connection, login, encPassword);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
