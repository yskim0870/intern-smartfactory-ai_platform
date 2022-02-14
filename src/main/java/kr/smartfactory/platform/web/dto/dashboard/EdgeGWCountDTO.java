/**
 * 
 */
package kr.smartfactory.platform.web.dto.dashboard;

/**
 * @packageName : kr.smartfactory.platform.web.dto.dashboard
 * @description : EdgeGateway 정상/비정상 작동 수에 대한 DTO
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
/**
 * @packageName : kr.smartfactory.platform.web.dto.dashboard
 * @description : 
 * @author : Younghun Yu
 * @date : 2021.12.23
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2021.12.23  Younghun Yu  최초 생성
 */
public class EdgeGWCountDTO {
	
	// 전체 개수
	private int totalCount;
	
	// 정상 작동 개수
	private int normalCount;
	
	// 비정상 작동 개수
	private int abnormalCount;
	
	/**
	 * Default Constructor
	 */
	public EdgeGWCountDTO() {
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the normalCount
	 */
	public int getNormalCount() {
		return normalCount;
	}

	/**
	 * @return the abnormalCount
	 */
	public int getAbnormalCount() {
		return abnormalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @param normalCount the normalCount to set
	 */
	public void setNormalCount(int normalCount) {
		this.normalCount = normalCount;
	}

	/**
	 * @param abnormalCount the abnormalCount to set
	 */
	public void setAbnormalCount(int abnormalCount) {
		this.abnormalCount = abnormalCount;
	}
}
