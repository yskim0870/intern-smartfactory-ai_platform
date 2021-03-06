/**
 * 
 */
package kr.smartfactory.platform.web.dto.bid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import kr.smartfactory.platform.web.dto.common.CompanyInfoDTO;

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
public class BidInfoDTO {

	// 공고번호
	private Integer id;
	
	// 공고명
	private String bidName;
	
	// 공고 시작 일자
	private Long bidStartDate;
	
	// 공고 종료 일자
	private Long bidEndDate;
	
	// 예상가격
	private String prePrice;
	
	// 부가세 = 0: 미포함, 1: 포함
	private Integer vatIncluded;
	
	// 계약상태 = 0:대기중, 1:진행중, 2:계약완료
	private Integer status;
	
	// 계약업체 ID (전문업체)
	private String contractorID;
	
	// 계약업체 정보
	private CompanyInfoDTO contractor;
	
	// 계약금액
	private String contractPrice;
	
	// 계약일자
	private Long contractDate;
	
	// 계약 시작 일자
	private Long workStartDate;
	
	// 계약 종료 일자
	private Long workEndDate;
	
	/**
	 * Default Constructor
	 */
	public BidInfoDTO() {
	}

	/**
	 * @param rs
	 */
	public BidInfoDTO(ResultSet rs) {
		try {
			this.setId(rs.getInt("bid_info.id"));
			this.setBidName(rs.getString("bid_info.bid_name"));
			this.setBidStartDate(rs.getLong("bid_info.bid_start_date"));
			this.setBidEndDate(rs.getLong("bid_info.bid_end_date"));
			this.setPrePrice(rs.getString("bid_info.pre_price"));
			this.setVatIncluded(rs.getInt("bid_info.vat_included"));
			this.setStatus(rs.getInt("bid_info.status"));
			this.setContractorID(rs.getString("bid_info.contractor_id"));
			this.setContractPrice(rs.getString("bid_info.contract_price"));
			this.setContractDate(rs.getLong("bid_info.contract_date"));
			this.setWorkStartDate(rs.getLong("bid_info.work_start_date"));
			this.setWorkEndDate(rs.getLong("bid_info.work_end_date"));
		} catch (SQLException e) {
			System.out.printf("Error : %s", e.getMessage());
		}
	}

	/**
	 * @param getsBidInfo
	 */
	public BidInfoDTO(Map<String, String> bidInfo) {
		this.setBidName(bidInfo.get("bidName"));
		this.setBidStartDate(Long.parseLong(bidInfo.get("bidStartDate")));
		this.setBidEndDate(Long.parseLong(bidInfo.get("bidEndDate")));
		this.setPrePrice(bidInfo.get("prePrice"));
		this.setVatIncluded(Integer.parseInt(bidInfo.get("vatIncluded")));
		this.setStatus(Integer.parseInt(bidInfo.get("status")));
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the bidName
	 */
	public String getBidName() {
		return bidName;
	}

	/**
	 * @return the bidStartDate
	 */
	public long getBidStartDate() {
		return bidStartDate;
	}

	/**
	 * @return the bidEndDate
	 */
	public long getBidEndDate() {
		return bidEndDate;
	}

	/**
	 * @return the prePrice
	 */
	public String getPrePrice() {
		return prePrice;
	}

	/**
	 * @return the vatIncluded
	 */
	public int getVatIncluded() {
		return vatIncluded;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return the contractorID
	 */
	public String getContractorID() {
		return contractorID;
	}

	/**
	 * @return the contractor
	 */
	public CompanyInfoDTO getContractor() {
		return contractor;
	}

	/**
	 * @return the contractPrice
	 */
	public String getContractPrice() {
		return contractPrice;
	}

	/**
	 * @return the contractDate
	 */
	public long getContractDate() {
		return contractDate;
	}

	/**
	 * @return the workStartDate
	 */
	public long getWorkStartDate() {
		return workStartDate;
	}

	/**
	 * @return the workEndDate
	 */
	public long getWorkEndDate() {
		return workEndDate;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param bidName the bidName to set
	 */
	public void setBidName(String bidName) {
		this.bidName = bidName;
	}

	/**
	 * @param bidStartDate the bidStartDate to set
	 */
	public void setBidStartDate(long bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	/**
	 * @param bidEndDate the bidEndDate to set
	 */
	public void setBidEndDate(long bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	/**
	 * @param prePrice the prePrice to set
	 */
	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}

	/**
	 * @param vatIncluded the vatIncluded to set
	 */
	public void setVatIncluded(int vatIncluded) {
		this.vatIncluded = vatIncluded;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param contractorID the contractorID to set
	 */
	public void setContractorID(String contractorID) {
		this.contractorID = contractorID;
	}

	/**
	 * @param contractor the contractor to set
	 */
	public void setContractor(CompanyInfoDTO contractor) {
		this.contractor = contractor;
	}

	/**
	 * @param contractPrice the contractPrice to set
	 */
	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	/**
	 * @param contractDate the contractDate to set
	 */
	public void setContractDate(long contractDate) {
		this.contractDate = contractDate;
	}

	/**
	 * @param workStartDate the workStartDate to set
	 */
	public void setWorkStartDate(long workStartDate) {
		this.workStartDate = workStartDate;
	}

	/**
	 * @param workEndDate the workEndDate to set
	 */
	public void setWorkEndDate(long workEndDate) {
		this.workEndDate = workEndDate;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidInfoDTO [id=");
		builder.append(id);
		builder.append(", bidName=");
		builder.append(bidName);
		builder.append(", bidStartDate=");
		builder.append(bidStartDate);
		builder.append(", bidEndDate=");
		builder.append(bidEndDate);
		builder.append(", prePrice=");
		builder.append(prePrice);
		builder.append(", vatIncluded=");
		builder.append(vatIncluded);
		builder.append(", status=");
		builder.append(status);
		builder.append(", contractorID=");
		builder.append(contractorID);
		builder.append(", contractor=");
		builder.append(contractor);
		builder.append(", contractPrice=");
		builder.append(contractPrice);
		builder.append(", contractDate=");
		builder.append(contractDate);
		builder.append(", workStartDate=");
		builder.append(workStartDate);
		builder.append(", workEndDate=");
		builder.append(workEndDate);
		builder.append("]");
		return builder.toString();
	}
	
}
