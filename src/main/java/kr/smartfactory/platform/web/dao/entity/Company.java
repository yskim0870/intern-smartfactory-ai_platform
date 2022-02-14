package kr.smartfactory.platform.web.dao.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

public class Company {

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
	 * 
	 */
	public Company() {
	}

	/**
	 * @param companyInfo
	 */
	public Company(CompanyInfoDTO companyInfo) {
		this.setBusinessNumber(companyInfo.getBusinessNumber());
		this.setName(companyInfo.getName());
		this.setAddress(companyInfo.getAddress());
		this.setCondition(companyInfo.getCondition());
		this.setIndustryType(companyInfo.getIndustryType());
		this.setTelNumber(companyInfo.getTelNumber());
		this.setFaxNumber(companyInfo.getFaxNumber());
		this.setSiteUrl(companyInfo.getSiteUrl());
		this.setCeoName(companyInfo.getCeoName());
	}

	/**
	 * @param rs
	 */
	public Company(ResultSet rs) {
		try {
			this.setName(rs.getString("company_info.name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * address을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * businessNumber을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getBusinessNumber() {
		return businessNumber;
	}

	/**
	 * ceoName을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getCeoName() {
		return ceoName;
	}

	/**
	 * condition을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * faxNumber을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * industryType을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getIndustryType() {
		return industryType;
	}

	/**
	 * name을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * siteUrl을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * telNumber을(를) 호출합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public String getTelNumber() {
		return telNumber;
	}

	/**
	 * address 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * businessNumber 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param businessNumber
	 */
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	/**
	 * ceoName 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param ceoName
	 */
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	/**
	 * condition 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * faxNumber 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param faxNumber
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * industryType 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param industryType
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	/**
	 * name 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * siteUrl 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param siteUrl
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * telNumber 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 30. 오후 1:46:45
	 * @author "KyungHun Park"
	 *
	 * @param telNumber
	 */
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

}
