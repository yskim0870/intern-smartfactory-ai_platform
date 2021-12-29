/**
 * 
 */
package kr.smartfactory.platform.web.dto.bid;

import java.sql.ResultSet;
import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

/**
 * @packageName : kr.smartfactory.platform.web.dto.bid
 * @description : 입찰 등록, 목록 조회, 상세보기에서 사용
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
public class BidDTO {

	// 입찰공고 정보(계약자 포함)
	private BidInfoDTO bidInfo;
	
	// 입찰공고 관련 파일 목록
	private BidNoticeFileDTO files[];
	
	// 입찰공고 담당자 정보
	private BidManagerDTO manager;
	
	// 업체 정보
	private CompanyInfoDTO company;

	/**
	 * Default Constructor
	 */
	public BidDTO() {
	}
	
	/**
	 * @param rs
	 */
	public BidDTO(ResultSet rs) {
		this.setBidInfo(new BidInfoDTO(rs));
		this.setManager(new BidManagerDTO(rs));
		this.setCompany(new CompanyInfoDTO(rs));
	}

	/**
	 * @param bid
	 * @param files2
	 */
	public BidDTO(BidDTO bid, BidNoticeFileDTO[] files) {
		this.setBidInfo(bid.getBidInfo());
		this.setManager(bid.getManager());
		this.setCompany(bid.getCompany());
		this.setFiles(files);
	}

	/**
	 * @return the bid
	 */
	public BidInfoDTO getBidInfo() {
		return bidInfo;
	}

	/**
	 * @return the files
	 */
	public BidNoticeFileDTO[] getFiles() {
		return files;
	}

	/**
	 * @return the manager
	 */
	public BidManagerDTO getManager() {
		return manager;
	}

	/**
	 * @return the company
	 */
	public CompanyInfoDTO getCompany() {
		return company;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBidInfo(BidInfoDTO bid) {
		this.bidInfo = bid;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(BidNoticeFileDTO[] files) {
		this.files = files;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(BidManagerDTO manager) {
		this.manager = manager;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(CompanyInfoDTO company) {
		this.company = company;
	}
	
}
