/**
 * 
 */
package kr.smartfactory.platform.web.dto.bid;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.smartfactory.platform.web.dao.entity.bid.BidNoticeFile;
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
	
	// 웹에서 파일정보를 제외한 나머지 정보를 JSON.stirngify()로 받아온 정보들 -> 문자열 정보이므로 다시 변환해준다.
	private Map<String, String> sBidInfo;
	private Map<String, String> sManager;

	// 입찰공고 정보(계약자 포함)
	private BidInfoDTO bidInfo;
	
	// 입찰공고파일 목록
	private MultipartFile[] bidFiles;
	
	// 샘플데이터파일 목록
	private MultipartFile[] sampleFiles;
	
	// 입찰공고문 파일, 샘플데이터 파일들이 담긴 목록
	private List<BidNoticeFile> fileList;
	
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
	 * @return the sBidInfo
	 */
	public Map<String, String> getsBidInfo() {
		return sBidInfo;
	}

	/**
	 * @param sBidInfo the sBidInfo to set
	 */
	public void setsBidInfo(Map<String, String> sBidInfo) {
		this.sBidInfo = sBidInfo;
	}

	/**
	 * @return the sManager
	 */
	public Map<String, String> getsManager() {
		return sManager;
	}

	/**
	 * @param sManager the sManager to set
	 */
	public void setsManager(Map<String, String> sManager) {
		this.sManager = sManager;
	}
	
	/**
	 * @return the fileList
	 */
	public List<BidNoticeFile> getFileList() {
		return fileList;
	}

	/**
	 * @param fileList the fileList to set
	 */
	public void setFileList(List<BidNoticeFile> fileList) {
		this.fileList = fileList;
	}

	/**
	 * @param contractor the contractor to set
	 */
	public void setContractor(UserInfoDTO contractor) {
		this.contractor = contractor;
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
		builder.append(", manager=");
		builder.append(manager);
		builder.append(", company=");
		builder.append(company);
		builder.append(", contractor=");
		builder.append(contractor);
		builder.append(", bidFiles=");
		builder.append(Arrays.toString(bidFiles));
		builder.append(", sampleFiles=");
		builder.append(Arrays.toString(sampleFiles));
		builder.append("]");
		return builder.toString();
	}
}
