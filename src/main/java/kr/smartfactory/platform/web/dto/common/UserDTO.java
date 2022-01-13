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
 * 2021.12.23 Younghun Yu 최초 생성
 */
public class UserDTO {

	// 사용자 정보 관리
	private UserInfoDTO userInfo;

	// 회사 정보 관리
	private CompanyInfoDTO companyInfo;

	/**
	 * Default Constructor
	 */
	public UserDTO() {
	}

	/**
	 * @param data
	 */
	public UserDTO(User data) {
		this.userInfo.setName(data.getName());
		this.userInfo.setTelNumber(data.getTelNumber());
		this.userInfo.setEmail(data.getEmail());
	}

	/**
	 * @return the userInfo
	 */
	public UserInfoDTO getUserInfo() {
		return userInfo;
	}

	/**
	 * @return the companyInfo
	 */
	public CompanyInfoDTO getCompanyInfo() {
		return companyInfo;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @param companyInfo the companyInfo to set
	 */
	public void setCompanyInfo(CompanyInfoDTO companyInfo) {
		this.companyInfo = companyInfo;
	}
}
