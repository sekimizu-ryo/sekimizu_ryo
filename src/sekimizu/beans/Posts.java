package sekimizu.beans;

import java.io.Serializable;
import java.util.Date;

public class Posts implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String subject;
	private String text;
	private String category;
	private String user_id;
	private Date insertDate;
	private Date updateDate;
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

	public String getuser_id() {
		return user_id;
	}
	/**
	 * @param user_id セットする user_id
	 */
	public void setuser_id(String user_id) {
		this.user_id = user_id;
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
	/**
	 * @return updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate セットする updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}