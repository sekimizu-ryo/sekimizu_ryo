package sekimizu.dao;

import static sekimizu.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import sekimizu.beans.Comment;
import sekimizu.exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
		//	sql.append("id");
			sql.append(" text");
			sql.append(", user_id");
			sql.append(", post_id");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(") VALUES (");
		//	sql.append("NEXT VALUE FOR my_seq "); // id
			sql.append(" ?"); // user_id
			sql.append(", ?"); // post_id
			sql.append(", ?"); // text
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			//ps.setInt(1, comment.getId());
			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getPostId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM comments WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();


		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public List<Comment> getComment(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM comments ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Comment> commentList = toUserList(rs);
			return  commentList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Comment> toUserList(ResultSet rs) throws SQLException {

		List<Comment> ret = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String text = rs.getString("text");
				int user_id = rs.getInt("user_id");
				int post_id = rs.getInt("post_id");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");
				Comment comment = new Comment();
				comment.setId(id);
				comment.setText(text);
				comment.setUserId(user_id);
				comment.setPostId(post_id);
				comment.setInsertDate(insertDate);
				comment.setUpdateDate(updateDate);
				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


}
