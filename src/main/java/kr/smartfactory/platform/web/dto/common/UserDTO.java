/**
 * 
 */
package kr.smartfactory.platform.web.dto.common;

import java.util.List;

import kr.smartfactory.platform.web.dao.entity.User;

/**
 * @packageName : kr.smartfactory.platform.web.dto
 * @description :
 * @author : Younghun Yu
 * @date : 2021.12.23 =========================================================== DATE AUTHOR NOTE ----------------------------------------------------------- 2021.12.23 Younghun Yu 최초 생성
 */
public class UserDTO {

	// 사용자 정보 관리
	private UserInfoDTO userInfo;

	// 회사 정보 관리
	private CompanyInfoDTO companyInfo;

	// 자격증 정보 관리
	private List<CertificateDTO> certificateInfo;

	// 전문업체 승인상태 관리
	private ExpertStatusDTO expertInfo;

	private UserGradeDTO userGrade;

	/**
	 * @return the userGrade
	 */
	public UserGradeDTO getUserGrade() {
		return userGrade;
	}

	/**
	 * @param userGrade
	 *            the userGrade to set
	 */
	public void setUserGrade(UserGradeDTO userGrade) {
		this.userGrade = userGrade;
	}

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

	public UserDTO(CompanyInfoDTO company, UserInfoDTO userInfo) {
		this.companyInfo = company;
		this.userInfo = userInfo;
	}

	/**
	 * @param userInfo2
	 * @param companyInfo2
	 */
	public UserDTO(UserInfoDTO userInfo, CompanyInfoDTO companyInfo, List<CertificateDTO> certificates, ExpertStatusDTO expertInfo) {
		this.setUserInfo(userInfo);
		this.setCompanyInfo(companyInfo);
		this.setCertificateInfo(certificates);
		this.setExpertInfo(expertInfo);
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
	 * @param userInfo
	 *            the userInfo to set
	 */
	public void setUserInfo(UserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @param companyInfo
	 *            the companyInfo to set
	 */
	public void setCompanyInfo(CompanyInfoDTO companyInfo) {
		this.companyInfo = companyInfo;
	}

	/**
	 * @return the certificateInfo
	 */
	public List<CertificateDTO> getCertificateInfo() {
		return certificateInfo;
	}

	/**
	 * @param certificateInfo
	 *            the certificateInfo to set
	 */
	public void setCertificateInfo(List<CertificateDTO> certificateInfo) {
		this.certificateInfo = certificateInfo;
	}

	/**
	 * @return the expertInfo
	 */
	public ExpertStatusDTO getExpertInfo() {
		return expertInfo;
	}

	/**
	 * @param expertInfo
	 *            the expertInfo to set
	 */
	public void setExpertInfo(ExpertStatusDTO expertInfo) {
		this.expertInfo = expertInfo;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [userInfo=");
		builder.append(userInfo);
		builder.append(", companyInfo=");
		builder.append(companyInfo);
		builder.append(", certificateInfo=");
		builder.append(certificateInfo);
		builder.append(", expertInfo=");
		builder.append(expertInfo);
		builder.append("]");
		return builder.toString();
	}
}