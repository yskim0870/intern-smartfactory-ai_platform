/*
 * This file is generated under this project, "kr.co.lguplus.dsmsv6.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 8. 17. 오전 10:32:00
*/

/**
 * This file is generated under this project, "kr.co.lguplus.dsmsv6.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 8. 17. 오전 10:32:00
*/
package kr.smartfactory.platform.web.dao.model;

import open.commons.annotation.ColumnDef;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 8. 17. 오전 10:32:00
 *
 */
public class User {

	private String userID;

	private String name;

	private Integer grade;

	private UserGrade gradeObj;

	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * @return the gradeObj
	 */
	public UserGrade getGradeObj() {
		return gradeObj;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	@ColumnDef(name = "grade", type = Integer.class, required = false)
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * @param gradeObj
	 *            the gradeObj to set
	 */
	public void setGradeObj(UserGrade gradeObj) {
		this.gradeObj = gradeObj;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@ColumnDef(name = "name", type = String.class, required = false)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	@ColumnDef(name = "user_id", type = String.class, required = false)
	public void setUserID(String userID) {
		this.userID = userID;
	}

}
