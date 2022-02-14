/**
 * 
 */
package kr.smartfactory.platform.web.dto.dashboard;

/**
 * @packageName : kr.smartfactory.platform.web.dto.dashboard
 * @description : 대쉬보드의 입찰공고 수, 제조사 수, 도메인IT전문업체 수, EdgeGW 수에 대한 DTO
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
public class DashboardCountDTO {
	
	// 입찰 공고 수
	private int bidCount;
	
	// 제조사 수
	private int manufacturerCount;
	
	// 도메인IT전문가 수
	private int expertCount;
	
	// EdgeGateway 수
	private EdgeGWCountDTO edgeGWCount;
	
	/**
	 * Default Constructor
	 */
	public DashboardCountDTO() {
	}

	/**
	 * @return the bidCount
	 */
	public int getBidCount() {
		return bidCount;
	}

	/**
	 * @return the manufacturerCount
	 */
	public int getManufacturerCount() {
		return manufacturerCount;
	}

	/**
	 * @return the expertCount
	 */
	public int getExpertCount() {
		return expertCount;
	}

	/**
	 * @return the edgeGWCount
	 */
	public EdgeGWCountDTO getEdgeGWCount() {
		return edgeGWCount;
	}

	/**
	 * @param bidCount the bidCount to set
	 */
	public void setBidCount(int bidCount) {
		this.bidCount = bidCount;
	}

	/**
	 * @param manufacturerCount the manufacturerCount to set
	 */
	public void setManufacturerCount(int manufacturerCount) {
		this.manufacturerCount = manufacturerCount;
	}

	/**
	 * @param expertCount the expertCount to set
	 */
	public void setExpertCount(int expertCount) {
		this.expertCount = expertCount;
	}

	/**
	 * @param edgeGWCount the edgeGWCount to set
	 */
	public void setEdgeGWCount(EdgeGWCountDTO edgeGWCount) {
		this.edgeGWCount = edgeGWCount;
	}
}