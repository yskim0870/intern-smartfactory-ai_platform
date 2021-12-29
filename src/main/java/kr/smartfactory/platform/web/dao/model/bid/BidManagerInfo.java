/**
 * 
 */
package kr.smartfactory.platform.web.dao.model.bid;

/**
 * @packageName : kr.smartfactory.platform.web.dao.model.bid
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.27
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.27  Younghun Yu  최초 생성
 */
public class BidManagerInfo {

	// 공고번호
	private Integer bidID;
	
	// 제조사 ID
	private String managerID;
	
	// 담당자 이름
	private String name;
	
	// 담당자 부서
	private String department;
	
	// 담당자 직책
	private String rank;
	
	// 담당자 연락처
	private String telNumber;
	
	// 담당자 이메일
	private String email;

	/**
	 * @return the bidID
	 */
	public Integer getBidID() {
		return bidID;
	}

	/**
	 * @return the managerID
	 */
	public String getManagerID() {
		return managerID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * @param bidID the bidID to set
	 */
	public void setBidID(Integer bidID) {
		this.bidID = bidID;
	}

	/**
	 * @param managerID the managerID to set
	 */
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
}