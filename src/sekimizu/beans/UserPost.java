package sekimizu.beans;

import java.io.Serializable;
import java.util.Date;

public class UserPost implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userId;
	private int id;
	private String subject;
	private String text;
	private String category;
	private Date insertDate;
	/**
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject セットする subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text セットする text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category セットする category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate セットする insertDate
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
