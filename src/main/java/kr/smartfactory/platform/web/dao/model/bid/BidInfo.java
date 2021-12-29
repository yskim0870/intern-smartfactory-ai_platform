/**
 * 
 */
package kr.smartfactory.platform.web.dao.model.bid;

/**
 * @packageName : kr.smartfactory.platform.web.dao.model
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.12.27
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.12.27  Younghun Yu  최초 생성
 */
public class BidInfo {
	
	// 공고 번호
	private Integer id;

	// 공고명
	private String bidName;
	
	// 공고 시작 일자
	private long bidStartDate;
	
	// 공고 종료 일자
	private long bidEndDate;
	
	// 예상가격
	private String prePrice;
	
	// 부가세 0:미포함, 1:포함
	private Integer vatIncluded;
	
	// 계약상태 0:대기중, 1:진행중, 2:계약완료
	private Integer status;
	
	// 계약자 ID
	private String contractorID;
	
	// 계약금액
	private String contractorPrice;
	
	// 계약일자
	private long contractDate;
	
	// 업무 시작 일자
	private long workStartDate;
	
	// 업무 종료 일자
	private long workEndDate;

	/**
	 * @return the id
	 */
	public Integer getId() {
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
	public Integer getVatIncluded() {
		return vatIncluded;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @return the contractorID
	 */
	public String getContractorID() {
		return contractorID;
	}

	/**
	 * @return the contractorPrice
	 */
	public String getContractorPrice() {
		return contractorPrice;
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
	public void setId(Integer id) {
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
	public void setVatIncluded(Integer vatIncluded) {
		this.vatIncluded = vatIncluded;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @param contractorID the contractorID to set
	 */
	public void setContractorID(String contractorID) {
		this.contractorID = contractorID;
	}

	/**
	 * @param contractorPrice the contractorPrice to set
	 */
	public void setContractorPrice(String contractorPrice) {
		this.contractorPrice = contractorPrice;
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
}