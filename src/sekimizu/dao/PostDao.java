package sekimizu.dao;

import static sekimizu.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import sekimizu.beans.Posts;
import sekimizu.exception.SQLRuntimeException;

public class PostDao {

	public void insert(Connection connection, Posts post) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts ( ");
			sql.append(" subject");
			sql.append(", text");
			sql.append(", category");
			sql.append(", user_id");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // subject
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", ?"); // user_id
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, post.getSubject());
			ps.setString(2, post.getText());
		    ps.setString(3, post.getCategory());
		    ps.setString(4, post.getuser_id());
		    System.out.println(ps.toString());


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
			String sql = "DELETE FROM posts WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();


		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public List<Posts> getAllPost(Connection connection,String sDate,String eDate,String category) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM posts ");
			sql.append("WHERE");
			sql.append(" insert_date >= ?");
			sql.append("AND");
			sql.append(" insert_date < ?");
			if(StringUtils.isEmpty(category) == false){
				sql.append("AND");
				sql.append(" category = ?");
			}

			sql.append("ORDER BY insert_date DESC");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1,sDate + " 00:00:00");
			ps.setString(2,eDate + " 23:59:59");

			if(StringUtils.isEmpty(category) == false){
			ps.setString(3,category);
			}

			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			List<Posts> postList = toUserList(rs);
			return  postList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<Posts> toUserList(ResultSet rs) throws SQLException {

		List<Posts> ret = new ArrayList<Posts>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String category = rs.getString("category");
				String user_id = rs.getString("user_id");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");
				Posts posts = new Posts();
				posts.setId(id);
				posts.setSubject(subject);
				posts.setText(text);
				posts.setCategory(category);
				posts.setuser_id(user_id);
				posts.setInsertDate(insertDate);
				posts.setUpdateDate(updateDate);
				ret.add(posts);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
