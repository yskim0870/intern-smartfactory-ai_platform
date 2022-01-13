/**
 * 
 */
package kr.smartfactory.platform.web.dto;

import java.util.List;

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
	private List<T> items;
	
	// 전체 아이템 숫자
	private int totalCount;
	
	/**
	 * Default Constructor
	 */
	public PaginationDTO() {
	}
	

	/**
	 * @param items
	 * @param totalCount
	 */
	public PaginationDTO(List<T> items, int totalCount) {
		this.items = items;
		this.totalCount = totalCount;
	}


	/**
	 * @return the items
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param list the items to set
	 */
	public void setItems(List<T> list) {
		this.items = list;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaginationDTO [items=");
		builder.append(items);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append("]");
		return builder.toString();
	}
	
	
}
