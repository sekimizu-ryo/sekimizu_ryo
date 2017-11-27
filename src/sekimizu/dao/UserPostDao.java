package sekimizu.dao;

import static sekimizu.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import sekimizu.beans.UserPost;
import sekimizu.exception.SQLRuntimeException;

public class UserPostDao {

	public List<UserPost> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_posts ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPost> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPost> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try {
			while (rs.next()) {

				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserPost post = new UserPost();
				post.setId(id);
				post.setUserId(userId);
				post.setSubject(subject);
				post.setText(text);
				post.setCategory(category);
				post.setInsertDate(insertDate);


				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
