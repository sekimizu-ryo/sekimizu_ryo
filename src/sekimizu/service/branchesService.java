package sekimizu.service;

import static sekimizu.utils.CloseableUtil.*;
import static sekimizu.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import sekimizu.beans.Branches;
import sekimizu.dao.branchsDao;

public class branchesService {

	public List<Branches> getBranches() {

		Connection connection = null;
		try {
			connection = getConnection();
			branchsDao branchesDao = new branchsDao();
			List<Branches> ret =branchesDao.getBranches(connection);
			commit(connection);
			return ret;

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
