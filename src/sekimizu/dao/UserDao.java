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

import sekimizu.beans.Users;
import sekimizu.exception.NoRowsUpdatedRuntimeException;
import sekimizu.exception.SQLRuntimeException;

public class UserDao {

	public Users getUser(Connection connection, String login,
			String password ) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ? AND is_working=0";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			List<Users> userList = toUserList(rs);

			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public Users getId(Connection connection, String id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			List<Users> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Users> getAllUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Users> userList = toUserList(rs);
			return  userList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Users> toUserList(ResultSet rs) throws SQLException {

		List<Users> ret = new ArrayList<Users>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int is_working = rs.getInt("is_working");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");

				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");


				Users users = new Users();
				users.setId(id);
				users.setlogin_id(login_id);
				users.setPassword(password);
				users.setName(name);
				users.setbranch_id(branch_id);
				users.setdepartment_id(department_id);
				users.setis_working(is_working);
				users.setInsertDate(insertDate);
				users.setUpdateDate(updateDate);

				ret.add(users);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, Users users) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			//sql.append("id");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(",branch_id");
			sql.append(", department_id");
			sql.append(", is_working");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(") VALUES (");
			//sql.append("NEXT VALUE FOR my_seq "); // id
			sql.append("?"); // login_id
			sql.append(", ?"); //password
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // department_id
			sql.append(", ?"); // is_working
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			//ps.setInt(1,users.getId());
			ps.setString(1, users.getlogin_id());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getName());
			ps.setInt(4, users.getbranch_id());
		    ps.setInt(5, users.getdepartment_id());
		    ps.setInt(6, users.getis_working());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Users users) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" login_id = ?");
			sql.append(", name = ?");
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");
			if (StringUtils.isEmpty(users.getPassword()) == false) {
				System.out.println("DAO確認"+users.getPassword());
				sql.append(", password = ?");
			}
			System.out.println("DAO確認"+users.getPassword());
			sql.append(" WHERE");
			sql.append(" id = ?");
			ps = connection.prepareStatement(sql.toString());

			if (StringUtils.isEmpty(users.getPassword()) == false) {
			ps.setString(1, users.getlogin_id());
			ps.setString(2, users.getName());
			ps.setInt(3, users.getbranch_id());
		    ps.setInt(4, users.getdepartment_id());
			ps.setInt(5, users.getId());
			System.out.println("DAO確認"+users.getPassword());
			}else{
				ps.setString(1, users.getlogin_id());
				ps.setString(2, users.getName());
				ps.setInt(3, users.getbranch_id());
			    ps.setInt(4, users.getdepartment_id());
				ps.setString(5, users.getPassword());
				ps.setInt(6, users.getId());
			}
			System.out.println(ps.toString());
			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}


	public void getworking(Connection connection, int is_working,String id) {

		PreparedStatement ps = null;
		try {
			String sql = "UPDATE users SET is_working = ? WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, is_working);
			ps.setString(2, id);
			System.out.println(ps.toString());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
