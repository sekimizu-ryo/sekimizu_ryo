package sekimizu.service;

import static sekimizu.utils.CloseableUtil.*;
import static sekimizu.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import sekimizu.beans.Comment;
import sekimizu.dao.CommentDao;

public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

			commit(connection);
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

	public List<Comment> getComment() {

		Connection connection = null;
		try {
			connection = getConnection();
			CommentDao commentDao = new CommentDao();
			List<Comment> ret =commentDao.getComment(connection);
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


	public void delete(int id) {

		Connection connection = null;
		try {
			connection = getConnection();
			CommentDao commentDao = new CommentDao();
			commentDao.delete(connection,id);
			commit(connection);

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
