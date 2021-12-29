/**
 * 
 */
package kr.smartfactory.platform.web.dto;

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
public class PaginationDTO<T> {

	// array of DTO - 목록을 표시할 DTO
	private T items[];
	
	// 전체 아이템 숫자
	private int totalCount;
	
	/**
	 * Default Constructor
	 */
	public PaginationDTO() {
	}

	/**
	 * @return the items
	 */
	public T[] getItems() {
		return items;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(T[] items) {
		this.items = items;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
