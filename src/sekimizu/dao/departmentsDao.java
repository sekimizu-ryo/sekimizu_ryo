package sekimizu.dao;

import static sekimizu.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sekimizu.beans.departments;
import sekimizu.exception.SQLRuntimeException;

public class departmentsDao {

	public List<departments> getDepartments(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM departments";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<departments> departmentsList = toUserList(rs);
			return  departmentsList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<departments> toUserList(ResultSet rs) throws SQLException {

		List<departments> ret = new ArrayList<departments>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				departments departments = new departments();
				departments.setId(id);
				departments.setName(name);
				ret.add(departments);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


}
