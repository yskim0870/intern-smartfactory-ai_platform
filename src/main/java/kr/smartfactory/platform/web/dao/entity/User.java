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
package kr.smartfactory.platform.web.dao.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import open.commons.annotation.ColumnDef;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 8. 17. 오전 10:32:00
 *
 */
public class User {
	
	// 사용자 ID
	private String userID;

	// Password
	private String password;

	// 사용자 구분 0: 관리자, 1: 제조사, 2: 도메인IT전문업체
	private Integer grade;

	// 권한 정보 관리를 위한 인스턴스
	private UserGrade gradeObj;
	
	// 이름
	private String name;
	
	// 연락처
	private String telNumber;

	// Email
	private String email;

	// 부서
	private String department;

	// 직급
	private String rank;

	// 가입일자
	private long regDate;

	/**
	 * Default Constructor
	 */
	public User() {
	}
	
	/**
	 * 상세보기를 위한 생성자
	 * @param rs
	 */
	public User(ResultSet rs) {
		try {
			this.setName(rs.getString("user_info.name"));
			this.setTelNumber(rs.getString("user_info.tel_number"));
			this.setEmail(rs.getString("user_info.email"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return the passwrord
	 */
	public String getPasswrord() {
		return password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * @return the telNumber
	 */
	public String getTelNumber() {
		return telNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @return the regDate
	 */
	public long getRegDate() {
		return regDate;
	}

	/**
	 * @param passwrord the passwrord to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the gradeObj
	 */
	public UserGrade getGradeObj() {
		return gradeObj;
	}

	/**
	 * @param telNumber the telNumber to set
	 */
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(long regDate) {
		this.regDate = regDate;
	}

	/**
	 * @param grade the grade to set
	 */
	@ColumnDef(name = "grade", type = Integer.class, required = false)
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * @param gradeObj the gradeObj to set
	 */
	public void setGradeObj(UserGrade gradeObj) {
		this.gradeObj = gradeObj;
	}

	/**
	 * @param name the name to set
	 */
	@ColumnDef(name = "name", type = String.class, required = false)
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param userID the userID to set
	 */
	@ColumnDef(name = "user_id", type = String.class, required = false)
	public void setUserID(String userID) {
		this.userID = userID;
	}

}
