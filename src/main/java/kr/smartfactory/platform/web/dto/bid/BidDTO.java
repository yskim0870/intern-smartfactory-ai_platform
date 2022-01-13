/**
 * 
 */
package kr.smartfactory.platform.web.dto.bid;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;
import kr.smartfactory.platform.web.dto.common.UserInfoDTO;

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
	
	// 입찰공고파일 목록
	private MultipartFile[] bidFiles;
	
	// 샘플데이터파일 목록
	private MultipartFile[] sampleFiles;
	
	// 입찰공고 담당자 정보
	private BidManagerDTO manager;
	
	// 업체 정보
	private CompanyInfoDTO company;
	
	// 계약자 정보
	private UserInfoDTO contractor;

	/**
	 * @return the contractor
	 */
	public UserInfoDTO getContractor() {
		return contractor;
	}

	/**
	 * @param contractor the contractor to set
	 */
	public void setContractor(UserInfoDTO contractor) {
		this.contractor = contractor;
	}

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
	public BidDTO(BidDTO bid, List<MultipartFile> files) {
		this.setBidInfo(bid.getBidInfo());
		this.setManager(bid.getManager());
		this.setCompany(bid.getCompany());
//		this.setFiles(files);
	}

	/**
	 * @return the bidFiles
	 */
	public MultipartFile[] getBidFiles() {
		return bidFiles;
	}

	/**
	 * @param bidFiles the bidFiles to set
	 */
	public void setBidFiles(MultipartFile[] bidFiles) {
		this.bidFiles = bidFiles;
	}

	/**
	 * @return the sampleFiles
	 */
	public MultipartFile[] getSampleFiles() {
		return sampleFiles;
	}

	/**
	 * @param sampleFiles the sampleFiles to set
	 */
	public void setSampleFiles(MultipartFile[] sampleFiles) {
		this.sampleFiles = sampleFiles;
	}

	/**
	 * @return the bid
	 */
	public BidInfoDTO getBidInfo() {
		return bidInfo;
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

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidDTO [bidInfo=");
		builder.append(bidInfo);
		builder.append(", files=");
		builder.append(", manager=");
		builder.append(manager);
		builder.append(", company=");
		builder.append(company);
		builder.append("]");
		return builder.toString();
	}
	
	
}
