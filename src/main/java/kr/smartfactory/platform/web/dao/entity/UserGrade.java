/*
 * This file is generated under this project, "kr.co.lguplus.dsmsv6.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 8. 17. 오전 10:49:27
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
 * @since: 2021. 8. 17. 오전 10:49:27
*/
package kr.smartfactory.platform.web.dao.entity;

import java.util.HashMap;

import open.commons.annotation.ColumnDef;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 8. 17. 오전 10:49:27
 *
 */
public class UserGrade {

	public static final int NOT_ENTERED_ID_OR_PASSWORD = -3;
	public static final int UNKNOWN_USER = -2;
	public static final int INVALID_UNKNOWN_AND_ERROR = -1;

	/** 수퍼 관리자 */
	public static final int SUPER_ADMIN = 0;
	/** 관리자 */
	public static final int ADMIN = 1;
	/** 일반사용자 */
	public static final int USER = 2;

	private static final HashMap<Integer, String> USER_GRADES = new HashMap<>();

	static {
		USER_GRADES.put(NOT_ENTERED_ID_OR_PASSWORD, "NOT_ENTERED_ID_OR_PASSWORD");
		USER_GRADES.put(UNKNOWN_USER, "UNKNOWN_USER");
		USER_GRADES.put(INVALID_UNKNOWN_AND_ERROR, "ERROR");

		USER_GRADES.put(SUPER_ADMIN, "ROLE_SUPER_ADMIN");
		USER_GRADES.put(ADMIN, "ROLE_ADMIN");
		USER_GRADES.put(USER, "ROLE_USER");
	}

	private Integer gradeID;
	private String name;
	private String desc;

	/**
	 * Desc : Constructor of UserGrade.java class
	 */
	public UserGrade() {
	}

	public UserGrade(Integer id, String name, String desc) {
		this.gradeID = id;
		this.name = name;
		this.desc = desc;
	}

	private UserGrade(int grade) {
		this.gradeID = grade;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return the gradeID
	 */
	public Integer getGradeID() {
		return gradeID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	@ColumnDef(name = "desc", type = String.class, required = false)
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param gradeID
	 *            the gradeID to set
	 */
	@ColumnDef(name = "grade_id", type = Integer.class, required = false)
	public void setGradeID(Integer gradeID) {
		this.gradeID = gradeID;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@ColumnDef(name = "name", type = String.class, required = false)
	public void setName(String name) {
		this.name = name;
	}

	public static boolean available(int grade) {
		switch (grade) {
			case UserGrade.SUPER_ADMIN:
			case UserGrade.ADMIN:
			case UserGrade.USER:
				return true;
			default:
				return false;
		}
	}

	public static UserGrade getUserGrade(int id, String name, String desc) {

		int _id_ = INVALID_UNKNOWN_AND_ERROR;

		if (USER_GRADES.containsKey(id)) {
			_id_ = id;
			name = USER_GRADES.get(id);
		}

		UserGrade grade = new UserGrade(_id_);

		grade.setName(name);
		grade.setDesc(desc);

		return grade;
	}

}
