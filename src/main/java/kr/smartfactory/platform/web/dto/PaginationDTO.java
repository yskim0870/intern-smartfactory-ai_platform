/**
 * 
 */
package kr.smartfactory.platform.web.dto;

import java.util.List;

/**
 * @packageName : kr.smartfactory.platform.web.dto
 * @description :
 * @author : Younghun Yu
 * @date : 2021.12.23 =========================================================== DATE AUTHOR NOTE ----------------------------------------------------------- 2021.12.23 Younghun Yu 최초 생성
 */
public class PaginationDTO<DTO> {

	private List<DTO> items;
	private int totalCount;

	public PaginationDTO() {

	}

	public PaginationDTO(List<DTO> items, int totalCount) {
		this.items = items;
		this.totalCount = totalCount;
	}

	/**
	 * items을(를) 호출합니다.
	 *
	 * @since 2021. 12. 23. 오후 4:52:41
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public List<DTO> getItems() {
		return items;
	}

	/**
	 * items 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 23. 오후 4:52:41
	 * @author "KyungHun Park"
	 *
	 * @param items
	 */
	public void setItems(List<DTO> items) {
		this.items = items;
	}

	/**
	 * totalCount을(를) 호출합니다.
	 *
	 * @since 2021. 12. 23. 오후 4:52:41
	 * @author "KyungHun Park"
	 *
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * totalCount 을(를) 지정합니다.
	 *
	 * @since 2021. 12. 23. 오후 4:52:41
	 * @author "KyungHun Park"
	 *
	 * @param totalCount
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
