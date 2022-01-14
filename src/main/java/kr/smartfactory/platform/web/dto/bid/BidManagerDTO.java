/**
 * 
 */
package kr.smartfactory.platform.web.dto.bid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @packageName : kr.smartfactory.platform.web.dto.bid
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
public class BidManagerDTO {
	
	// 공고 번호
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
	
	// 담당자 Email
	private String email;
	
	/**
	 * Default Constructor
	 */
	public BidManagerDTO() {
	}

	/**
	 * @param rs
	 */
	public BidManagerDTO(ResultSet rs) {
		try {
			this.setBidID(rs.getInt("bid_manager_info.bid_id"));
			this.setManagerID(rs.getString("bid_manager_info.manager_id"));
			this.setName(rs.getString("bid_manager_info.name"));
			this.setDepartment(rs.getString("bid_manager_info.department"));
			this.setRank(rs.getString("bid_manager_info.rank"));
			this.setTelNumber(rs.getString("bid_manager_info.tel_number"));
			this.setEmail(rs.getString("bid_manager_info.email"));
		} catch (SQLException e) {
			System.out.printf("Error : %s", e.getMessage());
		}
		
	}

	/**
	 * @param getsManager
	 */
	public BidManagerDTO(Map<String, String> manager) {
		this.setManagerID(manager.get("managerID"));
		this.setName(manager.get("name"));
		this.setDepartment(manager.get("department"));
		this.setRank(manager.get("rank"));
		this.setTelNumber(manager.get("telNumber"));
		this.setEmail(manager.get("email"));
	}

	/**
	 * @return the bidID
	 */
	public int getBidID() {
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
	public void setBidID(int bidID) {
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

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidManagerDTO [bidID=");
		builder.append(bidID);
		builder.append(", managerID=");
		builder.append(managerID);
		builder.append(", name=");
		builder.append(name);
		builder.append(", department=");
		builder.append(department);
		builder.append(", rank=");
		builder.append(rank);
		builder.append(", telNumber=");
		builder.append(telNumber);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
	
}