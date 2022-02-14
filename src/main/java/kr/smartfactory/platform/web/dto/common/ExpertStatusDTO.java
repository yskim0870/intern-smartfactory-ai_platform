/**
 * 
 */
package kr.smartfactory.platform.web.dto.common;

/**
 * @packageName : kr.smartfactory.platform.web.dto.common
 * @description : 전문업체 승인 상태 정보 관리
 * @author : Younghun Yu
 * @date : 2022.02.11
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.02.11  Younghun Yu  최초 생성
 */
public class ExpertStatusDTO {
	
	// 사용자 아이디
	String userID;
	
	// 사용자 승인상태
	// 0: 승인대기, 1: 승인완료, 2: 승인(입찰)제한
	Integer status;

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpertStatusDTO [userID=");
		builder.append(userID);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
