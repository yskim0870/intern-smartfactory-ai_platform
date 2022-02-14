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
	private Long regDate;

	// 기타 정보 (국가연구자번호)
	private String etcInfo;

	/**
	 * Default Constructor
	 */
	public User() {
	}

	/**
	 * 상세보기를 위한 생성자
	 * 
	 * @param rs
	 */
	public User(ResultSet rs) {
		try {
			this.setUserID(rs.getString("user_info.id"));
			this.setName(rs.getString("user_info.name"));
			this.setTelNumber(rs.getString("user_info.tel_number"));
			this.setEmail(rs.getString("user_info.email"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the etcInfo
	 */
	public String getEtcInfo() {
		return etcInfo;
	}

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
	 * @return the passwrord
	 */
	public String getPasswrord() {
		return password;
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
	public Long getRegDate() {
		return regDate;
	}

	/**
	 * @return the telNumber
	 */
	public String getTelNumber() {
		return telNumber;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	@ColumnDef(name = "department", type = String.class, required = false)
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	@ColumnDef(name = "email", type = String.class, required = false)
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param etcInfo
	 *            the etcInfo to set
	 */
	@ColumnDef(name = "etc_info", type = String.class, required = false)
	public void setEtcInfo(String etcInfo) {
		this.etcInfo = etcInfo;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	@ColumnDef(name = "user_type", type = Integer.class, required = false)
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
	 * @param passwrord
	 *            the passwrord to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	@ColumnDef(name = "rank", type = String.class, required = false)
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	@ColumnDef(name = "reg_date", type = Long.class, required = false)
	public void setRegDate(Long regDate) {
		this.regDate = regDate;
	}

	/**
	 * @param telNumber
	 *            the telNumber to set
	 */
	@ColumnDef(name = "tel_number", type = String.class, required = false)
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	@ColumnDef(name = "id", type = String.class, required = false)
	public void setUserID(String userID) {
		this.userID = userID;
	}

}
