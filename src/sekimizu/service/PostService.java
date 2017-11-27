package sekimizu.service;

import static sekimizu.utils.CloseableUtil.*;
import static sekimizu.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import sekimizu.beans.Posts;
import sekimizu.dao.PostDao;

public class PostService {

	public void register(Posts post) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao postDao = new PostDao();
			postDao.insert(connection, post);


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


	public List<Posts> getAllPost(String Sdate,String Edate,String category) {

		Connection connection = null;
		try {
			connection = getConnection();
			PostDao postDao = new PostDao();
			List<Posts> ret = postDao.getAllPost(connection,Sdate,Edate,category);
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
			PostDao postDao = new PostDao();
			postDao.delete(connection,id);
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
