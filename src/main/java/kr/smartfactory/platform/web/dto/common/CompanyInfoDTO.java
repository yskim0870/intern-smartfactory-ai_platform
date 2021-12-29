/**
 * 
 */
package kr.smartfactory.platform.web.dto.common;

import java.sql.ResultSet;
import java.sql.SQLException;

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
public class CompanyInfoDTO {
	
	// 사업자등록번호
	private String businessNumber;
	
	// 회사명
	private String name;
	
	// 회사 주소
	private String address;
	
	// 업태
	private String condition;
	
	// 업종
	private String industryType;
	
	// 대표 전화번호
	private String telNumber;
	
	// fax 번호
	private String faxNumber;
	
	// 회사 홈페이지
	private String siteUrl;
	
	// 대표자 이름
	private String ceoName;
	
	/**
	 * Default Constructor
	 */
	public CompanyInfoDTO() {
	}

	/**
	 * @param rs
	 */
	public CompanyInfoDTO(ResultSet rs) {
		try {
			this.setBusinessNumber(rs.getString("company_info.business_number"));
			this.setName(rs.getString("company_info.name"));
			this.setAddress(rs.getString("company_info.address"));
			this.setCondition(rs.getString("company_info.condition"));
			this.setIndustryType(rs.getString("company_info.industry_type"));
			this.setTelNumber(rs.getString("company_info.tel_number"));
			this.setFaxNumber(rs.getString("company_info.fax_number"));
			this.setSiteUrl(rs.getString("company_info.site_url"));
			this.setCeoName(rs.getString("company_info.ceo_name"));
		} catch (SQLException e) {
			System.out.printf("Error : %s", e.getMessage());
		}
	}

	/**
	 * @return the businessNumber
	 */
	public String getBusinessNumber() {
		return businessNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @return the industryType
	 */
	public String getIndustryType() {
		return industryType;
	}

	/**
	 * @return the telNumber
	 */
	public String getTelNumber() {
		return telNumber;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @return the siteUrl
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * @return the ceoName
	 */
	public String getCeoName() {
		return ceoName;
	}

	/**
	 * @param businessNumber the businessNumber to set
	 */
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @param industryType the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	/**
	 * @param telNumber the telNumber to set
	 */
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	/**
	 * @param faxNumber the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @param siteUrl the siteUrl to set
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * @param ceoName the ceoName to set
	 */
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
}