package sekimizu.beans;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login_id;
	private String password;
	private String name;
	private int branch_id;
	private int department_id;
	private int is_working;
	private Date insertDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getlogin_id() {
		return login_id;
	}

	public void setlogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getbranch_id() {
		return branch_id;
	}

	public void setbranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getdepartment_id() {
		return department_id;
	}

	public void setdepartment_id(int department_id) {
		this.department_id = department_id;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public int getis_working() {
		return is_working;
	}

	public void setis_working(int is_working) {
		this.is_working = is_working;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
