/**
 * 
 */
package kr.smartfactory.platform.web.dto.common;

import kr.smartfactory.platform.web.dao.entity.User;

/**
 * @packageName : kr.smartfactory.platform.web.dto
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
public class UserInfoDTO {

	// 사용자 ID
	private String id;
	
	// password
	private String password;
		
	// 이름
	private String name;
	
	// email
	private String email;
	
	// 연락처
	private String telNumber;
	
	// 부서
	private String department;
	
	// 직급
	private String rank;
	
	// 가입일자
	private long regDate;
	
	// 도메인IT전문업체 상태 0: 승인대기, 1: 승인완료, 2:입찰계약 제한
	private int status;
	
	/**
	 * Default Constructor
	 */
	public UserInfoDTO() {
	}

	/**
	 * @param data
	 */
	public UserInfoDTO(User data) {
		this.setName(data.getName());
		this.setTelNumber(data.getTelNumber());
		this.setEmail(data.getEmail());
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the telNumber
	 */
	public String getTelNumber() {
		return telNumber;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param telNumber the telNumber to set
	 */
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
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
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
